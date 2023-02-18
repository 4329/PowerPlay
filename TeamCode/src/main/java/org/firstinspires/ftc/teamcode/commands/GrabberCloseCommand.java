package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.util.Timing;

import org.firstinspires.ftc.teamcode.subsystems.GrabberServoSubsystem;

import java.util.concurrent.TimeUnit;

public class GrabberCloseCommand extends CommandBase {
//    private DoubleSupplier grabberPower;
    private GrabberServoSubsystem grabberServoSubsystem;
    private Timing.Timer timer;
    private boolean firstExecute=false;


    public GrabberCloseCommand(GrabberServoSubsystem GrabberServoSubsystem){
        this.grabberServoSubsystem = GrabberServoSubsystem;
        addRequirements(grabberServoSubsystem);
    }

    @Override
    public void initialize() {
        timer = new Timing.Timer(1000, TimeUnit.MILLISECONDS);
    }

    @Override
    public void execute(){
        if(!firstExecute) {
            grabberServoSubsystem.closeServo();
            timer.start();
            firstExecute=true;
        }

    }

    @Override
    public boolean isFinished() {
        return timer.done();
    }
}
