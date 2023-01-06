package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.CameraSubsystem;

public class CammeraCommand extends CommandBase {
    private CameraSubsystem cameraSubsystem;


    public CammeraCommand(CameraSubsystem camerasubsystem){
        cameraSubsystem = camerasubsystem;
        addRequirements(cameraSubsystem);
    }

    @Override
    public void schedule() {
        cameraSubsystem.cameraActicate();
        cameraSubsystem.detectObjects();
        cameraSubsystem.cameraDeactivate();

    }
}

