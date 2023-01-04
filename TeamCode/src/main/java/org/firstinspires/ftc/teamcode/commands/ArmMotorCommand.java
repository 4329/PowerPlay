package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

public class ArmMotorCommand extends CommandBase {
    private DoubleSupplier armPowerSupplier;
    private BooleanSupplier armSlowMode;
    private ArmSubsystem armSubsystem;

    public ArmMotorCommand(ArmSubsystem ArmSubsystem, DoubleSupplier ArmPowerSupplier, BooleanSupplier armSlowMode){
        armPowerSupplier = ArmPowerSupplier;
        armSubsystem = ArmSubsystem;
        this.armSlowMode = armSlowMode;
        addRequirements(armSubsystem);
    }

    @Override
    public void execute() {
        if (!armSlowMode.getAsBoolean()){
            armSubsystem.setPower(armPowerSupplier.getAsDouble() * 1.0);
        }
        else {
            armSubsystem.setPower(armPowerSupplier.getAsDouble());
        }
    }
}
