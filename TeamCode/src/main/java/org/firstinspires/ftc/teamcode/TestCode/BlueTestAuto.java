package org.firstinspires.ftc.teamcode.TestCode;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.ParallelDeadlineGroup;
import com.arcrobotics.ftclib.command.ParallelRaceGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitUntilCommand;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathBuilder;
import com.pedropathing.paths.PathChain;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Commands.ChassisDefaultAutoRR;
import org.firstinspires.ftc.teamcode.Commands.FeedShooter;
import org.firstinspires.ftc.teamcode.Commands.FollowPath;
import org.firstinspires.ftc.teamcode.Commands.IntakeCommand;
import org.firstinspires.ftc.teamcode.Commands.IntakeDefaultCommand;
import org.firstinspires.ftc.teamcode.Commands.RevToVeloUsingPIDAUTO;
import org.firstinspires.ftc.teamcode.Commands.Shoot;
import org.firstinspires.ftc.teamcode.Commands.ShootAllThree;
import org.firstinspires.ftc.teamcode.Commands.ShooterDefaultCommand;
import org.firstinspires.ftc.teamcode.Commands.Timer;
import org.firstinspires.ftc.teamcode.RoadRunner.MecanumDrive;
import org.firstinspires.ftc.teamcode.RobotConstants;
import org.firstinspires.ftc.teamcode.Subsystems.ChassisSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

import java.util.List;

@Autonomous(group = "Test Auto", name = "Blue test auto")
public class BlueTestAuto extends CommandOpMode{
    ChassisSubsystem chassis;
    ShooterSubsystem snap, crackle, pop;
    IntakeSubsystem intake;
    Follower follower;
    TelemetryManager telemetryM;
    PathBuilder builder;
    PathChain Shoot, GoToTopIntake, Intaketop3, ShootTwo, GoToBottomIntake, IntakeBottom3,
            ShootThree, GoToMidIntake, MidIntake, PrepForTeleop;
    ShooterDefaultCommand snapDefault, crackleDefault, popDefault;
    IntakeDefaultCommand intakeDefault;
    GamepadEx driver;
    Shoot shootCommand;
    MecanumDrive drive;
    boolean run;
    Pose2d start;
    Action pathOne;
    ChassisDefaultAutoRR chassisDefaultAutoRR;
    MecanumDrive.DriveLocalizer localizer;
//    LLResult result;
    char[] gpp = {'g', 'p', 'p'};
    char[] ppg = {'p', 'p', 'g'};
    char[] pgp = {'p', 'g', 'p'};
    char[] motif = ppg;
    public void buildPaths(){
        Shoot = builder
                .addPath(new BezierLine(new Pose(60.000, 9.348), new Pose(60.000, 84.000)))
                .setLinearHeadingInterpolation(Math.toRadians(90), Math.toRadians(138))
                .build();

        GoToTopIntake = builder
                .addPath(new BezierLine(new Pose(60.000, 84.000), new Pose(43.000, 84.000)))
                .setLinearHeadingInterpolation(Math.toRadians(138), Math.toRadians(180))
                .build();

        Intaketop3 = builder
                .addPath(new BezierLine(new Pose(43.000, 84.000), new Pose(17.000, 84.000)))
                .setTangentHeadingInterpolation()
                .build();

        ShootTwo = builder
                .addPath(
                        new BezierLine(new Pose(17.000, 84.000), new Pose(43.000, 100.000))
                )
                .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(138))
                .build();

        GoToBottomIntake = builder
                .addPath(
                        new BezierLine(new Pose(43.000, 100.000), new Pose(43.000, 35.500))
                )
                .setLinearHeadingInterpolation(Math.toRadians(138), Math.toRadians(180))
                .build();

        IntakeBottom3 = builder
                .addPath(new BezierLine(new Pose(43.000, 35.500), new Pose(17.000, 35.500)))
                .setTangentHeadingInterpolation()
                .build();

        ShootThree = builder
                .addPath(
                        new BezierCurve(
                                new Pose(17.000, 35.500),
                                new Pose(77.370, 28.840),
                                new Pose(43.000, 100.000)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(138))
                .build();

        GoToMidIntake = builder
                .addPath(
                        new BezierLine(new Pose(43.000, 100.000), new Pose(43.000, 60.000))
                )
                .setLinearHeadingInterpolation(Math.toRadians(138), Math.toRadians(180))
                .build();

        MidIntake = builder
                .addPath(new BezierLine(new Pose(43.000, 60.000), new Pose(18.000, 60.000)))
                .setTangentHeadingInterpolation()
                .build();

        PrepForTeleop = builder
                .addPath(
                        new BezierCurve(
                                new Pose(18.000, 60.000),
                                new Pose(32.221, 58.475),
                                new Pose(28.000, 70.000)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(90))
                .build();
    }
    @Override
    public void initialize() {
        start = new Pose2d(-43, 50.5, Math.toRadians(0));
        follower = Constants.createFollower(hardwareMap);
        builder = new PathBuilder(follower);
        telemetryM = PanelsTelemetry.INSTANCE.getTelemetry();
        chassis = new ChassisSubsystem(hardwareMap, telemetryM);

        intake = new IntakeSubsystem(hardwareMap, telemetryM);
        snap = new ShooterSubsystem(hardwareMap, telemetryM, RobotConstants.Hardware.SNAP);
        pop = new ShooterSubsystem(hardwareMap, telemetryM, RobotConstants.Hardware.POP);
        crackle  = new ShooterSubsystem(hardwareMap, telemetryM, RobotConstants.Hardware.CRACKLE);
        snapDefault = new ShooterDefaultCommand(snap);
        crackleDefault = new ShooterDefaultCommand(crackle);
        popDefault = new ShooterDefaultCommand(pop);
        intakeDefault = new IntakeDefaultCommand(intake);
        driver = new GamepadEx(gamepad1);
//        shootCommand = new Shoot(snap, crackle, pop, motif, true);
        buildPaths();
        run = true;
        drive = new MecanumDrive(hardwareMap, start);

        pathOne = drive.actionBuilder(
                start)
                .strafeToLinearHeading(new Vector2d(-24,24),Math.toRadians(135))
                .build();
        chassisDefaultAutoRR = new ChassisDefaultAutoRR(drive, telemetryM);
        register(drive);
        schedule(chassisDefaultAutoRR);
        drive.setDefaultCommand(chassisDefaultAutoRR);
    }

    @Override
    public void run() {
        CommandScheduler.getInstance().run();
        super.run();
//        if(chassis.id == 21){
//            motif = gpp;
//        } else if (chassis.id == 22) {
//            motif = pgp;
//        } else if (chassis.id == 23) {
//            motif = ppg;
//        }
        if(run){
            schedule(new SequentialCommandGroup(
                    new FollowPath(drive,telemetryM,pathOne),
                    new ShootAllThree(snap, crackle, pop, telemetry)
//                    new ParallelDeadlineGroup(
//                        new FollowPath(drive,telemetryM,pathOne),
//                        new RevToVeloUsingPIDAUTO(snap, telemetry),
//                        new RevToVeloUsingPIDAUTO(crackle, telemetry),
//                        new RevToVeloUsingPIDAUTO(pop, telemetry)
//                    ),
//                    new SequentialCommandGroup(new ParallelDeadlineGroup(
//                            new WaitUntilCommand(()->snap.getShooterPID().atSetPoint()&&pop.getShooterPID().atSetPoint()&&crackle.getShooterPID().atSetPoint()),
//                            new RevToVeloUsingPIDAUTO(snap, telemetry),
//                            new RevToVeloUsingPIDAUTO(crackle, telemetry),
//                            new RevToVeloUsingPIDAUTO(pop, telemetry)
//                    ),
//                            new ParallelRaceGroup(
//                                    new RevToVeloUsingPIDAUTO(snap, telemetry, true),
//                                    new RevToVeloUsingPIDAUTO(crackle, telemetry, true),
//                                    new RevToVeloUsingPIDAUTO(pop, telemetry, true),
//                                    new ParallelDeadlineGroup(
//                                            new Timer(RobotConstants.Teleop.HOLD_THE_ARM),
//                                            new FeedShooter(snap)),
//                                    new ParallelDeadlineGroup(
//                                            new Timer(RobotConstants.Teleop.HOLD_THE_ARM),
//                                            new FeedShooter(crackle)),
//                                    new ParallelDeadlineGroup(
//                                            new Timer(RobotConstants.Teleop.HOLD_THE_ARM),
//                                            new FeedShooter(pop))
//                            )
//                    ))
        ));
            run = false;
        }

//
}}
