package org.firstinspires.ftc.teamcode.opmodes;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.commands.CameraDetectCommand;
import org.firstinspires.ftc.teamcode.commands.CammeraCommand;
import org.firstinspires.ftc.teamcode.commands.DetectZoneCommand;
import org.firstinspires.ftc.teamcode.commands.DistanceCommand;
import org.firstinspires.ftc.teamcode.commands.ElevatorMoveCommand;
import org.firstinspires.ftc.teamcode.commands.GrabberCloseCommand;
import org.firstinspires.ftc.teamcode.commands.GrabberOpenCommand;
import org.firstinspires.ftc.teamcode.commands.ImuCommand;
import org.firstinspires.ftc.teamcode.commands.TurnRightCommand;
import org.firstinspires.ftc.teamcode.commands.VariableDriveDistanceCommand;
import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.CameraSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DistanceSensorSub;
import org.firstinspires.ftc.teamcode.subsystems.GrabberServoSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ImuSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.TelemetryUpdateSubsystem;

@Autonomous (name = "Left Full Auto", group = "2")
public class LeftFullAuto extends CommandOpMode {
    private TelemetryUpdateSubsystem telemetryUpdateSubsystem;
    private DistanceSensorSub distanceSensorSub;
    private DistanceCommand distanceCommand;
    private ImuCommand imuCommand;
    private ImuSubsystem imuSubsystem;
    private MecanumDriveSubsystem mecanumDriveSubsystem;
    private VariableDriveDistanceCommand forwardsDriveDistanceCommand;
    private VariableDriveDistanceCommand backwardsDriveDistanceCommand;
    private VariableDriveDistanceCommand rightStafeDriveDistanceCommand;
    private CameraSubsystem cameraSubsystem;
    private CammeraCommand cammeraCommand;
    private GrabberOpenCommand grabberOpenCommand;
    private GrabberCloseCommand grabberCloseCommand;
    private GrabberServoSubsystem grabberServoSubsystem;
    private GrabberCloseCommand grabberCommand;
    private ArmSubsystem armSubsystem;
    private CameraDetectCommand cameraDetectCommand;
    private DetectZoneCommand detectZoneCommand;
    private TurnRightCommand turnRightCommand;
    private ElevatorMoveCommand elevatorMoveCommand;

    @Override
    public void initialize(){
        imuSubsystem = new ImuSubsystem(hardwareMap, telemetry);
        armSubsystem = new ArmSubsystem(hardwareMap, telemetry);
        mecanumDriveSubsystem = new MecanumDriveSubsystem(hardwareMap, telemetry, imuSubsystem);
        forwardsDriveDistanceCommand = new VariableDriveDistanceCommand(mecanumDriveSubsystem, imuSubsystem, telemetry, 0.3, 0.3,0, 51.5);
        backwardsDriveDistanceCommand = new VariableDriveDistanceCommand(mecanumDriveSubsystem, imuSubsystem, telemetry, 0, 1,0, 1);
        rightStafeDriveDistanceCommand = new VariableDriveDistanceCommand(mecanumDriveSubsystem, imuSubsystem, telemetry, 0, 0,1, 1);
        telemetryUpdateSubsystem = new TelemetryUpdateSubsystem(telemetry);
        distanceSensorSub = new DistanceSensorSub(hardwareMap, telemetry);
        distanceCommand = new DistanceCommand(distanceSensorSub);
        imuCommand = new ImuCommand(imuSubsystem, mecanumDriveSubsystem, telemetry, armSubsystem, grabberServoSubsystem, grabberOpenCommand, cameraSubsystem);
        cameraSubsystem = new CameraSubsystem(hardwareMap, telemetry);
        telemetryUpdateSubsystem = new TelemetryUpdateSubsystem(telemetry);
        cammeraCommand = new CammeraCommand(cameraSubsystem, telemetry, mecanumDriveSubsystem);
        grabberServoSubsystem = new GrabberServoSubsystem(hardwareMap, telemetry);
        grabberCommand = new GrabberCloseCommand(grabberServoSubsystem);
        grabberOpenCommand = new GrabberOpenCommand(grabberServoSubsystem);
        cameraDetectCommand = new CameraDetectCommand(cameraSubsystem, imuSubsystem);
        detectZoneCommand = new DetectZoneCommand(cameraSubsystem, telemetry);
        turnRightCommand = new TurnRightCommand(mecanumDriveSubsystem, imuSubsystem, 45, 1);
        elevatorMoveCommand = new ElevatorMoveCommand(armSubsystem, 10);
        schedule(new SequentialCommandGroup(elevatorMoveCommand));
        register(telemetryUpdateSubsystem);
    }

}
