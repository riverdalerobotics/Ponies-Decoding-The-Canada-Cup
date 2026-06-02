package org.firstinspires.ftc.teamcode.pedroPathing;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;

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
        public PathChain Gate2;
        public PathChain IntakeFromGate;
        public PathChain ShootGate;


        public Red12BallPath(Follower follower) {
            ShootPreLoad = follower.pathBuilder().addPath(
                            new BezierLine(
                                    new Pose(128.339, 109.870),

                                    new Pose(92.970, 79.939)
                            )
                    ).setLinearHeadingInterpolation(Math.toRadians(90), Math.toRadians(55))

                    .build();

            Intake2ndLine = follower.pathBuilder().addPath(
                            new BezierCurve(
                                    new Pose(92.970, 79.939),
                                    new Pose(87.147, 52.328),
                                    new Pose(127.613, 58.503)
                            )
                    ).setLinearHeadingInterpolation(Math.toRadians(54), Math.toRadians(0))

                    .build();

            Gate = follower.pathBuilder().addPath(
                            new BezierCurve(
                                    new Pose(127.613, 58.503),
                                    new Pose(118.933, 62.223),
                                    new Pose(127.224, 64.659)
                            )
                    ).setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(90))

                    .build();

            Shoot2ndLine = follower.pathBuilder().addPath(
                            new BezierCurve(
                                    new Pose(127.224, 64.659),
                                    new Pose(90.716, 69.381),
                                    new Pose(93.122, 80.388)
                            )
                    ).setLinearHeadingInterpolation(Math.toRadians(90), Math.toRadians(55))

                    .build();

            Intake1stLine = follower.pathBuilder().addPath(
                            new BezierCurve(
                                    new Pose(93.122, 80.388),
                                    new Pose(95.320, 76.364),
                                    new Pose(127.457, 83.573)
                            )
                    ).setLinearHeadingInterpolation(Math.toRadians(54), Math.toRadians(0))

                    .build();

            Shoot1stLine = follower.pathBuilder().addPath(
                            new BezierLine(
                                    new Pose(127.457, 83.573),

                                    new Pose(104.478, 92.521)
                            )
                    ).setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(50))

                    .build();

            Intake3rdLine = follower.pathBuilder().addPath(
                            new BezierCurve(
                                    new Pose(104.478, 92.521),
                                    new Pose(103.910, 84.511),
                                    new Pose(87.146, 21.831),
                                    new Pose(134.863, 35.334)
                            )
                    ).setLinearHeadingInterpolation(Math.toRadians(50), Math.toRadians(0))

                    .build();

            Shoot3rdLine = follower.pathBuilder().addPath(
                            new BezierLine(
                                    new Pose(134.863, 35.334),

                                    new Pose(93.000, 80.107)
                            )
                    ).setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(55))

                    .build();

            Gate2 = follower.pathBuilder().addPath(
                            new BezierLine(
                                    new Pose(93.000, 80.107),

                                    new Pose(128.348, 65.383)
                            )
                    ).setLinearHeadingInterpolation(Math.toRadians(55), Math.toRadians(0))

                    .build();

            IntakeFromGate = follower.pathBuilder().addPath(
                            new BezierCurve(
                                    new Pose(128.348, 65.383),
                                    new Pose(129.470, 58.470),
                                    new Pose(134.313, 54.965)
                            )
                    ).setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(80))

                    .build();

            ShootGate = follower.pathBuilder().addPath(
                            new BezierCurve(
                                    new Pose(134.313, 54.965),
                                    new Pose(121.004, 53.922),
                                    new Pose(95.765, 120.009)
                            )
                    ).setLinearHeadingInterpolation(Math.toRadians(80), Math.toRadians(35))

                    .build();

        }
    }



    public static class Blue12BallPath {


            public PathChain ShootFirst3;
            public PathChain IntakeSecond3Pt1;
            public PathChain IntakeSecond3Pt2;
            public PathChain Gate;
            public PathChain ShootSecond3;
            public PathChain Intake3rdSet;
            public PathChain Shoot3rdSet;
            public PathChain Intake4thSet;
            public PathChain Shoot4thSet;
            public PathChain Gate2;
            public PathChain IntakeFromGate;
            public PathChain ShootFromGate;

            public Blue12BallPath(Follower follower) {
                ShootFirst3 = follower.pathBuilder().addPath(
                                new BezierLine(
                                        new Pose(15, 109),

                                        new Pose(36.035, 97.913)
                                )
                        ).setLinearHeadingInterpolation(Math.toRadians(90), Math.toRadians(135))

                        .build();

                IntakeSecond3Pt1 = follower.pathBuilder().addPath(
                                new BezierCurve(
                                        new Pose(36.035, 97.913),
                                        new Pose(58.600, 99.252),
                                        new Pose(40.643, 85.217)
                                )
                        ).setLinearHeadingInterpolation(Math.toRadians(135), Math.toRadians(180))

                        .build();

                IntakeSecond3Pt2 = follower.pathBuilder().addPath(
                                new BezierCurve(
                                        new Pose(40.643, 85.217),
                                        new Pose(36.104, 82.226),
                                        new Pose(15.200, 84.000)
                                )
                        ).setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(180))

                        .build();

                Gate = follower.pathBuilder().addPath(
                                new BezierCurve(
                                        new Pose(15.200, 84.000),
                                        new Pose(24.465, 74.430),
                                        new Pose(16.400, 70.461)
                                )
                        ).setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(90))

                        .build();

                ShootSecond3 = follower.pathBuilder().addPath(
                                new BezierCurve(
                                        new Pose(16.400, 70.461),
                                        new Pose(33.330, 78.804),
                                        new Pose(50.000, 96.000)
                                )
                        ).setLinearHeadingInterpolation(Math.toRadians(90), Math.toRadians(135))

                        .build();

                Intake3rdSet = follower.pathBuilder().addPath(
                                new BezierCurve(
                                        new Pose(50.000, 96.000),
                                        new Pose(78.135, 59.974),
                                        new Pose(11.696, 58.870)
                                )
                        ).setLinearHeadingInterpolation(Math.toRadians(135), Math.toRadians(180))

                        .build();

                Shoot3rdSet = follower.pathBuilder().addPath(
                                new BezierCurve(
                                        new Pose(11.696, 58.870),
                                        new Pose(41.630, 69.757),
                                        new Pose(54.174, 88.904)
                                )
                        ).setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(135))

                        .build();

                Intake4thSet = follower.pathBuilder().addPath(
                                new BezierCurve(
                                        new Pose(54.174, 88.904),
                                        new Pose(89.017, 30.622),
                                        new Pose(9.948, 34.861)
                                )
                        ).setLinearHeadingInterpolation(Math.toRadians(135), Math.toRadians(180))

                        .build();

                Shoot4thSet = follower.pathBuilder().addPath(
                                new BezierCurve(
                                        new Pose(9.948, 34.861),
                                        new Pose(28.948, 79.865),
                                        new Pose(63.443, 96.417)
                                )
                        ).setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(140))

                        .build();
                Gate2 = follower.pathBuilder().addPath(
                                new BezierLine(
                                        new Pose(53.300, 86.994),

                                        new Pose(12.104, 62.609)
                                )
                        ).setLinearHeadingInterpolation(Math.toRadians(125), Math.toRadians(135))

                        .build();

                IntakeFromGate = follower.pathBuilder().addPath(
                                new BezierCurve(
                                        new Pose(12.104, 62.609),
                                        new Pose(15.765, 59.478),
                                        new Pose(9.043, 58.226)
                                )
                        ).setLinearHeadingInterpolation(Math.toRadians(135), Math.toRadians(100))

                        .build();

                ShootFromGate = follower.pathBuilder().addPath(
                                new BezierCurve(
                                        new Pose(9.043, 58.226),
                                        new Pose(42.565, 78.665),
                                        new Pose(52.226, 107.696)
                                )
                        ).setLinearHeadingInterpolation(Math.toRadians(100), Math.toRadians(150))

                        .build();
            }
        public Pose getStartPose(){
            return new Pose(15, 109, Math.toRadians(90));
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







