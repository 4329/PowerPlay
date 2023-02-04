package org.firstinspires.ftc.teamcode.commands;
import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.ImuSubsystem;

public class ImuCommand extends CommandBase {
    private ImuSubsystem imuSubsystem;

    public ImuCommand(ImuSubsystem ImuSubsystem){
        this.imuSubsystem = ImuSubsystem;
        addRequirements(imuSubsystem);
    }

    @Override
    public void execute(){
        imuSubsystem.imuTelemetry();
    }
}
