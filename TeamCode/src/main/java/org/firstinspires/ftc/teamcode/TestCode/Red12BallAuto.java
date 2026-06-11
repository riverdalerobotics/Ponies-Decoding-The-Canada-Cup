package org.firstinspires.ftc.teamcode.TestCode;


import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.qualcomm.hardware.sparkfun.SparkFunOTOS;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.ParallelCommandGroup;
import com.seattlesolvers.solverslib.command.ParallelDeadlineGroup;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.geometry.Pose2d;

import org.firstinspires.ftc.teamcode.Commands.ChassisCommands.FieldDefaultCommand;
import org.firstinspires.ftc.teamcode.Commands.ChassisCommands.FollowPath;
import org.firstinspires.ftc.teamcode.Commands.ChassisCommands.RobotDefaultCommand;
import org.firstinspires.ftc.teamcode.Commands.IntakeCommands.IntakeCommand;
import org.firstinspires.ftc.teamcode.Commands.IntakeCommands.IntakeDefaultCommand;
import org.firstinspires.ftc.teamcode.Commands.IntakeCommands.LiftIntakeArms;
import org.firstinspires.ftc.teamcode.Commands.ShooterCommands.FeedShooter;
import org.firstinspires.ftc.teamcode.Commands.ShooterCommands.RevThreeToSetVelo;
import org.firstinspires.ftc.teamcode.Commands.ShooterCommands.RevThreeToVelo;
import org.firstinspires.ftc.teamcode.Commands.ShooterCommands.RevThreeToVeloUsingDistance;
import org.firstinspires.ftc.teamcode.Commands.ShooterCommands.RunNoPIDF;
import org.firstinspires.ftc.teamcode.Commands.ShooterCommands.ShooterDefaultCommand;
import org.firstinspires.ftc.teamcode.RobotConstants;
import org.firstinspires.ftc.teamcode.Subsystems.ChassisSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LimelightSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;
import org.firstinspires.ftc.teamcode.pedroPathing.Paths;

@Autonomous(group = "Test", name = "Red 12 Ball")
public class Red12BallAuto extends CommandOpMode {
    Follower follower;
    Paths.Red12BallPath path;

    LimelightSubsystem limelight;

    ShooterSubsystem snap, crackle, pop;
    ShooterDefaultCommand snapDefault, crackleDefault, popDefault;

    IntakeSubsystem intake;
    IntakeDefaultCommand intakeDefault;

    SequentialCommandGroup shootGroup;
    ParallelCommandGroup intakeGroup;

    Pose startPose;

    ChassisSubsystem chassis;
    RobotDefaultCommand chassisDefaultCommand;

    SparkFunOTOS otos;

    GamepadEx gamepadEx;

    TelemetryManager telemetryManager;

    @Override
    public void initialize() {

        chassis = new ChassisSubsystem(hardwareMap);
        chassisDefaultCommand = new RobotDefaultCommand(chassis, telemetryManager);
        chassis.setDefaultCommand(chassisDefaultCommand);
        chassis.initRed();

        startPose = new Pose(125, 113, Math.toRadians(90));

        chassis.setStartPoseOTOS(new SparkFunOTOS.Pose2D(startPose.getX(), startPose.getY(), startPose.getHeading()));

        follower = Constants.createFollower(hardwareMap);
        path = new Paths.Red12BallPath(follower);
        follower.setStartingPose(path.getStartPos());

        telemetryManager = PanelsTelemetry.INSTANCE.getTelemetry();

        limelight = new LimelightSubsystem(hardwareMap);

        snap = new ShooterSubsystem(RobotConstants.Hardware.SNAP, hardwareMap);
        snapDefault = new ShooterDefaultCommand(snap, limelight);
        snap.setDefaultCommand(snapDefault);

        crackle = new ShooterSubsystem(RobotConstants.Hardware.CRACKLE, hardwareMap);
        crackleDefault = new ShooterDefaultCommand(crackle, limelight);
        crackle.setDefaultCommand(crackleDefault);

        pop = new ShooterSubsystem(RobotConstants.Hardware.POP, hardwareMap);
        popDefault = new ShooterDefaultCommand(pop, limelight);
        pop.setDefaultCommand(popDefault);

        intake = new IntakeSubsystem(hardwareMap);
        intakeDefault = new IntakeDefaultCommand(intake);

        /// repeat sequences
        shootGroup = new SequentialCommandGroup(
                new RevThreeToVeloUsingDistance(snap, crackle, pop, limelight, follower, 'r', true),
                new ParallelDeadlineGroup(
                        new WaitCommand(RobotConstants.Teleop.SHOOTER_TIMER),
                        new RevThreeToVeloUsingDistance(snap, crackle, pop, limelight, follower, 'r', false),
                        new FeedShooter(snap),
                        new FeedShooter(crackle),
                        new FeedShooter(pop)

                ),
                new RunNoPIDF(snap, crackle, pop, 0)
        );
        follower.setMaxPower(1);
        /// auto
        schedule(new SequentialCommandGroup(
                //shoots preload
                new ParallelDeadlineGroup(
                        new FollowPath(follower, path.ShootPreLoad),
                        new RevThreeToVeloUsingDistance(snap, crackle, pop, limelight, chassis, 'r', false)

                ),
                shootGroup,

                //intake far (2nd) line, gate, shoot 2nd line
                new ParallelDeadlineGroup(
                        new SequentialCommandGroup(
                                new FollowPath(follower, path.Intake2ndLine),
                                new FollowPath(follower, path.Gate)
                        ),

                        new ParallelCommandGroup(
                                new LiftIntakeArms(snap),
                                new LiftIntakeArms(pop),
                                new IntakeCommand(intake),
                                new RunNoPIDF(snap, crackle, pop, -0.3)
                        )

                ),

                new ParallelDeadlineGroup(
                        new FollowPath(follower, path.Shoot2ndLine),
                        new SequentialCommandGroup(
                            new ParallelDeadlineGroup(
                                    new WaitCommand(1000),
                                    new RunNoPIDF(snap, crackle, pop, -0.3)
                            ),
                                new RevThreeToVeloUsingDistance(snap, crackle, pop, limelight, follower,'r', false)
                        ),
                        new IntakeCommand(intake)

                        //new RevThreeToVeloUsingDistance(snap, crackle, pop, limelight, chassis, 'r')
                ),

                shootGroup,

                //intake close (1st) line, shoot 1st line
                new ParallelDeadlineGroup(
                        new FollowPath(follower, path.Intake1stLine),
                        new ParallelCommandGroup(
                                new LiftIntakeArms(snap),
                                new LiftIntakeArms(pop),
                                new IntakeCommand(intake)       ,
                                new RunNoPIDF(snap, crackle, pop, -0.3)
                        )

                ),
                new ParallelDeadlineGroup(
                        new FollowPath(follower, path.Shoot1stLine),
                        new SequentialCommandGroup(
                                new ParallelDeadlineGroup(
                                        new WaitCommand(1000),
                                        new RunNoPIDF(snap, crackle, pop, -0.3)
                                ),
                                new RevThreeToVeloUsingDistance(snap, crackle, pop, limelight, follower,'r', false)
                        ),
                        new IntakeCommand(intake)


                        //new RevThreeToVeloUsingDistance(snap, crackle, pop, limelight, chassis, 'r')
                ),
                shootGroup,

                new ParallelDeadlineGroup(
                        new FollowPath(follower, path.Intake3rdLine),
                        new ParallelCommandGroup(
                                new LiftIntakeArms(snap),
                                new LiftIntakeArms(pop),
                                new IntakeCommand(intake),
                                new RunNoPIDF(snap, crackle, pop, -0.3)
                        )
                        //intakeGroup
                ),
                new ParallelDeadlineGroup(
                        new FollowPath(follower, path.Shoot3rdLine),
                        new SequentialCommandGroup(
                                new ParallelDeadlineGroup(
                                        new WaitCommand(1000),
                                        new RunNoPIDF(snap, crackle, pop, -0.3)
                                ),
                                new RevThreeToVeloUsingDistance(snap, crackle, pop, limelight, follower,'r', false)
                        ),
                        new IntakeCommand(intake)
                ),
                shootGroup,
                new FollowPath(follower, path.Leave)
        ));
        RobotConstants.Teleop.ROBOT_START_POSITION_FROM_AUTO = follower.getPose();
    }

    @Override
    public void run(){
        super.run();
        telemetryManager.addData("position", follower.getPose());
        telemetryManager.update(telemetry);
        follower.update();
    }

    @Override
    public void end() {
        super.end();
        RobotConstants.Teleop.ROBOT_START_POSITION_FROM_AUTO = follower.getPose();
        telemetryManager.addData("End Pose", RobotConstants.Teleop.ROBOT_START_POSITION_FROM_AUTO);
        telemetryManager.update(telemetry);
    }
}
