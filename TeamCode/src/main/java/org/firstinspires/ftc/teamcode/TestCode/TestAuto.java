package org.firstinspires.ftc.teamcode.TestCode;

import static org.firstinspires.ftc.teamcode.pedroPathing.Tuning.follower;

import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.ParallelDeadlineGroup;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.pedroCommand.FollowPathCommand;
import com.seattlesolvers.solverslib.util.Timing;

import org.firstinspires.ftc.teamcode.Commands.ChassisCommands.FieldDefaultCommand;
import org.firstinspires.ftc.teamcode.Commands.ChassisCommands.FollowPath;
import org.firstinspires.ftc.teamcode.Commands.IntakeCommands.IntakeDefaultCommand;
import org.firstinspires.ftc.teamcode.Commands.ShooterCommands.FeedShooter;
import org.firstinspires.ftc.teamcode.Commands.ShooterCommands.RevThreeToVelo;
import org.firstinspires.ftc.teamcode.Commands.ShooterCommands.ShooterDefaultCommand;
import org.firstinspires.ftc.teamcode.RobotConstants;
import org.firstinspires.ftc.teamcode.Subsystems.ChassisSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LimelightSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;
import org.firstinspires.ftc.teamcode.pedroPathing.Paths;

@Autonomous(group = "Test", name = "Test Pathing")
public class TestAuto extends CommandOpMode {
    LimelightSubsystem limelight;
    ShooterSubsystem snap, crackle, pop;
    IntakeSubsystem intake;
    FieldDefaultCommand chassisDefault;
    ShooterDefaultCommand snapDefault, crackleDefault, popDefault;
    IntakeDefaultCommand intakeDefault;
    Paths.Blue12BallPath blue12BallPath;
    GamepadEx driver;
    Follower follower;
    TelemetryManager telemetryM;
    PathChain Path1;
    @Override
    public void initialize() {
        follower = Constants.createFollower(hardwareMap);
        blue12BallPath = new Paths.Blue12BallPath(follower);
        telemetryM = PanelsTelemetry.INSTANCE.getTelemetry();
        follower.setStartingPose(blue12BallPath.getStartPose());
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
        intake.setDefaultCommand(intakeDefault);






        schedule(new SequentialCommandGroup(
                new FollowPath(follower, blue12BallPath.ShootFirstset),
                new RevThreeToVelo(snap, crackle, pop, limelight, true),
                new ParallelDeadlineGroup(
                        new WaitCommand(RobotConstants.Teleop.SHOOTER_TIMER),
                        new RevThreeToVelo(snap, crackle, pop, limelight),
                        new FeedShooter(snap),
                        new FeedShooter(crackle),
                        new FeedShooter(pop)
                )
                )
                );
    }

    @Override
    public void run() {
        super.run();
    }
}
