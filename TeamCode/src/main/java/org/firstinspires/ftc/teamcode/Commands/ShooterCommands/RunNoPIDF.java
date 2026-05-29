package org.firstinspires.ftc.teamcode.Commands.ShooterCommands;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.ShooterSubsystem;

public class RunNoPIDF extends CommandBase {
    ShooterSubsystem snap, crackle, pop;
    double power;
    public RunNoPIDF(ShooterSubsystem snap, ShooterSubsystem crackle, ShooterSubsystem pop, double power) {
        this.snap = snap;
        this.crackle = crackle;
        this.pop = pop;
        this.power = power;
        addRequirements(snap, crackle, pop);
    }

    @Override
    public void execute() {
        super.execute();
        snap.getShooterMotor().set(power);
        crackle.getShooterMotor().set(power);
        pop.getShooterMotor().set(power);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
