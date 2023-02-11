package org.firstinspires.ftc.teamcode.commands;

import android.util.Log;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.CameraSubsystem;

public class DetectZoneCommand extends CommandBase {
    private CameraSubsystem cameraSubsystem;
    private Telemetry telemetry;
    public CameraSubsystem.Zones zoneDetected = CameraSubsystem.Zones.Unknown;

    public DetectZoneCommand(CameraSubsystem cameraSubsystem, Telemetry telemetry) {
        this.cameraSubsystem = cameraSubsystem;
        this.telemetry = telemetry;
    }

    @Override
    public void initialize() {
        cameraSubsystem.cameraActivate();
    }

    @Override
    public void execute() {
        zoneDetected = cameraSubsystem.getZoneDetected();
    }

    @Override
    public boolean isFinished() {
        Log.i("10868", String.format(this.getName()+":isFinished: %s",zoneDetected));
        return zoneDetected != CameraSubsystem.Zones.Unknown;
    }
}


