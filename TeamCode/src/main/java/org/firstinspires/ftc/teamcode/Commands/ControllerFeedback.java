package org.firstinspires.ftc.teamcode.Commands;

import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.seattlesolvers.solverslib.command.CommandBase;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.CommandScheduler;
import com.seattlesolvers.solverslib.command.ParallelCommandGroup;
import com.seattlesolvers.solverslib.command.ParallelDeadlineGroup;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;
import com.seattlesolvers.solverslib.command.button.Button;
import com.seattlesolvers.solverslib.command.button.GamepadButton;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.Commands.ChassisCommands.ChassisLookToAprilTagTeleOp;
import org.firstinspires.ftc.teamcode.Commands.ChassisCommands.FieldDefaultCommand;
import org.firstinspires.ftc.teamcode.Commands.ControllerFeedback;
import org.firstinspires.ftc.teamcode.Commands.IntakeCommands.IntakeCommand;
import org.firstinspires.ftc.teamcode.Commands.IntakeCommands.IntakeDefaultCommand;
import org.firstinspires.ftc.teamcode.Commands.ShooterCommands.FeedShooter;
import org.firstinspires.ftc.teamcode.Commands.ShooterCommands.RevThreeToVelo;
import org.firstinspires.ftc.teamcode.Commands.ShooterCommands.ShooterDefaultCommand;
import org.firstinspires.ftc.teamcode.RobotConstants;
import org.firstinspires.ftc.teamcode.Subsystems.ChassisSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LimelightSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.ShooterSubsystem;

public class ControllerFeedback extends CommandBase {
    GamepadEx driver;
    ShooterSubsystem snap, crackle, pop;
    Gamepad.LedEffect rgbEffect;
    public ControllerFeedback(GamepadEx gamepad, ShooterSubsystem snap, ShooterSubsystem crackle, ShooterSubsystem pop){
        this.driver = gamepad;
        this.snap = snap;
        this.crackle = crackle;
        this.pop = pop;
        rgbEffect = new Gamepad.LedEffect.Builder()
                .addStep(1, 0, 0, 1000) // Show red for 250ms
                .addStep(1, 1, 1, 1000) // Show white for 250ms
                .build();
        driver.gamepad.runLedEffect(rgbEffect);

    }

    @Override
    public void execute() {
        super.execute();

        if(Math.abs(snap.getSpeed()-snap.setpoint)< RobotConstants.Tuning.SHOOTER_TOLERANCE[1] &&Math.abs(crackle.getSpeed()-crackle.setpoint)< RobotConstants.Tuning.SHOOTER_TOLERANCE[1]&&Math.abs(pop.getSpeed()-pop.setpoint)< RobotConstants.Tuning.SHOOTER_TOLERANCE[1]){
            driver.gamepad.rumble(800);
            driver.gamepad.setLedColor(0, 1, 0, 1000);

        }
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
        driver.gamepad.stopRumble();
        driver.gamepad.setLedColor(0,0,1, 100000000);
    }
}
