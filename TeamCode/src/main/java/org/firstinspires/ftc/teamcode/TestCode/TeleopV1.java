package org.firstinspires.ftc.teamcode.TestCode;

import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.CommandScheduler;
import com.seattlesolvers.solverslib.command.ParallelCommandGroup;
import com.seattlesolvers.solverslib.command.ParallelDeadlineGroup;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.button.Button;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.Commands.ChassisCommands.ChassisLookToAprilTagTeleOp;
import org.firstinspires.ftc.teamcode.Commands.ChassisCommands.FieldDefaultCommand;
import org.firstinspires.ftc.teamcode.Commands.IntakeCommands.IntakeDefaultCommand;
import org.firstinspires.ftc.teamcode.Commands.ShooterCommands.FeedShooter;
import org.firstinspires.ftc.teamcode.Commands.ShooterCommands.RevThreeToVelo;
import org.firstinspires.ftc.teamcode.Commands.ShooterCommands.ShooterDefaultCommand;
import org.firstinspires.ftc.teamcode.RobotConstants;
import org.firstinspires.ftc.teamcode.Subsystems.ChassisSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LimelightSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.ShooterSubsystem;

public class TeleopV1 extends CommandOpMode {
    ChassisSubsystem chassis;
    LimelightSubsystem limelight;
    ShooterSubsystem snap, crackle, pop;
    IntakeSubsystem intake;
    FieldDefaultCommand chassisDefault;
    ShooterDefaultCommand snapDefault, crackleDefault, popDefault;
    IntakeDefaultCommand intakeDefault;

    GamepadEx driver = new GamepadEx(gamepad1);
    TelemetryManager telemetryM;
    Button shoot, rev, lockOn;

    @Override
    public void initialize() {
        limelight = new LimelightSubsystem(hardwareMap);
        telemetryM = PanelsTelemetry.INSTANCE.getTelemetry();

        chassis = new ChassisSubsystem(hardwareMap);
        chassisDefault = new FieldDefaultCommand(chassis, telemetryM, driver);
        chassis.setDefaultCommand(chassisDefault);

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

    }

    @Override
    public void run(){
        CommandScheduler.getInstance().run();

        schedule(chassisDefault);

        rev.whileHeld(
                new RevThreeToVelo(snap, crackle, pop, limelight)
        );

        shoot.whenPressed(
                new SequentialCommandGroup(
                        new FeedShooter(snap),
                        new FeedShooter(crackle),
                        new FeedShooter(pop) //need timer
                )
        );

        lockOn.whileHeld(
                new ChassisLookToAprilTagTeleOp(chassis, limelight, telemetryM, driver)
        );
    }
}
