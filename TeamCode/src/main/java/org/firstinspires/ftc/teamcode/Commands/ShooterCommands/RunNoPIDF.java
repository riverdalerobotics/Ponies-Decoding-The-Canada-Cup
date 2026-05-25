package org.firstinspires.ftc.teamcode.Commands.ShooterCommands;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.ShooterSubsystem;

public class RunNoPIDF extends CommandBase {
    ShooterSubsystem snap, crackle, pop;
    public RunNoPIDF(ShooterSubsystem snap, ShooterSubsystem crackle, ShooterSubsystem pop) {
        this.snap = snap;
        this.crackle = crackle;
        this.pop = pop;
        addRequirements(snap, crackle, pop);
    }

    @Override
    public void execute() {
        super.execute();
        snap.getShooterMotor().set(1);
        crackle.getShooterMotor().set(1);
        pop.getShooterMotor().set(1);
    }
}
