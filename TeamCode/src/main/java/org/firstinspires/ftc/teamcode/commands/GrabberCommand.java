package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import java.util.function.DoubleSupplier;

import org.firstinspires.ftc.teamcode.subsystems.GrabberServoSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.GrabberSubsystem;

public class GrabberCommand extends CommandBase {
//    private DoubleSupplier grabberPower;
    private GrabberServoSubsystem grabberServoSubsystem;

    public GrabberCommand(GrabberServoSubsystem GrabberServoSubsystem){
        this.grabberServoSubsystem = GrabberServoSubsystem;
////        grabberPower = GrabberPower;
        addRequirements(grabberServoSubsystem);
    }

    @Override
    public void execute(){
          grabberServoSubsystem.closeServo();
//        grabberSubsystem.hold(grabberPower.getAsDouble());
    }
}
