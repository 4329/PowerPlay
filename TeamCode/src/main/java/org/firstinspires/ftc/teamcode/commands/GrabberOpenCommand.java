package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.GrabberServoSubsystem;

public class GrabberOpenCommand extends CommandBase {
    private GrabberServoSubsystem grabberServoSubsystem;

    public GrabberOpenCommand(GrabberServoSubsystem grabberServoSubsystem){
        this.grabberServoSubsystem = grabberServoSubsystem;
        addRequirements(grabberServoSubsystem);
    }

    @Override
    public void execute(){
        grabberServoSubsystem.openServo();
    }
}
