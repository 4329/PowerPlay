package org.firstinspires.ftc.teamcode.opmodes;


import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.HardwareMap;


import org.firstinspires.ftc.teamcode.commands.DriveStrafeDistanceCommand;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;

@Autonomous(name = "Red To Terminal", group = "2")
public class RedToTerminal extends CommandOpMode {

        @Override
        public void initialize() {
//            DriveDistance driveDistance = new DriveDistance(mecanumDriveSubsystem,
//                    0, 0, -0.5, 24);
//            schedule(new SequentialCommandGroup(driveDistance.withTimeout(1500)));
                MecanumDriveSubsystem mecanumDriveSubsystem= new MecanumDriveSubsystem(hardwareMap,telemetry);

                DriveStrafeDistanceCommand driveStrafeDistanceCommand =
                        new DriveStrafeDistanceCommand(mecanumDriveSubsystem,
                                DriveStrafeDistanceCommand.Direction.LEFT,10);
                schedule(driveStrafeDistanceCommand);
        }
}
