package org.firstinspires.ftc.teamcode.Commands.ShooterCommands;

import com.seattlesolvers.solverslib.command.CommandBase;
import com.seattlesolvers.solverslib.util.Timing;

import org.firstinspires.ftc.teamcode.Subsystems.ShooterSubsystem;

public class FeedShooter extends CommandBase {
    ShooterSubsystem shooter;
    Timing.Timer timer;
    public FeedShooter(ShooterSubsystem shooter){
        this.shooter = shooter;
        addRequirements(shooter);
    }

    @Override
    public void initialize() {
        super.initialize();

    }
}
