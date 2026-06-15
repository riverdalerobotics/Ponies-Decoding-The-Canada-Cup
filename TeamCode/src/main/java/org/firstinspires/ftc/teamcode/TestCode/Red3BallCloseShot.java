package org.firstinspires.ftc.teamcode.TestCode;

import com.bylazar.telemetry.TelemetryManager;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.ParallelCommandGroup;
import com.seattlesolvers.solverslib.command.ParallelDeadlineGroup;
import com.seattlesolvers.solverslib.command.Robot;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.Commands.ChassisCommands.FollowPath;
import org.firstinspires.ftc.teamcode.Commands.ChassisCommands.RobotDefaultCommand;
import org.firstinspires.ftc.teamcode.Commands.IntakeCommands.IntakeDefaultCommand;
import org.firstinspires.ftc.teamcode.Commands.ShooterCommands.FeedShooter;
import org.firstinspires.ftc.teamcode.Commands.ShooterCommands.RevThreeToVelo;
import org.firstinspires.ftc.teamcode.Commands.ShooterCommands.RevThreeToVeloUsingDistance;
import org.firstinspires.ftc.teamcode.Commands.ShooterCommands.ShooterDefaultCommand;
import org.firstinspires.ftc.teamcode.RobotConstants;
import org.firstinspires.ftc.teamcode.Subsystems.ChassisSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LimelightSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;
import org.firstinspires.ftc.teamcode.pedroPathing.Paths;

public class Red3BallCloseShot extends CommandOpMode {
    Follower follower;
    Paths.Red3BallCloseShotPath path;

    LimelightSubsystem limelight;

    ShooterSubsystem snap, crackle, pop;
    ShooterDefaultCommand snapDefault, crackleDefault, popDefault;

    IntakeSubsystem intake;
    IntakeDefaultCommand intakeDefault;

    SequentialCommandGroup shootGroup;

    ChassisSubsystem chassis;
    RobotDefaultCommand chassisDefault;

    TelemetryManager telemetryManager;

    @Override
    public void initialize(){
        follower = Constants.createFollower(hardwareMap);
        path = new Paths.Red3BallCloseShotPath(follower);

        chassis = new ChassisSubsystem(hardwareMap);
        chassisDefault = new RobotDefaultCommand(chassis, telemetryManager);
        chassis.initRed();

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

        shootGroup = new SequentialCommandGroup(
                new RevThreeToVelo(snap, crackle, pop, limelight, true),
                new ParallelDeadlineGroup(
                        new WaitCommand(RobotConstants.Teleop.SHOOTER_TIMER),
                        new RevThreeToVelo(snap, crackle, pop, limelight),
                        new FeedShooter(snap),
                        new FeedShooter(crackle),
                        new FeedShooter(pop)
                )
        );

        schedule(
                new SequentialCommandGroup(
                    new ParallelDeadlineGroup(
                            new FollowPath(follower, path.ShootPreLoad),
                            new RevThreeToVeloUsingDistance(snap, crackle, pop, limelight, chassis, 'r', false)
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
}
