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


        @Override
        public void initialize() {
                telemetryUpdateSubsystem = new TelemetryUpdateSubsystem(telemetry);
//            DriveDistance driveDistance = new DriveDistance(mecanumDriveSubsystem,
//                    0, 0, -0.5, 24);
//            schedule(new SequentialCommandGroup(driveDistance.withTimeout(1500)));
                MecanumDriveSubsystem mecanumDriveSubsystem = new MecanumDriveSubsystem(hardwareMap, telemetry);

                DriveStrafeDistanceCommand driveLeftStrafeCommand =
                        new DriveStrafeDistanceCommand(mecanumDriveSubsystem,
                                DriveStrafeDistanceCommand.Direction.LEFT,50, telemetry);
                DriveDistanceCommand driveForwardsCommand =
                        new DriveDistanceCommand(mecanumDriveSubsystem,
                                DriveDistanceCommand.DriveDirection.FORWARDS,20);
                DriveDistanceCommand driveStopCommand =
                        new DriveDistanceCommand(mecanumDriveSubsystem,
                                DriveDistanceCommand.DriveDirection.STOP,0);
                schedule(new SequentialCommandGroup(driveLeftStrafeCommand));
                register(telemetryUpdateSubsystem);
        }
}
