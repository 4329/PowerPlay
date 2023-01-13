package org.firstinspires.ftc.teamcode.opmodes;

import android.util.Log;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.commands.CammeraCommand;
import org.firstinspires.ftc.teamcode.commands.DriveDistanceCommand;
import org.firstinspires.ftc.teamcode.commands.DriveStrafeDistanceCommand;
import org.firstinspires.ftc.teamcode.subsystems.CameraSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.TelemetryUpdateSubsystem;

@Autonomous(name = "Red Basic Camera", group = "2")
public class RedBasicCamera extends CommandOpMode {

    TelemetryUpdateSubsystem telemetryUpdateSubsystem;
    CameraSubsystem cameraSubsystem;
    CammeraCommand cammeraCommand;
    MecanumDriveSubsystem mecanumDriveSubsystem;

    private static final String TAG = "10868";

    @Override
    public void initialize() {
        mecanumDriveSubsystem = new MecanumDriveSubsystem(hardwareMap, telemetry);
        cameraSubsystem = new CameraSubsystem(hardwareMap, telemetry);
        telemetryUpdateSubsystem = new TelemetryUpdateSubsystem(telemetry);
        cammeraCommand = new CammeraCommand(cameraSubsystem, telemetry, mecanumDriveSubsystem);
        schedule(cammeraCommand.withTimeout(20000));
        register(telemetryUpdateSubsystem);
    }
}
