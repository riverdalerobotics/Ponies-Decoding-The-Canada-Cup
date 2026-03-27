package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;

public class ShooterSubsystem extends SubsystemBase {
    public Motor shooterMotor;
    public ServoEx hood, lifter;
    public RevColorSensorV3 colourSensor;
    public ShooterSubsystem(String[] shooter, HardwareMap hardwareMap){
            this.shooterMotor = new Motor(hardwareMap, shooter[0]);

    }
}
