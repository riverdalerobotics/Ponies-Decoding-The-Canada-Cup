package org.firstinspires.ftc.teamcode.Commands.ShooterCommands;

import com.seattlesolvers.solverslib.command.CommandBase;
import com.seattlesolvers.solverslib.controller.PIDController;
import com.seattlesolvers.solverslib.controller.PIDFController;

import org.firstinspires.ftc.teamcode.RobotConstants;
import org.firstinspires.ftc.teamcode.Subsystems.LimelightSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.ShooterSubsystem;

public class RevThreeToVelo extends CommandBase {
    PIDFController snapPID, cracklePID, popPID;
    LimelightSubsystem limelight;
    ShooterSubsystem snap, crackle, pop;
    boolean autoStop;
    public RevThreeToVelo(ShooterSubsystem snap, ShooterSubsystem crackle, ShooterSubsystem pop, LimelightSubsystem limelight){
        this.snap = snap;
        this.crackle = crackle;
        this.pop = pop;
        addRequirements(snap, crackle, pop);
        snapPID = new PIDFController(RobotConstants.Tuning.SHOOTER_COEFFICIENTS);
        cracklePID = new PIDFController(RobotConstants.Tuning.SHOOTER_COEFFICIENTS);
        popPID = new PIDFController(RobotConstants.Tuning.SHOOTER_COEFFICIENTS);
    }
    public RevThreeToVelo(ShooterSubsystem snap, ShooterSubsystem crackle, ShooterSubsystem pop, LimelightSubsystem limelight, boolean autoStop){
        this.snap = snap;
        this.crackle = crackle;
        this.pop = pop;
        addRequirements(snap, crackle, pop);
        snapPID = new PIDFController(RobotConstants.Tuning.SHOOTER_COEFFICIENTS);
        cracklePID = new PIDFController(RobotConstants.Tuning.SHOOTER_COEFFICIENTS);
        popPID = new PIDFController(RobotConstants.Tuning.SHOOTER_COEFFICIENTS);
        this.autoStop = autoStop;
    }

    @Override
    public void initialize() {
        super.initialize();
        snapPID.setTolerance(RobotConstants.Tuning.SHOOTER_TOLERANCE[0]);
        cracklePID.setTolerance(RobotConstants.Tuning.SHOOTER_TOLERANCE[0]);
        popPID.setTolerance(RobotConstants.Tuning.SHOOTER_TOLERANCE[0]);
        if(limelight.getLimelightResult().isValid()){
            snapPID.setSetPoint(limelight.getShooterSpeedUsingLL());
            cracklePID.setSetPoint(limelight.getShooterSpeedUsingLL());
            popPID.setSetPoint(limelight.getShooterSpeedUsingLL());
        }else{
            snapPID.setSetPoint(RobotConstants.Teleop.FAR_SHOT);
            cracklePID.setSetPoint(RobotConstants.Teleop.FAR_SHOT);
            popPID.setSetPoint(RobotConstants.Teleop.FAR_SHOT);
        }
    }

    @Override
    public void execute() {
        super.execute();
        if(limelight.getLimelightResult().isValid()){
            snap.setHood(limelight.getLimelightResult().getTa()*RobotConstants.Tuning.TA_TO_ANGLE);
            crackle.setHood(limelight.getLimelightResult().getTa()*RobotConstants.Tuning.TA_TO_ANGLE);
            pop.setHood(limelight.getLimelightResult().getTa()*RobotConstants.Tuning.TA_TO_ANGLE);
            snapPID.setSetPoint(limelight.getShooterSpeedUsingLL());
            cracklePID.setSetPoint(limelight.getShooterSpeedUsingLL());
            popPID.setSetPoint(limelight.getShooterSpeedUsingLL());
        }else{
            snapPID.setSetPoint(RobotConstants.Teleop.FAR_SHOT);
            cracklePID.setSetPoint(RobotConstants.Teleop.FAR_SHOT);
            popPID.setSetPoint(RobotConstants.Teleop.FAR_SHOT);
        }
        snap.setShooterMotor(RobotConstants.clamp(snapPID.calculate(snap.getSpeed()), 0, 1));
        crackle.setShooterMotor(RobotConstants.clamp(cracklePID.calculate(snap.getSpeed()), 0, 1));
        pop.setShooterMotor(RobotConstants.clamp(popPID.calculate(snap.getSpeed()), 0, 1));
    }

    @Override
    public boolean isFinished() {
        return autoStop && snapPID.atSetPoint() && cracklePID.atSetPoint() && popPID.atSetPoint();
    }
}
