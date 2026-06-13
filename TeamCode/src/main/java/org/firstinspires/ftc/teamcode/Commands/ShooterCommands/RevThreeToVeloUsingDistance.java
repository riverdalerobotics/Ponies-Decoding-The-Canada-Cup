package org.firstinspires.ftc.teamcode.Commands.ShooterCommands;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.seattlesolvers.solverslib.command.CommandBase;
import com.seattlesolvers.solverslib.controller.PIDFController;

import org.firstinspires.ftc.teamcode.RobotConstants;
import org.firstinspires.ftc.teamcode.Subsystems.ChassisSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LimelightSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.ShooterSubsystem;

public class RevThreeToVeloUsingDistance extends CommandBase {
    PIDFController snapPID, cracklePID, popPID;
    LimelightSubsystem limelight;
    ShooterSubsystem snap, crackle, pop;
    ChassisSubsystem chassis;
    Follower follower;
    boolean autoStop;
    double distance;
    char colour;
    double offset;
    public RevThreeToVeloUsingDistance(ShooterSubsystem snap, ShooterSubsystem crackle, ShooterSubsystem pop, LimelightSubsystem limelight, ChassisSubsystem chassis, char colour){
        this.snap = snap;
        this.crackle = crackle;
        this.pop = pop;
        this.chassis = chassis;
        this.colour = colour;
        this.limelight = limelight;
        addRequirements(snap, crackle, pop);
        snapPID = new PIDFController(RobotConstants.Tuning.SHOOTER_COEFFICIENTS);
        cracklePID = new PIDFController(RobotConstants.Tuning.SHOOTER_COEFFICIENTS);
        popPID = new PIDFController(RobotConstants.Tuning.SHOOTER_COEFFICIENTS);
    }
    public RevThreeToVeloUsingDistance(ShooterSubsystem snap, ShooterSubsystem crackle, ShooterSubsystem pop, LimelightSubsystem limelight, ChassisSubsystem chassis, char colour, boolean autoStop){
        this.snap = snap;
        this.crackle = crackle;
        this.pop = pop;
        this.limelight = limelight;
        this.chassis = chassis;
        this.colour = colour;
        addRequirements(snap, crackle, pop);
        snapPID = new PIDFController(RobotConstants.Tuning.SHOOTER_COEFFICIENTS);
        cracklePID = new PIDFController(RobotConstants.Tuning.SHOOTER_COEFFICIENTS);
        popPID = new PIDFController(RobotConstants.Tuning.SHOOTER_COEFFICIENTS);
        this.autoStop = autoStop;
    }
    public RevThreeToVeloUsingDistance(ShooterSubsystem snap, ShooterSubsystem crackle, ShooterSubsystem pop, LimelightSubsystem limelight, Follower follower, char colour, boolean autoStop){
        this.snap = snap;
        this.crackle = crackle;
        this.pop = pop;
        this.limelight = limelight;
        this.follower = follower;
        this.colour = colour;
        addRequirements(snap, crackle, pop);
        snapPID = new PIDFController(RobotConstants.Tuning.SHOOTER_COEFFICIENTS);
        cracklePID = new PIDFController(RobotConstants.Tuning.SHOOTER_COEFFICIENTS);
        popPID = new PIDFController(RobotConstants.Tuning.SHOOTER_COEFFICIENTS);
        this.autoStop = autoStop;
    }

    @Override
    public void initialize() {
        super.initialize();
        snapPID.setTolerance(RobotConstants.Tuning.SHOOTER_TOLERANCE[1]);
        cracklePID.setTolerance(RobotConstants.Tuning.SHOOTER_TOLERANCE[1]);
        popPID.setTolerance(RobotConstants.Tuning.SHOOTER_TOLERANCE[1]);
        double setpoint = RobotConstants.Teleop.CLOSE_SHOT_TELEOP;
        if(colour =='r'){
            offset = 144;
        } else{
            offset = 0;
        }
        if(limelight.getLimelightResult().isValid()){
            if(limelight.getLimelightResult().getTa()<RobotConstants.Teleop.CLOSE_SHOT_THRESHOLD){
                setpoint = RobotConstants.Teleop.FAR_SHOT;
            }else{
                setpoint = RobotConstants.Teleop.CLOSE_SHOT_TELEOP;
            }

        }
        snapPID.setSetPoint(setpoint);
        cracklePID.setSetPoint(setpoint);
        popPID.setSetPoint(setpoint);
    }

    @Override
    public void execute() {
        super.execute();
        snapPID.setSetPoint(RobotConstants.Teleop.FAR_SHOT);
        cracklePID.setSetPoint(RobotConstants.Teleop.FAR_SHOT);
        popPID.setSetPoint(RobotConstants.Teleop.FAR_SHOT);
        if(chassis == null){
            distance = follower.getPose().distanceFrom(new Pose(144, offset));
        }
        else{
            distance = Math.sqrt(Math.pow(chassis.getOtos().getPosition().x - offset, 2) + Math.pow(chassis.getOtos().getPosition().y - 144, 2))-18;

        }
        if(limelight.getLimelightResult().isValid()){
            snap.setHood(limelight.getLimelightResult().getTa()*RobotConstants.Tuning.TA_TO_ANGLE);
            crackle.setHood(limelight.getLimelightResult().getTa()*RobotConstants.Tuning.TA_TO_ANGLE);
            pop.setHood(limelight.getLimelightResult().getTa()*RobotConstants.Tuning.TA_TO_ANGLE);
        }
        double setpoint = 6.65054*distance + 1018.43604;
        snapPID.setSetPoint(setpoint);
        cracklePID.setSetPoint(setpoint);
        popPID.setSetPoint(setpoint);
        snap.setShooterMotor(RobotConstants.clamp(snapPID.calculate(snap.getSpeed()), 0, 1));
        snap.setpoint = setpoint;
        crackle.setShooterMotor(RobotConstants.clamp(cracklePID.calculate(crackle.getSpeed()), 0, 1));
        crackle.setpoint = setpoint;
        pop.setShooterMotor(RobotConstants.clamp(popPID.calculate(pop.getSpeed()), 0, 1));
        pop.setpoint = setpoint;
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
//        if(autoStop){
//            snap.setShooterMotor(0);
//            crackle.setShooterMotor(0);
//            pop.setShooterMotor(0);
//        }
    }

    @Override
    public boolean isFinished() {
        return autoStop &&
                Math.abs(snap.getSpeed()-snap.setpoint)< RobotConstants.Tuning.SHOOTER_TOLERANCE[1] &&
                Math.abs(crackle.getSpeed()-crackle.setpoint)< RobotConstants.Tuning.SHOOTER_TOLERANCE[1]&&
                Math.abs(pop.getSpeed()-pop.setpoint)< RobotConstants.Tuning.SHOOTER_TOLERANCE[1];
    }
}
