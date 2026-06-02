package org.firstinspires.ftc.teamcode.Commands.IntakeCommands;

import com.seattlesolvers.solverslib.command.CommandBase;
import com.seattlesolvers.solverslib.util.Timing;

import org.firstinspires.ftc.teamcode.Subsystems.ShooterSubsystem;

public class LiftIntakeArms extends CommandBase {
    ShooterSubsystem shooter;
    Timing.Timer timer;
    public LiftIntakeArms(ShooterSubsystem shooter){
        this.shooter = shooter;
    }

    @Override
    public void initialize() {
        super.initialize();
        shooter.liftArm();

    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
        shooter.resetFeed();
    }
}
