package org.firstinspires.ftc.teamcode.opmodes;

import android.util.Log;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.commands.DetectZoneCommand;
import org.firstinspires.ftc.teamcode.subsystems.CameraSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ImuSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.TelemetryUpdateSubsystem;

@Autonomous(name = "Left Auto with Sensors",group = "3 - In Development")
public class LeftAutoWithSensors extends CommandOpMode {
    private TelemetryUpdateSubsystem telemetryUpdateSubsystem;
    private CameraSubsystem cameraSubsystem;
    private ImuSubsystem imuSubsystem;
    private DriveSubsystem driveSubsystem;

    private DetectZoneCommand detectZoneCommand;

    @Override
    public void initialize() {
        telemetryUpdateSubsystem = new TelemetryUpdateSubsystem(telemetry);
        cameraSubsystem = new CameraSubsystem(hardwareMap,telemetry);
        imuSubsystem = new ImuSubsystem(hardwareMap,telemetry);
        driveSubsystem = new DriveSubsystem(hardwareMap,telemetry,imuSubsystem);


        detectZoneCommand = new DetectZoneCommand(cameraSubsystem,telemetry);

        CommandScheduler.getInstance()
                .onCommandInitialize(
                        command -> Log.i("10868", getClass().getName()+":Command initialize: " + command.getName())
                );
        CommandScheduler.getInstance().onCommandFinish(
                command -> Log.i("10868", getClass().getName()+":Command Finish: "+command.getName())
        );
        CommandScheduler.getInstance().onCommandInterrupt(
                command -> Log.i("10868", getClass().getName()+":Command Interrupt: "+command.getName())
        );

        schedule(detectZoneCommand.withTimeout(5000));
        register(cameraSubsystem,imuSubsystem,telemetryUpdateSubsystem);
    }

}
