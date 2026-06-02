package org.firstinspires.ftc.teamcode.TestCode;


import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
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
import org.firstinspires.ftc.teamcode.Commands.IntakeCommands.IntakeCommand;
import org.firstinspires.ftc.teamcode.Commands.IntakeCommands.IntakeDefaultCommand;
import org.firstinspires.ftc.teamcode.Commands.IntakeCommands.LiftIntakeArms;
import org.firstinspires.ftc.teamcode.Commands.ShooterCommands.FeedShooter;
import org.firstinspires.ftc.teamcode.Commands.ShooterCommands.RevThreeToVelo;
import org.firstinspires.ftc.teamcode.Commands.ShooterCommands.RunNoPIDF;
import org.firstinspires.ftc.teamcode.Commands.ShooterCommands.ShooterDefaultCommand;
import org.firstinspires.ftc.teamcode.RobotConstants;
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

    @Override
    public void initialize() {
        startPose = new Pose(125, 113, Math.toRadians(90)); ///Ill move this later
        follower = Constants.createFollower(hardwareMap);
        path = new Paths.Red12BallPath(follower);
        follower.setStartingPose(startPose);

        limelight = new LimelightSubsystem(hardwareMap);

        snap = new ShooterSubsystem(RobotConstants.Hardware.SNAP, hardwareMap);
        snapDefault = new ShooterDefaultCommand(snap, limelight);
        snap.setDefaultCommand(snapDefault);

        crackle = new ShooterSubsystem(RobotConstants.Hardware.CRACKLE, hardwareMap);
        crackleDefault = new ShooterDefaultCommand(crackle, limelight);
        crackle.setDefaultCommand(crackleDefault);

        pop = new ShooterSubsystem(RobotConstants.Hardware.CRACKLE, hardwareMap);
        popDefault = new ShooterDefaultCommand(pop, limelight);
        pop.setDefaultCommand(popDefault);

        intake = new IntakeSubsystem(hardwareMap);
        intakeDefault = new IntakeDefaultCommand(intake);

        /// repeat sequences
        shootGroup = new SequentialCommandGroup(
                new RevThreeToVelo(snap, crackle, pop, limelight, true),
                new ParallelDeadlineGroup(
                        new WaitCommand(RobotConstants.Teleop.SHOOTER_TIMER),
                        new RevThreeToVelo(snap, crackle, pop, limelight),
                        new FeedShooter(snap),
                        new FeedShooter(crackle),
                        new FeedShooter(pop)

                ),
                new RunNoPIDF(snap, crackle, pop, 0)

        );

        /// auto
        schedule(new SequentialCommandGroup(
                //shoots preload
                new ParallelDeadlineGroup(
                        new FollowPath(follower, path.ShootPreLoad),
                        new RevThreeToVelo(snap, crackle, pop, limelight)
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
                                new IntakeCommand(intake)
                        )

                ),
                new ParallelDeadlineGroup(
                        new FollowPath(follower, path.Shoot2ndLine),
                        new IntakeCommand(intake),
                        new RevThreeToVelo(snap, crackle, pop, limelight)
                ),
                shootGroup,

                //intake close (1st) line, shoot 1st line
                new ParallelDeadlineGroup(
                        new FollowPath(follower, path.Intake1stLine),
                        new ParallelCommandGroup(
                                new LiftIntakeArms(snap),
                                new LiftIntakeArms(pop),
                                new IntakeCommand(intake)
                        )

                ),
                new ParallelDeadlineGroup(
                        new FollowPath(follower, path.Shoot1stLine),
                        new IntakeCommand(intake),
                        new RevThreeToVelo(snap, crackle, pop, limelight)
                ),
                shootGroup,

                new ParallelDeadlineGroup(
                        new FollowPath(follower, path.Intake3rdLine),
                        new ParallelCommandGroup(
                                new LiftIntakeArms(snap),
                                new LiftIntakeArms(pop),
                                new IntakeCommand(intake)
                        )
                        //intakeGroup
                ),
                new ParallelDeadlineGroup(
                        new FollowPath(follower, path.Shoot3rdLine),
                        new IntakeCommand(intake),
                        new RevThreeToVelo(snap, crackle, pop, limelight)
                ),
                shootGroup,
                new FollowPath(follower, path.Gate2),
                new ParallelDeadlineGroup(
                        new SequentialCommandGroup(
                                new FollowPath(follower, path.IntakeFromGate),
                                new WaitCommand(1000)
                                ),
                        new IntakeCommand(intake)
                ),
                new ParallelDeadlineGroup(
                        new FollowPath(follower, path.ShootGate),
                        new RevThreeToVelo(snap, crackle, pop, limelight)
                ),
                shootGroup
        ));
    }

    @Override
    public void run(){
        super.run();
        follower.update();
    }
}
