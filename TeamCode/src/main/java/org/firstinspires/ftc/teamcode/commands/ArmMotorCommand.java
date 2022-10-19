package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;

import java.util.function.DoubleSupplier;

public class ArmMotorCommand extends CommandBase {
    private DoubleSupplier armPowerSupplier;
    private ArmSubsystem armSubsystem;

    public ArmMotorCommand(ArmSubsystem ArmSubsystem, DoubleSupplier ArmPowerSupplier){
        armPowerSupplier = ArmPowerSupplier;
        armSubsystem = ArmSubsystem;
        addRequirements(armSubsystem);
    }

    @Override
    public void execute() {
        armSubsystem.setPower(armPowerSupplier.getAsDouble());
    }
}
