package org.firstinspires.ftc.teamcode.Commands.ChassisCommands;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.ChassisSubsystem;

public class ResetPosition extends CommandBase {
    ChassisSubsystem chassis;
    public ResetPosition(ChassisSubsystem chassis){
        this.chassis = chassis;
    }

    @Override
    public void initialize() {
        super.initialize();
        chassis.getOtos().resetTracking();

    }
}
