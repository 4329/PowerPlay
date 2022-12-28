package org.firstinspires.ftc.teamcode.opmodes;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.CameraSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.TelemetryUpdateSubsystem;

@TeleOp(name = "Camera Test", group = "2")
public class CameraTest extends CommandOpMode {



    @Override
    public void initialize() {
        CameraSubsystem cameraSubsystem = new CameraSubsystem(hardwareMap,telemetry);
        TelemetryUpdateSubsystem telemetryUpdateSubsystem = new TelemetryUpdateSubsystem(telemetry);
        register(telemetryUpdateSubsystem, cameraSubsystem);

    }
}
