package org.firstinspires.ftc.teamcode.commands;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.TelemetryUpdateSubsystem;

public class PickZoneCommand extends CommandBase {
    TelemetryUpdateSubsystem telemetryUpdateSubsystem;

    @Override
    public void initialize() {



        telemetryUpdateSubsystem = new TelemetryUpdateSubsystem(telemetry);

        MecanumDriveSubsystem mecanumDriveSubsystem = new MecanumDriveSubsystem(hardwareMap, telemetry);

        DriveStrafeDistanceCommand driveRightStrafeCommand =
                new DriveStrafeDistanceCommand(mecanumDriveSubsystem,
                        DriveStrafeDistanceCommand.Direction.RIGHT, 24, telemetry);
        DriveStrafeDistanceCommand driveLeftStrafeCommand =
                new DriveStrafeDistanceCommand(mecanumDriveSubsystem,
                        DriveStrafeDistanceCommand.Direction.LEFT, 24, telemetry);
        DriveDistanceCommand driveForwardsCommand =
                new DriveDistanceCommand(mecanumDriveSubsystem,
                        DriveDistanceCommand.DriveDirection.FORWARDS, 24);
        DriveDistanceCommand driveBackwardsCommand =
                new DriveDistanceCommand(mecanumDriveSubsystem,
                        DriveDistanceCommand.DriveDirection.BACKWARDS, 7);
//        public PickZoneCommand(Number number, Confidence confidence) {
//            if (number = 1 && confidence >= 50) {
//            }
//
//            if (number = 2 && confidence >= 50) {
//            }
//
//            if (number = 3 && confidence >= 50) {
//            } else {
//
//            }
//        }
//            schedule(new SequentialCommandGroup());
    }
}