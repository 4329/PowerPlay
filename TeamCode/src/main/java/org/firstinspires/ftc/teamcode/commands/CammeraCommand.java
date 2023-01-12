package org.firstinspires.ftc.teamcode.commands;

import android.util.Log;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.ScheduleCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.util.Timing;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.CameraSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class CammeraCommand extends CommandBase {
    private CameraSubsystem cameraSubsystem;
    private MecanumDriveSubsystem mecanumDriveSubsystem;
    private static final String TAG = "10868";
    private Telemetry telemetry;
    private Timing.Timer timer;
    private DriveStrafeDistanceCommand driveLeftStrafeCommand;
    private DriveStrafeDistanceCommand driveRightStrafeCommand;
    private DriveDistanceCommand driveForwardsCommand;
    private DriveDistanceCommand driveBackwardsCommand;
    private DriveDistanceCommand driveStopCommand;
    private boolean foundZone=false;

    public CammeraCommand(CameraSubsystem camerasubsystem, Telemetry telemetry, MecanumDriveSubsystem mecanumDriveSubsystem) {
        cameraSubsystem = camerasubsystem;
        this.mecanumDriveSubsystem = mecanumDriveSubsystem;
        this.telemetry = telemetry;
        cameraSubsystem.cameraActicate();
        driveRightStrafeCommand =
                new DriveStrafeDistanceCommand(mecanumDriveSubsystem,
                        DriveStrafeDistanceCommand.Direction.RIGHT,5, telemetry);
        driveLeftStrafeCommand =
                new DriveStrafeDistanceCommand(mecanumDriveSubsystem,
                        DriveStrafeDistanceCommand.Direction.LEFT,5, telemetry);
        driveForwardsCommand =
                new DriveDistanceCommand(mecanumDriveSubsystem,
                        DriveDistanceCommand.DriveDirection.FORWARDS,24);
        driveBackwardsCommand =
                new DriveDistanceCommand(mecanumDriveSubsystem,
                        DriveDistanceCommand.DriveDirection.BACKWARDS,7);
        driveStopCommand =
                new DriveDistanceCommand(mecanumDriveSubsystem,
                        DriveDistanceCommand.DriveDirection.STOP,0);
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
                CommandScheduler.getInstance().schedule(new SequentialCommandGroup(driveLeftStrafeCommand, driveForwardsCommand));
                foundZone = true;
                break;
            case Two:
                telemetry.addLine("two is sheduled");
                Log.i(TAG, "schedule: Tue");
                CommandScheduler.getInstance().schedule(new SequentialCommandGroup(driveForwardsCommand));
                foundZone = true;
                break;
            case Three:
                telemetry.addLine("three is scheduled command");
                Log.i(TAG, "schedule: Three");
                CommandScheduler.getInstance().schedule(new SequentialCommandGroup(driveRightStrafeCommand, driveForwardsCommand));
                foundZone = true;
                break;
        }
    }


    @Override
    public boolean isFinished() {
        return foundZone;
    }

    @Override
    public void end(boolean interrupted) {
        cameraSubsystem.cameraDeactivate();

    }
}

