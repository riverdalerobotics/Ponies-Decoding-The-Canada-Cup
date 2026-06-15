package org.firstinspires.ftc.teamcode.TestCode;

import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.ParallelCommandGroup;
import com.seattlesolvers.solverslib.command.ParallelDeadlineGroup;
import com.seattlesolvers.solverslib.command.ParallelRaceGroup;
import com.seattlesolvers.solverslib.command.Robot;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.Commands.ChassisCommands.FollowPath;
import org.firstinspires.ftc.teamcode.Commands.ChassisCommands.RobotDefaultCommand;
import org.firstinspires.ftc.teamcode.Commands.IntakeCommands.IntakeCommand;
import org.firstinspires.ftc.teamcode.Commands.IntakeCommands.IntakeDefaultCommand;
import org.firstinspires.ftc.teamcode.Commands.IntakeCommands.LiftIntakeArms;
import org.firstinspires.ftc.teamcode.Commands.ShooterCommands.FeedShooter;
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

public class BlueFar6Ball extends CommandOpMode {
    Follower follower;
    Paths.BlueFar6BallPath path;

    LimelightSubsystem limelight;

    ShooterSubsystem snap, crackle, pop;
    ShooterDefaultCommand snapDefault, crackleDefault, popDefault;

    IntakeSubsystem intake;
    IntakeDefaultCommand intakeDefault;

    SequentialCommandGroup shootGroup;

    ChassisSubsystem chassis;
    RobotDefaultCommand chassisDefaultCommand;

    TelemetryManager telemetryManager;

    @Override
    public void initialize(){
        chassis = new ChassisSubsystem(hardwareMap);
        chassisDefaultCommand = new RobotDefaultCommand(chassis, telemetryManager);
        chassis.setDefaultCommand(chassisDefaultCommand);
        chassis.initRed();

        follower = Constants.createFollower(hardwareMap);
        path = new Paths.BlueFar6BallPath(follower);
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
                new RevThreeToVeloUsingDistance(snap, crackle, pop, limelight, follower, 'b', true),
                new ParallelDeadlineGroup(
                        new WaitCommand(RobotConstants.Teleop.SHOOTER_TIMER),
                        new RevThreeToVeloUsingDistance(snap, crackle, pop, limelight, follower, 'b', false),
                        new FeedShooter(snap),
                        new FeedShooter(crackle),
                        new FeedShooter(pop)

                ),
                new RunNoPIDF(snap, crackle, pop, 0)
        );
        follower.setMaxPower(1);

        schedule(
                new SequentialCommandGroup(
                        new ParallelDeadlineGroup(
                                new FollowPath(follower, path.ShootPreLoad),
                                new RevThreeToVeloUsingDistance(snap, crackle, pop, limelight, follower,  'b', false)
                        ),
                        shootGroup,

                        new ParallelDeadlineGroup(
                                new FollowPath(follower, path.Intake3rdLine),
                                new LiftIntakeArms(snap),
                                new LiftIntakeArms(pop),
                                new IntakeCommand(intake),
                                new RunNoPIDF(snap, crackle, pop, -0.3)
                        ),

                        new ParallelDeadlineGroup(
                                new FollowPath(follower, path.Shoot3rdLine),
                                new SequentialCommandGroup(
                                        new ParallelDeadlineGroup(
                                                new WaitCommand(1000),
                                                new RunNoPIDF(snap, crackle, pop, -0.3)
                                        ),
                                        new RevThreeToVeloUsingDistance(snap, crackle, pop, limelight, follower, 'b', false )
                                ),
                                new IntakeCommand(intake)
                        ),

                        shootGroup,

                        new FollowPath(follower, RobotConstants.goTo90(follower))
                )
        );

    }

    @Override
    public void run(){
        super.run();
        follower.update();
    }

    @Override
    public void end(){
        super.end();
    }
}
