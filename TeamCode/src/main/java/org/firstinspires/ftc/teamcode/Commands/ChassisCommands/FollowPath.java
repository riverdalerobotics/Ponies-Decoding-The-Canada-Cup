package org.firstinspires.ftc.teamcode.Commands.ChassisCommands;

import com.pedropathing.follower.Follower;
import com.pedropathing.paths.Path;
import com.pedropathing.paths.PathChain;
import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.ChassisSubsystem;

public class FollowPath extends CommandBase {
    Follower follower;
    PathChain pathChain;
    public FollowPath(Follower follower, PathChain path){
        this.follower = follower;
        this.pathChain = path;
    }

    @Override
    public void initialize() {
        super.initialize();
        follower.followPath(pathChain);
    }

    @Override
    public boolean isFinished() {
        return !follower.isBusy();
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
        follower.holdPoint(pathChain.endPose());
    }
}
