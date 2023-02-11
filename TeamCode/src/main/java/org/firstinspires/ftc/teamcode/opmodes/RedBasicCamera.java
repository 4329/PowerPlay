package org.firstinspires.ftc.teamcode.opmodes;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.commands.CammeraCommand;
import org.firstinspires.ftc.teamcode.commands.GrabberCloseCommand;
import org.firstinspires.ftc.teamcode.subsystems.CameraSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.GrabberServoSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ImuSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.TelemetryUpdateSubsystem;

@Autonomous(name = "CameraAuto", group = "2")
public class RedBasicCamera extends CommandOpMode {

    private TelemetryUpdateSubsystem telemetryUpdateSubsystem;
    private CameraSubsystem cameraSubsystem;
    private CammeraCommand cammeraCommand;
    private MecanumDriveSubsystem mecanumDriveSubsystem;
    private GrabberServoSubsystem grabberServoSubsystem;
    private GrabberCloseCommand grabberCommand;
    private ImuSubsystem imuSubsystem;

    private static final String TAG = "10868";

    @Override
    public void initialize() {
        mecanumDriveSubsystem = new MecanumDriveSubsystem(hardwareMap, telemetry, imuSubsystem);
        cameraSubsystem = new CameraSubsystem(hardwareMap, telemetry);
        telemetryUpdateSubsystem = new TelemetryUpdateSubsystem(telemetry);
        cammeraCommand = new CammeraCommand(cameraSubsystem, telemetry, mecanumDriveSubsystem);
        grabberServoSubsystem = new GrabberServoSubsystem(hardwareMap, telemetry);
        grabberCommand = new GrabberCloseCommand(grabberServoSubsystem);
        schedule(grabberCommand, cammeraCommand.withTimeout(10));
        register(telemetryUpdateSubsystem);
    }
}
