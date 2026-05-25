package org.firstinspires.ftc.teamcode.TestCode;

import com.bylazar.telemetry.TelemetryManager;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.drivebase.MecanumDrive;
import com.seattlesolvers.solverslib.pedroCommand.FollowPathCommand;

import org.firstinspires.ftc.teamcode.Subsystems.ChassisSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

@Autonomous
public class PedroAutoTest extends CommandOpMode{
    Follower follower;
    ChassisSubsystem chassis;
    TelemetryManager telemetry;
    ShooterSubsystem snap, crackle, pop;
    MecanumDrive drive;

    // Poses - ill move everything to its own file later
    private final Pose startPose = new Pose(-65, -38, Math.toRadians(-180));
    private final Pose poseTwo = new Pose(-27,-27, Math.toRadians(-150));
    private final Pose poseThree = new Pose(-20, -18, Math.toRadians(-90));

    // Path chains
    PathChain scorePreLoaded, scoreFirstIntake;

    public void buildPaths(){ //pre-building paths to use in auto
        scorePreLoaded = follower.pathBuilder()
                .addPath(new BezierLine(startPose, poseTwo))
                .setLinearHeadingInterpolation(startPose.getHeading(), poseTwo.getHeading())
                .build();

        scoreFirstIntake = follower.pathBuilder()
                .addPath(new BezierLine(poseTwo, poseThree))
                .setLinearHeadingInterpolation(poseTwo.getHeading(), poseThree.getHeading())
                .build();
    }

    // Instant Commands



    @Override
    public void initialize(){
        super.reset();

        // Initializing Follower
        follower = Constants.createFollower(hardwareMap);
        follower.setStartingPose(startPose);
        buildPaths();

        // Scheduling auto sequence
        schedule(
                new SequentialCommandGroup(
                        new FollowPathCommand(follower, scorePreLoaded),
                        new FollowPathCommand(follower, scoreFirstIntake)
                )
        );
    }

    @Override
    public void run() {
        super.run();
        follower.update();
    }
}
