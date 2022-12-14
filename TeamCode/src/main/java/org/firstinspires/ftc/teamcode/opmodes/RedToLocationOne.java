package org.firstinspires.ftc.teamcode.opmodes;


import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;


import org.firstinspires.ftc.teamcode.commands.DriveDistanceCommand;
import org.firstinspires.ftc.teamcode.commands.DriveStrafeDistanceCommand;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.TelemetryUpdateSubsystem;

@Autonomous(name = "Red To Location One", group = "2")
public class RedToLocationOne extends CommandOpMode {

        TelemetryUpdateSubsystem telemetryUpdateSubsystem;
        DriveStrafeDistanceCommand driveLeftStrafeCommand;
        DriveStrafeDistanceCommand driveRightStrafeCommand;
        DriveDistanceCommand driveForwardsCommand;
        DriveDistanceCommand driveBackwardsCommand;
        DriveDistanceCommand driveStopCommand;


        @Override
        public void initialize() {
                telemetryUpdateSubsystem = new TelemetryUpdateSubsystem(telemetry);
//            DriveDistance driveDistance = new DriveDistance(mecanumDriveSubsystem,
//                    0, 0, -0.5, 24);
//            schedule(new SequentialCommandGroup(driveDistance.withTimeout(1500)));
                MecanumDriveSubsystem mecanumDriveSubsystem = new MecanumDriveSubsystem(hardwareMap, telemetry);

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
                schedule(new SequentialCommandGroup(driveLeftStrafeCommand, driveBackwardsCommand, driveRightStrafeCommand, driveForwardsCommand));
                register(telemetryUpdateSubsystem);
        }
}
