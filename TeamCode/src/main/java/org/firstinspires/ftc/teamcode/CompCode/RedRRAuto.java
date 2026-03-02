package org.firstinspires.ftc.teamcode.CompCode;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.ParallelDeadlineGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Commands.ChassisDefaultAutoRR;
import org.firstinspires.ftc.teamcode.Commands.ChassisDefaultFEILDCommand;
import org.firstinspires.ftc.teamcode.Commands.ChassisLookToAprilTagInAutoBlueRR;
import org.firstinspires.ftc.teamcode.Commands.FollowPath;
import org.firstinspires.ftc.teamcode.Commands.IntakeAndRevCommand;
import org.firstinspires.ftc.teamcode.Commands.IntakeCommand;
import org.firstinspires.ftc.teamcode.Commands.IntakeDefaultCommand;
import org.firstinspires.ftc.teamcode.Commands.IntakeFeeding;
import org.firstinspires.ftc.teamcode.Commands.RevToVeloUsingPID;
import org.firstinspires.ftc.teamcode.Commands.ShootAllThree;
import org.firstinspires.ftc.teamcode.Commands.ShooterDefaultCommand;
import org.firstinspires.ftc.teamcode.Commands.Timer;
import org.firstinspires.ftc.teamcode.RoadRunner.MecanumDrive;
import org.firstinspires.ftc.teamcode.RobotConstants;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LLsubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.ShooterSubsystem;

import java.util.List;

@Autonomous(group = "COMP Auto", name = "Red 12 Ball")
public class RedRRAuto extends CommandOpMode {
    LLsubsystem limelight;
    ShooterSubsystem snap, crackle, pop;
    IntakeSubsystem intake;
    ChassisDefaultFEILDCommand chassisDefault;
    IntakeDefaultCommand intakeDefaultCommand;
    ShooterDefaultCommand snapDefault, crackleDefault, popDefault;
    GamepadEx gamepad;
    TelemetryManager telemetryM;
    MecanumDrive drive;
    Pose2d start, shoot, intakeOne, intakeTwo, gate, intakeThree;
    Action pathOne, pathTwo,pathThree,pathFour, pathFive, pathSix, pathSeven, pathEight;
    ChassisDefaultAutoRR chassisDefaultAutoRR;
    boolean run = true;



    @Override
    public void initialize(){
        List<LynxModule> allHubs = hardwareMap.getAll(LynxModule.class);
        telemetryM = PanelsTelemetry.INSTANCE.getTelemetry();
        limelight = new LLsubsystem(hardwareMap);
                //Shooter Initialization
        snap = new ShooterSubsystem(hardwareMap, telemetryM, RobotConstants.Hardware.SNAP);
        crackle = new ShooterSubsystem(hardwareMap, telemetryM, RobotConstants.Hardware.CRACKLE);
        pop = new ShooterSubsystem(hardwareMap, telemetryM, RobotConstants.Hardware.POP);

        crackleDefault = new ShooterDefaultCommand(crackle);
        popDefault = new ShooterDefaultCommand(pop);
        snapDefault = new ShooterDefaultCommand(snap);
        //Intake Initialization
        intake = new IntakeSubsystem(hardwareMap, telemetryM);
        intakeDefaultCommand = new IntakeDefaultCommand(intake);
        start = new Pose2d(-65, 38, Math.toRadians(180));
        shoot = new Pose2d(new Vector2d(-27,27),Math.toRadians(150));
        gate = new Pose2d(new Vector2d(0,55),Math.toRadians(180));
        intakeOne =new Pose2d(-9,55,Math.toRadians(90));
        intakeTwo = new Pose2d(new Vector2d(10, 53), Math.toRadians(90));
        intakeThree = new Pose2d(new Vector2d(33, 53), Math.toRadians(90));
        drive = new MecanumDrive(hardwareMap, start);
        drive.setPosition(start);

        pathOne = drive.actionBuilder(new Pose2d(-61, 38, Math.toRadians(180)))
                .strafeToLinearHeading(new Vector2d(shoot.position.x, shoot.position.y), shoot.heading)
                .build();
        pathTwo = drive.actionBuilder(shoot)
                .strafeToLinearHeading(new Vector2d(-20,18),Math.toRadians(90))
                .strafeToConstantHeading(new Vector2d(-20, 60))
                .strafeToLinearHeading(new Vector2d(-15, 37), Math.toRadians(180))
                .strafeToConstantHeading(new Vector2d(-11, 55))
        .build();
        pathThree = drive.actionBuilder(intakeOne)
                .strafeToLinearHeading(new Vector2d(shoot.position.x, shoot.position.y), shoot.heading)
                .build();
        pathFour = drive.actionBuilder(shoot)
                .strafeToLinearHeading(new Vector2d(10,12), Math.toRadians(90))
                .strafeToConstantHeading(new Vector2d(10, 63)).
                build();
        pathFive = drive.actionBuilder(intakeTwo)
                .strafeToLinearHeading(new Vector2d(12,20), Math.toRadians(90))
                .strafeToLinearHeading(new Vector2d(shoot.position.x, shoot.position.y), shoot.heading)
                .build();
        pathSix = drive.actionBuilder(shoot)
                .strafeToLinearHeading(new Vector2d(31,20), Math.toRadians(90))
                .strafeToLinearHeading(new Vector2d(31, 63), Math.toRadians(90)).
                build();
        pathSeven = drive.actionBuilder(intakeThree)
                .strafeToLinearHeading(new Vector2d(20,20), Math.toRadians(90))
                .strafeToLinearHeading(new Vector2d(shoot.position.x, shoot.position.y), shoot.heading)
                .build();
        pathEight = drive.actionBuilder(shoot)
                .strafeToConstantHeading(new Vector2d(-6,30)).build();


        chassisDefaultAutoRR = new ChassisDefaultAutoRR(drive, telemetryM);
        //Trigger Initializations

        //Command based stuff
        register(snap, crackle, pop, intake, drive);
        schedule(snapDefault, crackleDefault, popDefault,chassisDefaultAutoRR, intakeDefaultCommand);
        drive.setDefaultCommand(chassisDefaultAutoRR);
        pop.setDefaultCommand(popDefault);
        crackle.setDefaultCommand(crackleDefault);
        snap.setDefaultCommand(snapDefault);
        intake.setDefaultCommand(intakeDefaultCommand);
        limelight.getLimelight().start();


    }

    @Override
    public void run(){
        CommandScheduler.getInstance().run();

        if (run){schedule(
                new SequentialCommandGroup(
                    new ParallelDeadlineGroup(
                        new FollowPath(drive,telemetryM,pathOne),
                        new RevToVeloUsingPID(snap, telemetry),
                        new RevToVeloUsingPID(crackle, telemetry),
                        new RevToVeloUsingPID(pop, telemetry)
                    ),
                    new ParallelDeadlineGroup(
                            new ChassisLookToAprilTagInAutoBlueRR(drive, limelight, telemetryM, 0),
                            new RevToVeloUsingPID(snap, telemetry),
                            new RevToVeloUsingPID(crackle, telemetry),
                            new RevToVeloUsingPID(pop, telemetry)
                    ),
                    new ShootAllThree(snap, crackle, pop, telemetry),
                    new ParallelDeadlineGroup(
                            new FollowPath(drive,telemetryM,pathTwo),
                            new IntakeCommand(intake, snap, crackle, pop),
                            new IntakeFeeding(snap),
                            new IntakeFeeding(pop)
                    ),
                    new ParallelDeadlineGroup(
                            new FollowPath(drive,telemetryM,pathThree),
                            new SequentialCommandGroup(
                                    new ParallelDeadlineGroup(
                                            new Timer(RobotConstants.BlueAuto.AUTO_TIME),
                                            new IntakeCommand(intake, snap, crackle, pop),
                                            new IntakeFeeding(snap),
                                            new IntakeFeeding(pop)
                                    ),
                                    new ParallelCommandGroup(
                                            new RevToVeloUsingPID(snap, telemetry),
                                            new RevToVeloUsingPID(crackle, telemetry),
                                            new RevToVeloUsingPID(pop, telemetry)),
                                            new IntakeAndRevCommand(intake)

                            )
                    ),
                new ParallelDeadlineGroup(
                        new ChassisLookToAprilTagInAutoBlueRR(drive, limelight, telemetryM, 0),
                        new RevToVeloUsingPID(snap, telemetry),
                        new RevToVeloUsingPID(crackle, telemetry),
                        new RevToVeloUsingPID(pop, telemetry),
                        new IntakeAndRevCommand(intake)
                ),
                    new ShootAllThree(snap, crackle, pop, telemetry, intake),
                    new ParallelDeadlineGroup(
                            new FollowPath(drive,telemetryM,pathFour),
                            new IntakeCommand(intake, snap, crackle, pop)
                    ),
                    new ParallelDeadlineGroup(
                            new FollowPath(drive,telemetryM,pathFive),
                            new SequentialCommandGroup(
                                    new ParallelDeadlineGroup(
                                            new Timer(RobotConstants.BlueAuto.AUTO_TIME),
                                            new IntakeCommand(intake, snap, crackle, pop),
                                            new IntakeFeeding(snap),
                                            new IntakeFeeding(pop)
                                    ),
                                    new ParallelCommandGroup(
                                            new RevToVeloUsingPID(snap, telemetry),
                                            new RevToVeloUsingPID(crackle, telemetry),
                                            new RevToVeloUsingPID(pop, telemetry),
                                            new IntakeAndRevCommand(intake)
                                    )


                            )
                    ),
                        new ParallelDeadlineGroup(
                                new ChassisLookToAprilTagInAutoBlueRR(drive, limelight, telemetryM, 0),
                                new RevToVeloUsingPID(snap, telemetry),
                                new RevToVeloUsingPID(crackle, telemetry),
                                new RevToVeloUsingPID(pop, telemetry),
                                new IntakeAndRevCommand(intake)
                        ),                    new ShootAllThree(snap, crackle, pop, telemetry, intake),

                    new ParallelDeadlineGroup(
                            new FollowPath(drive,telemetryM,pathSix),
                            new IntakeCommand(intake, snap, crackle, pop),
                            new IntakeFeeding(snap),
                            new IntakeFeeding(pop)
                    ),
                    new ParallelDeadlineGroup(
                            new FollowPath(drive,telemetryM,pathSeven),
                            new SequentialCommandGroup(
                                    new ParallelDeadlineGroup(
                                            new Timer(RobotConstants.BlueAuto.AUTO_TIME),
                                            new IntakeCommand(intake, snap, crackle, pop),
                                            new IntakeFeeding(snap),
                                            new IntakeFeeding(pop)
                                    ),
                                    new ParallelCommandGroup(
                                            new RevToVeloUsingPID(snap, telemetry),
                                            new RevToVeloUsingPID(crackle, telemetry),
                                            new RevToVeloUsingPID(pop, telemetry),
                                            new IntakeAndRevCommand(intake)
                                    )

                            )
                    ),
                        new ParallelDeadlineGroup(
                                new ChassisLookToAprilTagInAutoBlueRR(drive, limelight, telemetryM, 0),
                                new RevToVeloUsingPID(snap, telemetry),
                                new RevToVeloUsingPID(crackle, telemetry),
                                new RevToVeloUsingPID(pop, telemetry),
                                new IntakeAndRevCommand(intake)
                        ),
                        new ShootAllThree(snap, crackle, pop, telemetry, intake),
                    new FollowPath(drive,telemetryM,pathEight)


                )
        );
            run = false;
        }
        telemetry.addData("position", limelight.getLLResults().getBotpose_MT2());
        telemetry.addData("ta", snap.getLLResult().getTa());
        telemetry.update();
        telemetryM.update();
    }
}
