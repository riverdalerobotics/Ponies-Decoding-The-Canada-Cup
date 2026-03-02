package org.firstinspires.ftc.teamcode.Commands;

import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.arcrobotics.ftclib.command.CommandBase;
import com.bylazar.telemetry.TelemetryManager;

import org.firstinspires.ftc.teamcode.RoadRunner.MecanumDrive;
import org.firstinspires.ftc.teamcode.Subsystems.ChassisSubsystem;

public class ChassisDefaultAutoRR extends CommandBase {
    MecanumDrive chassis;
    TelemetryManager telemetry;
    /**
     * Makes the chassis stay still for auto
     * */
    public ChassisDefaultAutoRR(MecanumDrive chassis, TelemetryManager telemetry){
        this.chassis = chassis;
        this.telemetry = telemetry;
        addRequirements(chassis);
    }

    @Override
    public void execute() {
        super.execute();
        chassis.setDrivePowers(new PoseVelocity2d(new Vector2d(0,0),0));
        telemetry.addLine("Chassis auto default is running");

    }
}
