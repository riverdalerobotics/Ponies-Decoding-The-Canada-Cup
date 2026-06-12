package org.firstinspires.ftc.teamcode.TestCode;

import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.CommandScheduler;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.ParallelCommandGroup;
import com.seattlesolvers.solverslib.command.ParallelDeadlineGroup;
import com.seattlesolvers.solverslib.command.ScheduleCommand;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;
import com.seattlesolvers.solverslib.command.button.Button;
import com.seattlesolvers.solverslib.command.button.GamepadButton;
import com.seattlesolvers.solverslib.command.button.Trigger;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;
import com.seattlesolvers.solverslib.gamepad.TriggerReader;

import org.firstinspires.ftc.teamcode.Commands.ChassisCommands.ChassisLookToAprilTagTeleOp;
import org.firstinspires.ftc.teamcode.Commands.ChassisCommands.FieldDefaultCommand;
import org.firstinspires.ftc.teamcode.Commands.ChassisCommands.ResetYaw;
import org.firstinspires.ftc.teamcode.Commands.ControllerFeedback;
import org.firstinspires.ftc.teamcode.Commands.IntakeCommands.IntakeCommand;
import org.firstinspires.ftc.teamcode.Commands.IntakeCommands.IntakeDefaultCommand;
import org.firstinspires.ftc.teamcode.Commands.IntakeCommands.LiftIntakeArms;
import org.firstinspires.ftc.teamcode.Commands.IntakeCommands.SpitCommand;
import org.firstinspires.ftc.teamcode.Commands.ShooterCommands.FeedShooter;
import org.firstinspires.ftc.teamcode.Commands.ShooterCommands.ResetShooterFeed;
import org.firstinspires.ftc.teamcode.Commands.ShooterCommands.RevThreeToVelo;
import org.firstinspires.ftc.teamcode.Commands.ShooterCommands.RunNoPIDF;
import org.firstinspires.ftc.teamcode.Commands.ShooterCommands.ShooterDefaultCommand;
import org.firstinspires.ftc.teamcode.RobotConstants;
import org.firstinspires.ftc.teamcode.Subsystems.ChassisSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LimelightSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.ShooterSubsystem;
@TeleOp(name = "YES - Frankie")
public class TeleopV1 extends CommandOpMode {
    ChassisSubsystem chassis;
    LimelightSubsystem limelight;
    ShooterSubsystem snap, crackle, pop;
    IntakeSubsystem intake;
    FieldDefaultCommand chassisDefault;
    ShooterDefaultCommand snapDefault, crackleDefault, popDefault;
    IntakeDefaultCommand intakeDefault;

    GamepadEx driver;
    TelemetryManager telemetryM;
    Button shoot, rev, lockOn, spitButton, spinMotor, resetYaw;
    Trigger intakeTrigger, holdArms;

    @Override
    public void initialize() {
        limelight = new LimelightSubsystem(hardwareMap);
        telemetryM = PanelsTelemetry.INSTANCE.getTelemetry();
        driver = new GamepadEx(gamepad1);

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
        spinMotor = new GamepadButton(driver, GamepadKeys.Button.X);
        lockOn = new GamepadButton(driver, GamepadKeys.Button.LEFT_BUMPER);
        rev = new GamepadButton(driver, GamepadKeys.Button.RIGHT_BUMPER);
        shoot = new GamepadButton(driver, GamepadKeys.Button.A);
        spitButton = new GamepadButton(driver, GamepadKeys.Button.X);
        resetYaw = new GamepadButton(driver, GamepadKeys.Button.START);
        chassis.initRed();


        intakeTrigger = new Trigger(()->new TriggerReader(driver, GamepadKeys.Trigger.LEFT_TRIGGER).isDown());
        holdArms = new Trigger(()->new TriggerReader(driver, GamepadKeys.Trigger.RIGHT_TRIGGER).isDown());


        rev.whileHeld(
                new ParallelCommandGroup(
                        new ControllerFeedback(driver, snap, crackle, pop),
                        new RevThreeToVelo(snap, crackle, pop, limelight)
                )


        );


        shoot.whenPressed(

                        new ParallelDeadlineGroup(
                                new WaitCommand(RobotConstants.Teleop.SHOOTER_TIMER),
                                new FeedShooter(snap),
                                new FeedShooter(crackle),
                                new FeedShooter(pop)
                        )


        );

        resetYaw.whenPressed(
                new ResetYaw(chassis)
        );

        holdArms.whileActiveContinuous(

                        new ParallelCommandGroup(
                                new LiftIntakeArms(snap),
                                new LiftIntakeArms(pop)
                        )


        );



        lockOn.whileHeld(
                new ChassisLookToAprilTagTeleOp(chassis, limelight, telemetryM, driver)
        );
        intakeTrigger.whileActiveContinuous(
                new IntakeCommand(intake)
        );
        spitButton.whileHeld(
                new SpitCommand(intake)
        );


    }

    @Override
    public void run(){
        CommandScheduler.getInstance().run();
        telemetryM.addData("POP is green?", pop.getColour());
        telemetryM.addData("POP rgb", pop.rgb());
        telemetryM.addData("SNAP shooter speed", snap.getSpeed());
        telemetryM.addData("Yaw", chassis.getOtos().getPosition().h);
        telemetryM.update(telemetry);

    }
}
