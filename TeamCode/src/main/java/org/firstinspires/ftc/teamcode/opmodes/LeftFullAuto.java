package org.firstinspires.ftc.teamcode.opmodes;

import android.util.Log;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.commands.ArmMotorCommand;
import org.firstinspires.ftc.teamcode.commands.CameraDetectCommand;
import org.firstinspires.ftc.teamcode.commands.CammeraCommand;
import org.firstinspires.ftc.teamcode.commands.DetectZoneCommand;
import org.firstinspires.ftc.teamcode.commands.DistanceCommand;
import org.firstinspires.ftc.teamcode.commands.ElevatorMoveCommand;
import org.firstinspires.ftc.teamcode.commands.ElevatorToGroundCommand;
import org.firstinspires.ftc.teamcode.commands.GrabberCloseCommand;
import org.firstinspires.ftc.teamcode.commands.GrabberOpenCommand;
import org.firstinspires.ftc.teamcode.commands.ImuCommand;
import org.firstinspires.ftc.teamcode.commands.TurnCommand;
import org.firstinspires.ftc.teamcode.commands.TurnRightCommand;
import org.firstinspires.ftc.teamcode.commands.VariableDriveDistanceCommand;
import org.firstinspires.ftc.teamcode.commands.VariableStrafeDistanceCommand;
import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.CameraSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DistanceSensorSub;
import org.firstinspires.ftc.teamcode.subsystems.GrabberServoSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ImuSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.TelemetryUpdateSubsystem;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

@Autonomous(name = "Left Full Auto", group = "2")
public class LeftFullAuto extends CommandOpMode {
    private TelemetryUpdateSubsystem telemetryUpdateSubsystem;
    private DistanceSensorSub distanceSensorSub;
    private ArmSubsystem armSubsystem;
    private ImuSubsystem imuSubsystem;
    private MecanumDriveSubsystem mecanumDriveSubsystem;
    private CameraSubsystem cameraSubsystem;
    private GrabberServoSubsystem grabberServoSubsystem;

    private DistanceCommand distanceCommand;
    private ImuCommand imuCommand;

    private VariableDriveDistanceCommand forwards515DriveDistanceCommand;
    private VariableDriveDistanceCommand forwards10DriveDistanceCommand;

    private VariableDriveDistanceCommand backwards10DriveDistanceCommand;
    private VariableDriveDistanceCommand backwards24DriveDistanceCommand;

    private GrabberCloseCommand grabberCommand;
    private GrabberOpenCommand grabberOpenCommand;
    private GrabberCloseCommand grabberCloseCommand;

    private CammeraCommand cammeraCommand;
    private CameraDetectCommand cameraDetectCommand;
    private DetectZoneCommand detectZoneCommand;

    private ElevatorMoveCommand elevatorMoveHighCommand;
    private ElevatorToGroundCommand elevatorToGroundCommand;
    private ElevatorToGroundCommand elevatorToGroundCommand2;
    private ElevatorToGroundCommand elevatorToGroundCommand3;
    private ArmMotorCommand armMotorCommand;

    private TurnCommand turn45Command;
    private TurnCommand turn0Command;

    private VariableStrafeDistanceCommand R24StrafeDistanceCommand;
    private VariableStrafeDistanceCommand L24StrafeDistanceCommand;

    private TurnRightCommand turnRightCommand;

    private SequentialCommandGroup firstTallTowerSequence;
    private ParallelCommandGroup moveForwards515RaiseHigh;
    private ParallelCommandGroup moveBackwards24LowerBottom;
    private BooleanSupplier booleanSupplier;

    static final String Tag = "10868";

    private void commandLogging() {
        CommandScheduler.getInstance().onCommandInitialize(command -> {
            Log.i(Tag, String.format("*** %s *** Initialize *** %s", command.getName(), command.toString()));}
        );
        CommandScheduler.getInstance().onCommandExecute(command -> {
            Log.i(Tag, String.format("*** %s *** Execute *** %s", command.getName(), command.toString()));
        });
        CommandScheduler.getInstance().onCommandFinish(command -> {
            Log.i(Tag, String.format("*** %s *** Finish *** %s", command.getName(), command.toString()));
        });
        CommandScheduler.getInstance().onCommandInterrupt(command -> {
            Log.i(Tag, String.format("*** %s *** Interrupt *** %s", command.getName(), command.toString()));
        });
    }

    @Override
    public void initialize() {
        booleanSupplier = new BooleanSupplier() {
            @Override
            public boolean getAsBoolean() {
                return false;
            }
        };
        imuSubsystem = new ImuSubsystem(hardwareMap, telemetry);
        armSubsystem = new ArmSubsystem(hardwareMap, telemetry);
        mecanumDriveSubsystem = new MecanumDriveSubsystem(hardwareMap, telemetry, imuSubsystem);
        grabberServoSubsystem = new GrabberServoSubsystem(hardwareMap, telemetry);
        cameraSubsystem = new CameraSubsystem(hardwareMap, telemetry);
        distanceSensorSub = new DistanceSensorSub(hardwareMap, telemetry);
        telemetryUpdateSubsystem = new TelemetryUpdateSubsystem(telemetry);

        forwards515DriveDistanceCommand = new VariableDriveDistanceCommand(mecanumDriveSubsystem, imuSubsystem, telemetry, 0.3, 0.3, 0, 51.5);
        forwards10DriveDistanceCommand = new VariableDriveDistanceCommand(mecanumDriveSubsystem, imuSubsystem, telemetry, 0.3, 0.3, 0, 10);

        backwards10DriveDistanceCommand = new VariableDriveDistanceCommand(mecanumDriveSubsystem, imuSubsystem, telemetry, -0.3, 0.3, 0, 10);
        backwards24DriveDistanceCommand = new VariableDriveDistanceCommand(mecanumDriveSubsystem, imuSubsystem, telemetry, 0.3, 0.3, 0, 24);

        R24StrafeDistanceCommand = new VariableStrafeDistanceCommand(mecanumDriveSubsystem, imuSubsystem, telemetry, 0, 0.3, 0.3,24);
        L24StrafeDistanceCommand = new VariableStrafeDistanceCommand(mecanumDriveSubsystem, imuSubsystem, telemetry, 0, 0.3, -0.3, 48);

        distanceCommand = new DistanceCommand(distanceSensorSub);

        imuCommand = new ImuCommand(imuSubsystem, mecanumDriveSubsystem, telemetry, armSubsystem, grabberServoSubsystem, grabberOpenCommand, cameraSubsystem);
        turn45Command = new TurnCommand(mecanumDriveSubsystem, imuSubsystem, -0.15, -45, telemetry);
        turn0Command = new TurnCommand(mecanumDriveSubsystem, imuSubsystem, -0.15, 0, telemetry);

        grabberCloseCommand = new GrabberCloseCommand(grabberServoSubsystem);
        grabberOpenCommand = new GrabberOpenCommand(grabberServoSubsystem);

        cammeraCommand = new CammeraCommand(cameraSubsystem, telemetry, mecanumDriveSubsystem);
        cameraDetectCommand = new CameraDetectCommand(cameraSubsystem, imuSubsystem);
        detectZoneCommand = new DetectZoneCommand(cameraSubsystem, telemetry);

        elevatorMoveHighCommand = new ElevatorMoveCommand(armSubsystem, telemetry, 35.0);
        elevatorToGroundCommand = new ElevatorToGroundCommand(armSubsystem);
        elevatorToGroundCommand2 = new ElevatorToGroundCommand(armSubsystem);
        elevatorToGroundCommand3 = new ElevatorToGroundCommand(armSubsystem);
        armMotorCommand = new ArmMotorCommand(armSubsystem, () ->0, () -> false, telemetry);
        //() -> is a lambda creates a function which returns in the type you expect

//        turnRightCommand = new TurnRightCommand(mecanumDriveSubsystem, imuSubsystem, 45, 1);

        moveForwards515RaiseHigh = new ParallelCommandGroup(forwards515DriveDistanceCommand, elevatorMoveHighCommand);
        moveBackwards24LowerBottom = new ParallelCommandGroup(backwards24DriveDistanceCommand, elevatorToGroundCommand3);

        firstTallTowerSequence = new SequentialCommandGroup(elevatorToGroundCommand,
                grabberCloseCommand, elevatorToGroundCommand2, moveForwards515RaiseHigh, turn45Command, forwards10DriveDistanceCommand, grabberOpenCommand, backwards10DriveDistanceCommand, turn0Command, moveBackwards24LowerBottom);
//
        armSubsystem.setDefaultCommand(armMotorCommand);

        commandLogging();
        schedule(new SequentialCommandGroup(firstTallTowerSequence));
//        schedule(new SequentialCommandGroup(grabberCloseCommand, forwards515DriveDistanceCommand, turn45Command, elevatorMoveCommand, forwards10DriveDistanceCommand, grabberOpenCommand, backwardsDriveDistanceCommand, elevatorToGroundCommand, turn0Command));
        register(telemetryUpdateSubsystem);
    }

}
