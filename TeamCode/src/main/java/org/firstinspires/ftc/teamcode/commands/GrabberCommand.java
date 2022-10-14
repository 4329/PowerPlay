package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import java.util.function.DoubleSupplier;
import org.firstinspires.ftc.teamcode.subsystems.GrabberSubsystem;

public class GrabberCommand extends CommandBase {
    private DoubleSupplier grabberPower;
    private GrabberSubsystem grabberSubsystem;

    public GrabberCommand(GrabberSubsystem GrabberSubsystem, DoubleSupplier GrabberPower){
        grabberSubsystem = GrabberSubsystem;
        grabberPower = GrabberPower;
        addRequirements(grabberSubsystem);
    }

    @Override
    public void execute(){
        grabberSubsystem.hold(grabberPower.getAsDouble());
    }
}
