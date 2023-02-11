package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.util.Timing;

import org.firstinspires.ftc.teamcode.subsystems.CameraSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ImuSubsystem;

public class CameraDetectCommand extends CommandBase {
    private CameraSubsystem cameraSubsystem;
    private ImuSubsystem imuSubsystem;
    private Timing.Timer timer;

    public CameraDetectCommand(CameraSubsystem cameraSubsystem, ImuSubsystem imuSubsystem){
        this.cameraSubsystem = cameraSubsystem;
        this.imuSubsystem = imuSubsystem;
    }
    @Override
    public void execute() {
        cameraSubsystem.detectObjects();
        imuSubsystem.imuTelemetry();
        cameraSubsystem.getZoneDetected();
    }
}
