package org.firstinspires.ftc.teamcode.pedroPathing;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;

import org.firstinspires.ftc.teamcode.RobotConstants;

public class Paths {

    public static class BlueShoot3FarPath {
        public PathChain TurnToGoal;

        public BlueShoot3FarPath(Follower follower) {
            TurnToGoal = follower.pathBuilder()
                    .addPath(
                            new BezierLine(
                                    new Pose(56.000, 8.000),
                                    new Pose(56.520, 8.010)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(90), Math.toRadians(180))
                    .build();
        }
    }

    public static class Red3BallCloseShotPath {
        public PathChain ShootPreLoad;

        public Red3BallCloseShotPath(Follower follower) {
            ShootPreLoad = follower.pathBuilder()
                    .addPath(
                            new BezierLine(
                                    new Pose(119.141, 118.113),
                                    new Pose(109.777, 108.749)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(90), Math.toRadians(55))
                    .build();
        }
    }

    public static class Red12BallPath {
        public PathChain ShootPreLoad;
        public PathChain Intake2ndLine;
        public PathChain Gate;
        public PathChain Shoot2ndLine;
        public PathChain Intake1stLine;
        public PathChain Shoot1stLine;
        public PathChain Intake3rdLine;
        public PathChain Shoot3rdLine;
        public PathChain Leave;


        public Red12BallPath(Follower follower) {
            ShootPreLoad = follower.pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(126.000, 109.000),
                                    new Pose(89.347, 94.958),
                                    new Pose(85.925, 71.980)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(90), RobotConstants.getAngleToGoal(new Pose(85.925, 71.980), 'r'))
                    .build();

            Intake2ndLine = follower.pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(85.925, 71.980),
                                    new Pose(84.573, 48.895),
                                    new Pose(127.947, 56.971)
                            )
                    )
                    .setLinearHeadingInterpolation(RobotConstants.getAngleToGoal(new Pose(85.925, 71.980), 'r'), Math.toRadians(0))
                    .build();

            Gate = follower.pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(127.947, 56.971),
                                    new Pose(107.500, 62.223),
                                    new Pose(124.700, 68.500)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(270))
                    .build();

            Shoot2ndLine = follower.pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(124.700, 68.500),
                                    new Pose(91.047, 60.878),
                                    new Pose(86.512, 76.091)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(270), RobotConstants.getAngleToGoal(new Pose(86.512, 76.091), 'r'))
                    .build();

            Intake1stLine = follower.pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(86.512, 76.091),
                                    new Pose(95.320, 76.364),
                                    new Pose(127.457, 83.573)
                            )
                    )
                    .setLinearHeadingInterpolation(RobotConstants.getAngleToGoal(new Pose(86.512, 76.091), 'r'), Math.toRadians(0))
                    .build();

            Shoot1stLine = follower.pathBuilder()
                    .addPath(
                            new BezierLine(
                                    new Pose(127.457, 83.573),
                                    new Pose(103.520, 92.777)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(0), RobotConstants.getAngleToGoal(new Pose(103.520, 92.777), 'r'))
                    .build();

            Intake3rdLine = follower.pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(103.520, 92.777),
                                    new Pose(91.385, 73.351),
                                    new Pose(85.238, 18.189),
                                    new Pose(128.794, 35.681)
                            )
                    )
                    .setLinearHeadingInterpolation(RobotConstants.getAngleToGoal(new Pose(103.520, 92.777), 'r'), Math.toRadians(0))
                    .build();

            Shoot3rdLine = follower.pathBuilder()
                    .addPath(
                            new BezierLine(
                                    new Pose(128.794, 35.681),
                                    new Pose(91.842, 80.578)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(0), RobotConstants.getAngleToGoal(new Pose(91.842, 80.578), 'r'))
                    .build();

            Leave = follower.pathBuilder()
                    .addPath(
                            new BezierLine(
                                    new Pose(91.842, 80.578),
                                    new Pose(119.812, 69.757)
                            )
                    )
                    .setLinearHeadingInterpolation(RobotConstants.getAngleToGoal(new Pose(91.842, 80.578), 'r'), Math.toRadians(90))
                    .build();

        }
        public Pose getStartPos(){
            return new Pose(126.000, 109.000, Math.toRadians(90));
        }
    }


    public static class Blue12BallPath {
        public PathChain ShootPreLoad;
        public PathChain Intake2ndLine;
        public PathChain Gate;
        public PathChain Shoot2ndLine;
        public PathChain Intake1stLine;
        public PathChain Shoot1stLine;
        public PathChain Intake3rdLine;
        public PathChain Shoot3rdLine;
        public PathChain Leave;

        public Blue12BallPath(Follower follower) {
            ShootPreLoad = follower.pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(15.500, 109.000),
                                    new Pose(52.153, 94.958),
                                    new Pose(55.575, 71.980)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(90), RobotConstants.getAngleToGoal(new Pose(55.575, 71.980), 'b'))
                    .build();

            Intake2ndLine = follower.pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(55.575, 71.980),
                                    new Pose(56.927, 48.895),
                                    new Pose(13.553, 56.971)
                            )
                    )
                    .setLinearHeadingInterpolation(RobotConstants.getAngleToGoal(new Pose(55.575, 71.980), 'b'), Math.toRadians(180))
                    .build();

            Gate = follower.pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(13.553, 56.971),
                                    new Pose(34.000, 62.223),
                                    new Pose(16.800, 68.500)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(-90))
                    .build();

            Shoot2ndLine = follower.pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(16.800, 68.500),
                                    new Pose(50.453, 60.878),
                                    new Pose(54.988, 76.091)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(-90), RobotConstants.getAngleToGoal(new Pose(54.988, 76.091), 'b'))
                    .build();

            Intake1stLine = follower.pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(54.988, 76.091),
                                    new Pose(46.180, 76.364),
                                    new Pose(14.043, 83.573)
                            )
                    )
                    .setLinearHeadingInterpolation(RobotConstants.getAngleToGoal(new Pose(54.988, 76.091), 'b'), Math.toRadians(180))
                    .build();

            Shoot1stLine = follower.pathBuilder()
                    .addPath(
                            new BezierLine(
                                    new Pose(14.043, 83.573),
                                    new Pose(37.980, 92.777)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(180), RobotConstants.getAngleToGoal(new Pose(37.980, 92.777), 'b'))
                    .build();

            Intake3rdLine = follower.pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(37.980, 92.777),
                                    new Pose(50.115, 73.351),
                                    new Pose(56.262, 18.189),
                                    new Pose(12.706, 35.681)
                            )
                    )
                    .setLinearHeadingInterpolation(RobotConstants.getAngleToGoal(new Pose(37.980, 92.777), 'b'), Math.toRadians(180))
                    .build();

            Shoot3rdLine = follower.pathBuilder()
                    .addPath(
                            new BezierLine(
                                    new Pose(12.706, 35.681),
                                    new Pose(49.658, 80.578)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(180), RobotConstants.getAngleToGoal(new Pose(49.658, 80.578), 'b'))
                    .build();

            Leave = follower.pathBuilder()
                    .addPath(
                            new BezierLine(
                                    new Pose(49.658, 80.578),
                                    new Pose(21.688, 69.757)
                            )
                    )
                    .setLinearHeadingInterpolation(RobotConstants.getAngleToGoal(new Pose(49.658, 80.578), 'b'), Math.toRadians(90))
                    .build();
        }
        public Pose getStartPos(){
            return new Pose(15.500, 109.000, Math.toRadians(90));
        }
    }

    public static class RedFar6BallPath {
        public PathChain Intake3rdLine;
        public PathChain ShootPreLoad;
        public PathChain Shoot3rdLine;

        public RedFar6BallPath(Follower follower) {
            Intake3rdLine = follower.pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(85.000, 20.000),
                                    new Pose(87.412, 25.985),
                                    new Pose(125.000, 39.000)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(64), Math.toRadians(0))
                    .build();

            ShootPreLoad = follower.pathBuilder()
                    .addPath(
                            new BezierLine(
                                    new Pose(85.000, 9.000),
                                    new Pose(85.000, 20.000)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(90), Math.toRadians(64))
                    .build();

            Shoot3rdLine = follower.pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(125.000, 39.000),
                                    new Pose(104.624, 40.257),
                                    new Pose(89.000, 54.000)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(58.6))
                    .build();
        }
        public Pose getStartPos(){
            return new Pose(85.000, 20.000, Math.toRadians(90));
        }

    }

    public static class BlueFar6BallPath {
        public PathChain Intake3rdLine;
        public PathChain ShootPreLoad;
        public PathChain Shoot3rdLine;

        public BlueFar6BallPath(Follower follower) {
            Intake3rdLine = follower.pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(56.500, 20.000),
                                    new Pose(54.088, 25.985),
                                    new Pose(16.500, 39.000)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(116), Math.toRadians(180))
                    .build();

            ShootPreLoad = follower.pathBuilder()
                    .addPath(
                            new BezierLine(
                                    new Pose(56.500, 9.000),
                                    new Pose(56.500, 20.000)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(90), Math.toRadians(116))
                    .build();

            Shoot3rdLine = follower.pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(16.500, 39.000),
                                    new Pose(36.876, 40.257),
                                    new Pose(52.500, 54.000)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(121.4))
                    .build();
        }
        public Pose getStartPos(){
            return new Pose (56.500, 20.000, Math.toRadians(90));
        }
    }

    public static class TestPath {
        public PathChain Path1;

        public TestPath(Follower follower) {
            Path1 = follower.pathBuilder().addPath(
                            new BezierLine(
                                    new Pose(56.000, 8.000),

                                    new Pose(56.000, 36.000)
                            )
                    ).setLinearHeadingInterpolation(Math.toRadians(90), Math.toRadians(180))

                    .build();
        }
        public Pose getStartPos(){
            return new Pose(56.000, 8.000, Math.toRadians(90));

        }


    }
    }







