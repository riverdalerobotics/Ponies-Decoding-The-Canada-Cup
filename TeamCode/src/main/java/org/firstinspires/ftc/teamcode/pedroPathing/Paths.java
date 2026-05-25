package org.firstinspires.ftc.teamcode.pedroPathing;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;

public class Paths {

    public static class Blue12BallPath {
        public PathChain ShootFirstset;
        public PathChain Intake2ndsetPt1;
        public PathChain Intake2ndsetPt2;
        public PathChain Gate;
        public PathChain Shoot2ndset;
        public PathChain Intake3rdset;
        public PathChain Shoot3rdset;
        public PathChain Intake4thset;
        public PathChain Shoot4thset;

        public Blue12BallPath(Follower follower) {
            ShootFirstset = follower.pathBuilder().addPath(
                            new BezierLine(
                                    new Pose(19.687, 113.600),

                                    new Pose(40.000, 100.000)
                            )
                    ).setLinearHeadingInterpolation(Math.toRadians(90), Math.toRadians(135))

                    .build();

            Intake2ndsetPt1 = follower.pathBuilder().addPath(
                            new BezierCurve(
                                    new Pose(40.000, 100.000),
                                    new Pose(58.600, 99.252),
                                    new Pose(40.643, 85.217)
                            )
                    ).setLinearHeadingInterpolation(Math.toRadians(135), Math.toRadians(180))

                    .build();

            Intake2ndsetPt2 = follower.pathBuilder().addPath(
                            new BezierCurve(
                                    new Pose(40.643, 85.217),
                                    new Pose(36.104, 82.226),
                                    new Pose(13.322, 83.374)
                            )
                    ).setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(180))

                    .build();

            Gate = follower.pathBuilder().addPath(
                            new BezierCurve(
                                    new Pose(13.322, 83.374),
                                    new Pose(24.465, 74.430),
                                    new Pose(16.400, 70.461)
                            )
                    ).setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(90))

                    .build();

            Shoot2ndset = follower.pathBuilder().addPath(
                            new BezierCurve(
                                    new Pose(16.400, 70.461),
                                    new Pose(33.330, 78.804),
                                    new Pose(50.000, 96.000)
                            )
                    ).setLinearHeadingInterpolation(Math.toRadians(90), Math.toRadians(135))

                    .build();

            Intake3rdset = follower.pathBuilder().addPath(
                            new BezierCurve(
                                    new Pose(50.000, 96.000),
                                    new Pose(78.135, 59.974),
                                    new Pose(11.696, 58.870)
                            )
                    ).setLinearHeadingInterpolation(Math.toRadians(135), Math.toRadians(180))

                    .build();

            Shoot3rdset = follower.pathBuilder().addPath(
                            new BezierCurve(
                                    new Pose(11.696, 58.870),
                                    new Pose(41.630, 69.757),
                                    new Pose(53.548, 87.652)
                            )
                    ).setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(135))

                    .build();

            Intake4thset = follower.pathBuilder().addPath(
                            new BezierCurve(
                                    new Pose(53.548, 87.652),
                                    new Pose(89.017, 30.622),
                                    new Pose(9.948, 34.861)
                            )
                    ).setLinearHeadingInterpolation(Math.toRadians(135), Math.toRadians(180))

                    .build();

            Shoot4thset = follower.pathBuilder().addPath(
                            new BezierCurve(
                                    new Pose(9.948, 34.861),
                                    new Pose(28.948, 79.865),
                                    new Pose(53.635, 87.235)
                            )
                    ).setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(135))

                    .build();
        }
        public Pose getStartPose(){
            return new Pose(19.687, 113.600, Math.toRadians(90));
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
