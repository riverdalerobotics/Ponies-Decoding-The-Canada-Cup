package org.firstinspires.ftc.teamcode.Commands.ShooterCommands;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.RobotConstants;
import org.firstinspires.ftc.teamcode.Subsystems.LimelightSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.ShooterSubsystem;

public class ShooterDefaultCommand extends CommandBase {
    ShooterSubsystem shooter;
    LimelightSubsystem limelight;
    public ShooterDefaultCommand(ShooterSubsystem shooter, LimelightSubsystem limelight){
        this.shooter = shooter;
        this.limelight = limelight;
        addRequirements(shooter);
    }

    @Override
    public void initialize() {
        super.initialize();
        shooter.setShooterMotor(0);
        shooter.resetFeed();
    }

    @Override
    public void execute() {
        super.execute();
        if(limelight.getLimelightResult().isValid()){
            shooter.setHood(limelight.getLimelightResult().getTa()* RobotConstants.Tuning.TA_TO_ANGLE);
        }
    }
}
