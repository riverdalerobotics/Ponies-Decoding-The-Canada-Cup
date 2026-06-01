package org.firstinspires.ftc.teamcode.Commands;

import com.seattlesolvers.solverslib.command.CommandBase;
import com.seattlesolvers.solverslib.command.CommandOpMode;

import org.firstinspires.ftc.teamcode.Subsystems.LimelightSubsystem;

public class LimelightSees extends CommandBase {
    LimelightSubsystem limelightSubsystem;

    public LimelightSees(LimelightSubsystem limelightSubsystem){
        this.limelightSubsystem = limelightSubsystem;
    }

    @Override
    public boolean isFinished(){ return limelightSubsystem.getLimelightResult().isValid();

    }
}
