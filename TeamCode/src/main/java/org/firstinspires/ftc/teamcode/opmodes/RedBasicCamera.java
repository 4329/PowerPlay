package org.firstinspires.ftc.teamcode.opmodes;

import android.util.Log;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.commands.CammeraCommand;
import org.firstinspires.ftc.teamcode.commands.DriveDistanceCommand;
import org.firstinspires.ftc.teamcode.commands.DriveStrafeDistanceCommand;
import org.firstinspires.ftc.teamcode.subsystems.CameraSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.TelemetryUpdateSubsystem;

@Autonomous(name = "Red Basic Camera", group = "2")
public class RedBasicCamera extends CommandOpMode {

    TelemetryUpdateSubsystem telemetryUpdateSubsystem;
    CameraSubsystem cameraSubsystem;
    CammeraCommand cammeraCommand;
    DriveStrafeDistanceCommand driveLeftStrafeCommand;
    DriveStrafeDistanceCommand driveRightStrafeCommand;
    DriveDistanceCommand driveForwardsCommand;
    DriveDistanceCommand driveBackwardsCommand;
    DriveDistanceCommand driveStopCommand;
    MecanumDriveSubsystem mecanumDriveSubsystem;

    private static final String TAG = "10868";

    @Override
    public void initialize() {
        mecanumDriveSubsystem = new MecanumDriveSubsystem(hardwareMap, telemetry);

        driveRightStrafeCommand =
                new DriveStrafeDistanceCommand(mecanumDriveSubsystem,
                        DriveStrafeDistanceCommand.Direction.RIGHT,24, telemetry);
        driveLeftStrafeCommand =
                new DriveStrafeDistanceCommand(mecanumDriveSubsystem,
                        DriveStrafeDistanceCommand.Direction.LEFT,24, telemetry);
        driveForwardsCommand =
                new DriveDistanceCommand(mecanumDriveSubsystem,
                        DriveDistanceCommand.DriveDirection.FORWARDS,24);
        driveBackwardsCommand =
                new DriveDistanceCommand(mecanumDriveSubsystem,
                        DriveDistanceCommand.DriveDirection.BACKWARDS,7);
        driveStopCommand =
                new DriveDistanceCommand(mecanumDriveSubsystem,
                        DriveDistanceCommand.DriveDirection.STOP,0);
        cameraSubsystem = new CameraSubsystem(hardwareMap, telemetry);
        telemetryUpdateSubsystem = new TelemetryUpdateSubsystem(telemetry);
        cammeraCommand = new CammeraCommand(cameraSubsystem, telemetry, mecanumDriveSubsystem);
        schedule(cammeraCommand.withTimeout(20000));
        register(telemetryUpdateSubsystem);
    }
}
