package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.RobotConstants;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.ShooterSubsystem;

public class IntakeAndRevCommand extends CommandBase {
    IntakeSubsystem intake;
    public IntakeAndRevCommand(IntakeSubsystem intake){
        this.intake = intake;

        addRequirements(intake);
    }

    @Override
    public void initialize() {
        super.initialize();

    }

    @Override
    public void execute() {
        super.execute();
        intake.spinIntake(RobotConstants.Teleop.INTAKE_SPEED);

    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
        intake.spinIntake(0);
    }
}
