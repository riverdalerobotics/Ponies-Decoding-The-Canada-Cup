package org.firstinspires.ftc.teamcode.Commands;

import static org.firstinspires.ftc.teamcode.pedroPathing.Tuning.drawCurrent;
import static org.firstinspires.ftc.teamcode.pedroPathing.Tuning.drawCurrentAndHistory;
import static org.firstinspires.ftc.teamcode.pedroPathing.Tuning.follower;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Trajectory;
import com.acmerobotics.roadrunner.Vector2d;
import com.arcrobotics.ftclib.command.CommandBase;
import com.bylazar.telemetry.TelemetryManager;
import com.pedropathing.paths.PathChain;

import org.firstinspires.ftc.teamcode.RoadRunner.MecanumDrive;
import org.firstinspires.ftc.teamcode.Subsystems.ChassisSubsystem;

public class FollowPath extends CommandBase {
    MecanumDrive chassis;
    TelemetryManager telemetry;
    Action action;
    Boolean done;
    public FollowPath(MecanumDrive chassis, TelemetryManager telemetry, Action action){
        this.chassis = chassis;
        this.telemetry = telemetry;
        this.action = action;
        addRequirements(chassis);
    }

    @Override
    public void initialize() {
        super.initialize();

        telemetry.debug("Following Path, ROBOT IS MOVING");
    }

    @Override
    public void execute() {
        super.execute();
        TelemetryPacket packet = new TelemetryPacket();
        action.preview(packet.fieldOverlay());
        done = !action.run(packet);
        FtcDashboard.getInstance().sendTelemetryPacket(packet);

    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
        chassis.setDrivePowers(new PoseVelocity2d(new Vector2d(0,0),0));
        telemetry.debug("PATH HAS ENDED, pick up the controller");
    }
    @Override
    public boolean isFinished() {
        return done;
    }
}
