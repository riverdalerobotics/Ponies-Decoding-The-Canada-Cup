package org.firstinspires.ftc.teamcode.TestCode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.seattlesolvers.solverslib.command.CommandOpMode;

import org.firstinspires.ftc.teamcode.RobotConstants;
/*
* This is a simple op mode that runs one motor at a time depending on which button is clicked, used
* for getting chassis names for constants and the ports.
* */
@TeleOp (name="setting up chassis")
public class SettingUpChassisThing extends CommandOpMode {
    DcMotor fr, fl, br, bl;

    @Override
    public void initialize() {
        fr = hardwareMap.get(DcMotor.class, RobotConstants.Hardware.FRONT_RIGHT_MOTOR);
        br = hardwareMap.get(DcMotor.class, RobotConstants.Hardware.BACK_RIGHT_MOTOR);
        fl = hardwareMap.get(DcMotor.class, RobotConstants.Hardware.FRONT_LEFT_MOTOR);
        bl = hardwareMap.get(DcMotor.class, RobotConstants.Hardware.BACK_LEFT_MOTOR);

        telemetry.addLine("Figuring out which motor is which");
        telemetry.update();
    }

    @Override
    public void run() {
        super.run();

        telemetry.addLine("Press triangle/Y for front left " +
                "\nPress Circle/B to run front right " +
                "\n Press Cross/A to run back Right " +
                "\nPress Square/X to run back left");

        telemetry.addLine("Ensure each motor is spinning \n" +
                "forward and the right one is spinning!");

        if(gamepad1.y){
            fl.setPower(1);
        }
        if(gamepad1.b){
            fr.setPower(1);
        }
        if(gamepad1.a){
            br.setPower(1);
        }
        if(gamepad1.x){
            bl.setPower(1);
        }
    }
}
