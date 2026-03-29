package org.firstinspires.ftc.teamcode.Commands.ChassisCommands;

import com.bylazar.telemetry.TelemetryManager;
import com.seattlesolvers.solverslib.command.CommandBase;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.Subsystems.ChassisSubsystem;

public class FieldDefaultCommand extends CommandBase {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    ChassisSubsystem chassisSubsystem;
    TelemetryManager telemetryManager;
    GamepadEx gamepadEx;

    /**
     *
     * @param chassisSubsystem drive subsystem
     * @param telemetryManager telemetry yea
     * @param gamepadEx gg
     */

    public FieldDefaultCommand(ChassisSubsystem chassisSubsystem, TelemetryManager telemetryManager,
            GamepadEx gamepadEx){
        this.chassisSubsystem = chassisSubsystem;
        this.telemetryManager = telemetryManager;
        this.gamepadEx = gamepadEx;
        addRequirements(chassisSubsystem);
    }

    @Override
    public void execute() {
        chassisSubsystem.fieldOriented(
                gamepadEx.getLeftX(),
                gamepadEx.getLeftY(),
                -gamepadEx.getRightX());
    }


}
