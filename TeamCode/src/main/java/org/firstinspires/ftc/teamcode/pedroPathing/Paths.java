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

        public Red12BallPath(Follower follower) {
            ShootPreLoad = follower.pathBuilder()
                    .addPath(
                            new BezierLine(
                                    new Pose(125.000, 113.000),
                                    new Pose(91.301, 86.200)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(90), Math.toRadians(51))
                    .build();

            Intake2ndLine = follower.pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(91.301, 86.200),
                                    new Pose(63.244, 51.496),
                                    new Pose(127.613, 58.330)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(50), Math.toRadians(0))
                    .build();

            Gate = follower.pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(127.613, 58.330),
                                    new Pose(116.332, 64.824),
                                    new Pose(124.103, 65.179)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(270))
                    .build();

            Shoot2ndLine = follower.pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(124.103, 65.179),
                                    new Pose(85.498, 67.294),
                                    new Pose(85.818, 81.640)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(270), Math.toRadians(49))
                    .build();

            Intake1stLine = follower.pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(85.818, 81.640),
                                    new Pose(95.320, 76.364),
                                    new Pose(127.457, 83.573)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(49), Math.toRadians(0))
                    .build();

            Shoot1stLine = follower.pathBuilder()
                    .addPath(
                            new BezierLine(
                                    new Pose(127.457, 83.573),
                                    new Pose(96.757, 97.112)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(48))
                    .build();

            Intake3rdLine = follower.pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(96.757, 97.112),
                                    new Pose(85.238, 56.940),
                                    new Pose(83.157, 29.634),
                                    new Pose(127.233, 34.641)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(48), Math.toRadians(0))
                    .build();

            Shoot3rdLine = follower.pathBuilder()
                    .addPath(
                            new BezierLine(
                                    new Pose(127.233, 34.641),
                                    new Pose(92.189, 83.526)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(52))
                    .build();
        }
    }
//    public static class Red12BallPath {
//
//        public PathChain ShootPreload;
//        public PathChain Intake2ndLine;
//        public PathChain Gate;
//        public PathChain Shoot2ndLine;
//        public PathChain Intake1stLine;
//        public PathChain Shoot1stLine;

//        public Red12BallPath(Follower follower) {
//            ShootPreload = follower
//                    .pathBuilder()
//                    .addPath(
//                            new BezierLine(new Pose(125.000, 113.000), new Pose(105.000, 102.529))
//                    )
//                    .setLinearHeadingInterpolation(Math.toRadians(90), Math.toRadians(45))
//                    .build();
//
//            Intake2ndLine = follower
//                    .pathBuilder()
//                    .addPath(
//                            new BezierCurve(
//                                    new Pose(105.000, 102.529),
//                                    new Pose(83.294, 84.529),
//                                    new Pose(84.176, 60.000),
//                                    new Pose(126.882, 59.471)
//                            )
//                    )
//                    .setLinearHeadingInterpolation(Math.toRadians(40), Math.toRadians(0))
//                    .build();
//
//            Gate = follower
//                    .pathBuilder()
//                    .addPath(
//                            new BezierCurve(
//                                    new Pose(126.882, 59.471),
//                                    new Pose(112.941, 68.647),
//                                    new Pose(127.765, 67.588)
//                            )
//                    )
//                    .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(270))
//                    .build();
//
//            Shoot2ndLine = follower
//                    .pathBuilder()
//                    .addPath(
//                            new BezierCurve(
//                                    new Pose(127.765, 67.588),
//                                    new Pose(88.235, 57.176),
//                                    new Pose(77.647, 80.118)
//                            )
//                    )
//                    .setLinearHeadingInterpolation(Math.toRadians(270), Math.toRadians(45))
//                    .build();
//
//            Intake1stLine = follower
//                    .pathBuilder()
//                    .addPath(
//                            new BezierLine(new Pose(77.647, 80.118), new Pose(127.588, 83.824))
//                    )
//                    .setLinearHeadingInterpolation(Math.toRadians(45), Math.toRadians(0))
//                    .build();
//
//            Shoot1stLine = follower
//                    .pathBuilder()
//                    .addPath(
//                            new BezierLine(new Pose(127.588, 83.824), new Pose(88.765, 88.765))
//                    )
//                    .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(45))
//                    .build();
//        }
//    }



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







