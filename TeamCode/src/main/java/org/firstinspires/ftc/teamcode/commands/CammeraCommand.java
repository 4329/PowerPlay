package org.firstinspires.ftc.teamcode.commands;

import android.util.Log;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.util.Timing;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.teamcode.subsystems.CameraSubsystem;

import java.util.concurrent.TimeUnit;

public class CammeraCommand extends CommandBase {
    private CameraSubsystem cameraSubsystem;
    private static final String TAG = "10868";
    private Telemetry telemetry;
    private Timing.Timer timer;


    public CammeraCommand(CameraSubsystem camerasubsystem, Telemetry telemetry) {
        cameraSubsystem = camerasubsystem;
        this.telemetry = telemetry;
        cameraSubsystem.cameraActicate();
        addRequirements(cameraSubsystem);
    }


    @Override
    public void execute() {
        Log.i(TAG, "schedule: At Start");
        cameraSubsystem.detectObjects();
        cameraSubsystem.directionChooser();
        switch (cameraSubsystem.directionChooser()) {
            case One:
                telemetry.addLine("one is scheduled in command");
                Log.i(TAG, "schedule: One");
                break;
            case Two:
                telemetry.addLine("two is sheduled");
                Log.i(TAG, "schedule: Tue");
                break;
            case Three:
                telemetry.addLine("three is scheduled command");
                Log.i(TAG, "schedule: Three");
                break;
            case Unknown:
                telemetry.addLine("unknown is scheduled command");
                Log.i(TAG, "schedule: Unknown");
        }
    }

//    @Override
//    public boolean isFinished() {
//        return timer.done();
//    }

    @Override
    public void end(boolean interrupted) {
        cameraSubsystem.cameraDeactivate();
    }
}

