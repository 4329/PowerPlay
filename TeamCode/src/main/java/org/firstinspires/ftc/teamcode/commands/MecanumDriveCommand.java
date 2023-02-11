package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.robocol.Command;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

public class MecanumDriveCommand extends CommandBase {
    private MecanumDriveSubsystem mecanumDriveSubsystem;
    private DoubleSupplier forwardDrive;
    private DoubleSupplier rotateDrive;
    private DoubleSupplier strafeDrive;
    private BooleanSupplier slowMotion;
    private Telemetry telemetry;
    private static double SLOW_MOTION_DIVISOR=3.0;

    public MecanumDriveCommand(MecanumDriveSubsystem mecanumDriveSubsystem,
                               DoubleSupplier forwardDrive,
                               DoubleSupplier rotateDrive,
                               DoubleSupplier strafeDrive,
                               BooleanSupplier slowMotion,
                               Telemetry telemetry) {
        this.mecanumDriveSubsystem = mecanumDriveSubsystem;
        this.forwardDrive = forwardDrive;
        this.rotateDrive = rotateDrive;
        this.strafeDrive = strafeDrive;
        this.slowMotion = slowMotion;
        this.telemetry = telemetry;
        addRequirements(mecanumDriveSubsystem);
    }

    @Override
    public void execute() {
        if (!slowMotion.getAsBoolean()) {
            mecanumDriveSubsystem.Drive(
                    forwardDrive.getAsDouble()/SLOW_MOTION_DIVISOR,
                    rotateDrive.getAsDouble()/SLOW_MOTION_DIVISOR,
                    strafeDrive.getAsDouble()/SLOW_MOTION_DIVISOR);
        }
        else {
            mecanumDriveSubsystem.Drive(
                    forwardDrive.getAsDouble(),
                    rotateDrive.getAsDouble(),
                    strafeDrive.getAsDouble());
        }
    }
}
