package com.example.lib;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTest {


        public static void main(String[] args) {
            MeepMeep meepMeep = new MeepMeep(800);

            RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                    // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                    .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15).setDimensions(17.5,17.5)
                    .build();

            myBot.runAction(myBot.getDrive().actionBuilder(
                    new Pose2d(-61, -38, Math.toRadians(180)))
                            .strafeToLinearHeading(new Vector2d(-20,-20),Math.toRadians(150))
                            .strafeToLinearHeading(new Vector2d(-14,-25),Math.toRadians(-90))
                            .strafeToConstantHeading(new Vector2d(-14, -53))
                            .strafeToLinearHeading(new Vector2d(0,-56),Math.toRadians(180))
                            .strafeToLinearHeading(new Vector2d(-20,-20),Math.toRadians(-135))
                            .strafeToLinearHeading(new Vector2d(14,-25), Math.toRadians(-90))
                            .strafeToConstantHeading(new Vector2d(14, -53))
                            .strafeToLinearHeading(new Vector2d(-20,-20),Math.toRadians(-135))
                            .strafeToLinearHeading(new Vector2d(38,-30), Math.toRadians(-90))
                            .strafeToConstantHeading(new Vector2d(38, -53))
                            .strafeToLinearHeading(new Vector2d(-20,-20),Math.toRadians(-135))


                    .build());

            meepMeep.setBackground(
                    MeepMeep.Background.FIELD_DECODE_JUICE_DARK)
                    .setDarkMode(true)
                    .setBackgroundAlpha(0.95f)
                    .addEntity(myBot)
                    .start();
        }
    }
