package org.firstinspires.ftc.teamcode.opmodes;


import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;


import org.firstinspires.ftc.teamcode.commands.DriveDistanceCommand;
import org.firstinspires.ftc.teamcode.commands.DriveStrafeDistanceCommand;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;

@Autonomous(name = "Red To Location One", group = "2")
public class RedToLocationOne extends CommandOpMode {

        @Override
        public void initialize() {
//            DriveDistance driveDistance = new DriveDistance(mecanumDriveSubsystem,
//                    0, 0, -0.5, 24);
//            schedule(new SequentialCommandGroup(driveDistance.withTimeout(1500)));
                MecanumDriveSubsystem mecanumDriveSubsystem = new MecanumDriveSubsystem(hardwareMap, telemetry);

                DriveStrafeDistanceCommand driveLeftStrafeCommand =
                        new DriveStrafeDistanceCommand(mecanumDriveSubsystem,
                                DriveStrafeDistanceCommand.Direction.LEFT,12);
                DriveDistanceCommand driveForwardsCommand =
                        new DriveDistanceCommand(mecanumDriveSubsystem,
                                DriveDistanceCommand.DriveDirection.FORWARDS,12);
                schedule(new SequentialCommandGroup(driveLeftStrafeCommand, driveForwardsCommand));
        }
}
