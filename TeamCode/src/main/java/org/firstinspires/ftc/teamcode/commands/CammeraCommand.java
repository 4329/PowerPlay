package org.firstinspires.ftc.teamcode.commands;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.CameraSubsystem;

public class CammeraCommand extends CommandBase {
    private CameraSubsystem cameraSubsystem;


    public CammeraCommand(CameraSubsystem camerasubsystem){
        cameraSubsystem = camerasubsystem;
        addRequirements(cameraSubsystem);
    }

    @Override
    public void execute() {
        cameraSubsystem.DetectObjects();
    }
}

