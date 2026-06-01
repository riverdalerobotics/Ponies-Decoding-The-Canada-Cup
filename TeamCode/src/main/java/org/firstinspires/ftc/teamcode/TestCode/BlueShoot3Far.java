package org.firstinspires.ftc.teamcode.TestCode;

import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.ParallelCommandGroup;
import com.seattlesolvers.solverslib.command.ParallelDeadlineGroup;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.Commands.ChassisCommands.FollowPath;
import org.firstinspires.ftc.teamcode.Commands.IntakeCommands.IntakeDefaultCommand;
import org.firstinspires.ftc.teamcode.Commands.LimelightSees;
import org.firstinspires.ftc.teamcode.Commands.ShooterCommands.FeedShooter;
import org.firstinspires.ftc.teamcode.Commands.ShooterCommands.RevThreeToVelo;
import org.firstinspires.ftc.teamcode.Commands.ShooterCommands.RunNoPIDF;
import org.firstinspires.ftc.teamcode.Commands.ShooterCommands.ShooterDefaultCommand;
import org.firstinspires.ftc.teamcode.RobotConstants;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LimelightSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;
import org.firstinspires.ftc.teamcode.pedroPathing.Paths;

@Autonomous (group = "Test", name = "Blue Shoot 3 Far")
public class BlueShoot3Far extends CommandOpMode {
    Follower follower;
    Paths.BlueShoot3FarPath path;

    LimelightSubsystem limelight;

    ShooterSubsystem snap, crackle, pop;
    ShooterDefaultCommand snapDefault, crackleDefault, popDefault;

    SequentialCommandGroup shootGroup;
    ParallelCommandGroup intakeGroup;

    @Override
    public void initialize(){
        follower = Constants.createFollower(hardwareMap);
        path = new Paths.BlueShoot3FarPath(follower);

        limelight = new LimelightSubsystem(hardwareMap);

        snap = new ShooterSubsystem(RobotConstants.Hardware.SNAP, hardwareMap);
        snapDefault = new ShooterDefaultCommand(snap, limelight);
        snap.setDefaultCommand(snapDefault);

        crackle = new ShooterSubsystem(RobotConstants.Hardware.CRACKLE, hardwareMap);
        crackleDefault = new ShooterDefaultCommand(crackle, limelight);
        crackle.setDefaultCommand(crackleDefault);

        pop = new ShooterSubsystem(RobotConstants.Hardware.CRACKLE, hardwareMap);
        popDefault = new ShooterDefaultCommand(pop, limelight);
        pop.setDefaultCommand(popDefault);

        shootGroup = new SequentialCommandGroup(
                new RevThreeToVelo(snap, crackle, pop, limelight, true),
                new ParallelDeadlineGroup(
                        new WaitCommand(RobotConstants.Teleop.SHOOTER_TIMER),
                        new RevThreeToVelo(snap, crackle, pop, limelight),
                        new FeedShooter(snap),
                        new FeedShooter(crackle),
                        new FeedShooter(pop)

                ),
                new RunNoPIDF(snap, crackle, pop, 0)
        );

        schedule(new SequentialCommandGroup(
                new ParallelDeadlineGroup(
                        new LimelightSees(limelight),
                        new FollowPath(follower, path.TurnToGoal)
                ),
                shootGroup
                )

        );

    }

    @Override
    public void run(){
        super.run();
    }
}
