package org.firstinspires.ftc.teamcode.opmodes;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;

@Autonomous(name = "Red Park Only", group = "2")
public class RedToTerminal extends CommandOpMode {
        private MecanumDriveSubsystem mecanumDriveSubsystem;

        @Override
        public void initialize() {
            DriveDistance driveDistance = new DriveDistance(mecanumDriveSubsystem,
                    0, 0, -0.5, 24);

            schedule(new SequentialCommandGroup(driveDistance.withTimeout(1500)));

        }
}
