package org.firstinspires.ftc.teamcode.pedroPathing;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;

public class Paths {

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






