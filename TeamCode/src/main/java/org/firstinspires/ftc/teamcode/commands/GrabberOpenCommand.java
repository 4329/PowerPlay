package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.util.Timing;

import org.firstinspires.ftc.teamcode.subsystems.GrabberServoSubsystem;

import java.util.concurrent.TimeUnit;

public class GrabberOpenCommand extends CommandBase {
    private GrabberServoSubsystem grabberServoSubsystem;
    private Timing.Timer timer;
    private boolean firstExecute=false;

    public GrabberOpenCommand(GrabberServoSubsystem grabberServoSubsystem){
        this.grabberServoSubsystem = grabberServoSubsystem;
        addRequirements(grabberServoSubsystem);
    }

    @Override
    public void initialize() {
        timer = new Timing.Timer(500, TimeUnit.MILLISECONDS);
    }

    @Override
    public void execute(){
        if(!firstExecute) {
            grabberServoSubsystem.openServo();
            timer.start();
            firstExecute=true;
        }

    }

    @Override
    public boolean isFinished() {
        return timer.done();
    }
}
