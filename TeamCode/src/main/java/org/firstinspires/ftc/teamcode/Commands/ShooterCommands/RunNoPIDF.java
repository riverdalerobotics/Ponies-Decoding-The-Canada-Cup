package org.firstinspires.ftc.teamcode.Commands.ShooterCommands;

import com.bylazar.telemetry.TelemetryManager;
import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.ShooterSubsystem;

public class RunNoPIDF extends CommandBase {
    ShooterSubsystem snap, crackle, pop;
    double power;
    TelemetryManager telemetryManager;
    public RunNoPIDF(ShooterSubsystem snap, ShooterSubsystem crackle, ShooterSubsystem pop, double power) {
        this.snap = snap;
        this.crackle = crackle;
        this.pop = pop;
        this.power = power;
        addRequirements(snap, crackle, pop);
    }

    public RunNoPIDF(ShooterSubsystem snap, ShooterSubsystem crackle, ShooterSubsystem pop, double power, TelemetryManager telemetryManager) {
        this.snap = snap;
        this.crackle = crackle;
        this.pop = pop;
        this.power = power;
        this.telemetryManager = telemetryManager;
        addRequirements(snap, crackle, pop);
    }

    @Override
    public void execute() {
        super.execute();
        snap.getShooterMotor().set(power);
        crackle.getShooterMotor().set(power);
        pop.getShooterMotor().set(power);
        if(telemetryManager != null){
            telemetryManager.addLine("Do the thing");
        }
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
