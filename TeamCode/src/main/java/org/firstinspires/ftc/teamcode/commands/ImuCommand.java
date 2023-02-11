package org.firstinspires.ftc.teamcode.commands;
import android.util.Log;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.util.Timing;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.CameraSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.GrabberServoSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ImuSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;

import java.util.function.DoubleSupplier;

public class ImuCommand extends CommandBase {
    private ImuSubsystem imuSubsystem;
    private MecanumDriveSubsystem mecanumDriveSubsystem;
    private ArmSubsystem armSubsystem;
    private CameraSubsystem cameraSubsystem;
    private GrabberServoSubsystem grabberServoSubsystem;
    private GrabberOpenCommand grabberOpenCommand;
    private static final String TAG = "10868";
    private DriveStrafeDistanceCommand driveLeftStrafeCommand;
    private DriveStrafeDistanceCommand driveRightStrafeCommand;
    private DriveDistanceCommand driveForwardsCommand;
    private DriveDistanceCommand driveThreeForwardsCommand;
    private DriveDistanceCommand driveBackwardsCommand;
    private DriveDistanceCommand driveStopCommand;
    private boolean foundZone=false;
    private double High;
    private double Medium;
    private double Low;
    private Telemetry telemetry;

    public ImuCommand(ImuSubsystem ImuSubsystem, MecanumDriveSubsystem mecanumDriveSubsystem, Telemetry telemetry, ArmSubsystem armSubsystem, GrabberServoSubsystem grabberServoSubsystem, GrabberOpenCommand grabberOpenCommand, CameraSubsystem cammeraSubsystem){
        this.imuSubsystem = ImuSubsystem;
        this.mecanumDriveSubsystem = mecanumDriveSubsystem;
        this.High = High;
        this.Medium = Medium;
        this.Low = Low;
        this.telemetry = telemetry;
        this.armSubsystem = armSubsystem;
        this.grabberServoSubsystem = grabberServoSubsystem;
        this.grabberOpenCommand = grabberOpenCommand;
        this.cameraSubsystem = cammeraSubsystem;
//        cameraSubsystem.cameraActicate();
        driveRightStrafeCommand =
                new DriveStrafeDistanceCommand(mecanumDriveSubsystem,
                        DriveStrafeDistanceCommand.Direction.RIGHT,27, telemetry);
        driveLeftStrafeCommand =
                new DriveStrafeDistanceCommand(mecanumDriveSubsystem,
                        DriveStrafeDistanceCommand.Direction.LEFT,25, telemetry);
        driveForwardsCommand =
                new DriveDistanceCommand(mecanumDriveSubsystem,
                        DriveDistanceCommand.DriveDirection.FORWARDS,24);
        driveThreeForwardsCommand =
                new DriveDistanceCommand(mecanumDriveSubsystem,
                        DriveDistanceCommand.DriveDirection.FORWARDS,72);
        //too long 72
        driveBackwardsCommand =
                new DriveDistanceCommand(mecanumDriveSubsystem,
                        DriveDistanceCommand.DriveDirection.BACKWARDS,7);
        driveStopCommand =
                new DriveDistanceCommand(mecanumDriveSubsystem,
                        DriveDistanceCommand.DriveDirection.STOP,0);
        addRequirements(imuSubsystem);
    }


    @Override
    public void execute(){
//        turn45R();
//        mecanumDriveSubsystem.Drive();
//        armSubsystem.elevaorHigh();
//        CommandScheduler.getInstance().schedule(grabberOpenCommand);
//        turn45L();
////        switch (cameraSubsystem.directionChooser()) {
////            case One:
////                telemetry.addLine("one is scheduled in command");
////                Log.i(TAG, "schedule: One");
////                CommandScheduler.getInstance().schedule(new SequentialCommandGroup(driveLeftStrafeCommand, driveForwardsCommand));
////                foundZone = true;
////                break;
////            case Two:
////                telemetry.addLine("two is sheduled");
////                Log.i(TAG, "schedule: Tue");
////                CommandScheduler.getInstance().schedule(new SequentialCommandGroup(driveBackwardsCommand));
////                foundZone = true;
////                break;
////            case Three:
////                telemetry.addLine("three is scheduled command");
////                Log.i(TAG, "schedule: Three");
////                CommandScheduler.getInstance().schedule(new SequentialCommandGroup(driveRightStrafeCommand, driveForwardsCommand));
////                foundZone = true;
////                break;
////        }
    }

    @Override
    public boolean isFinished() {
        return foundZone;
    }

    @Override
    public void end(boolean interrupted) {
        if (interrupted) {
            CommandScheduler.getInstance().schedule(new SequentialCommandGroup(driveRightStrafeCommand, driveForwardsCommand));
        }
        else{
            CommandScheduler.getInstance().schedule(driveStopCommand);
        }
    }
}
