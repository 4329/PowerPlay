package org.firstinspires.ftc.teamcode.commands;
import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.ImuSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

public class ImuCommand extends CommandBase {
    private ImuSubsystem imuSubsystem;
    private MecanumDriveSubsystem mecanumDriveSubsystem;
    private double target;
    private DoubleSupplier forwardDrive;
    private DoubleSupplier rotateDrive;
    private DoubleSupplier strafeDrive;

    public ImuCommand(ImuSubsystem ImuSubsystem, MecanumDriveSubsystem mecanumDriveSubsystem, double target){
        this.imuSubsystem = ImuSubsystem;
        this.mecanumDriveSubsystem = mecanumDriveSubsystem;
        this.target = target;
        addRequirements(imuSubsystem, mecanumDriveSubsystem);
    }

    @Override
    public void execute(){
        mecanumDriveSubsystem.Drive(0,1,0);
        imuSubsystem.imuTelemetry();
    }

    @Override
    public boolean isFinished() {
        return Math.abs(Math.abs(imuSubsystem.Position())- Math.abs(target)) < 5;
    }

    @Override
    public void end(boolean interrupted) {
        mecanumDriveSubsystem.Drive(0,0,0);
    }
}
