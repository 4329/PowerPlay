package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.GrabberServoSubsystem;

public class GrabberCloseCommand extends CommandBase {
//    private DoubleSupplier grabberPower;
    private GrabberServoSubsystem grabberServoSubsystem;

    public GrabberCloseCommand(GrabberServoSubsystem GrabberServoSubsystem){
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
