package org.firstinspires.ftc.teamcode.opmodes;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.commands.CammeraCommand;
import org.firstinspires.ftc.teamcode.subsystems.CameraSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.TelemetryUpdateSubsystem;

@Autonomous(name = "Red Basic Camera", group = "2")
public class RedBasicCamera extends CommandOpMode {

    TelemetryUpdateSubsystem telemetryUpdateSubsystem;
    CameraSubsystem cameraSubsystem;
    CammeraCommand cammeraCommand;


    @Override
    public void initialize() {
        cameraSubsystem = new CameraSubsystem(hardwareMap, telemetry);
        telemetryUpdateSubsystem = new TelemetryUpdateSubsystem(telemetry);
        cammeraCommand = new CammeraCommand(cameraSubsystem, telemetry);
        schedule(cammeraCommand.withTimeout(25000));
        register(telemetryUpdateSubsystem);
    }
}
