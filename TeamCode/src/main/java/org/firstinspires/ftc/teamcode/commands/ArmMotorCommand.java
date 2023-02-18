package org.firstinspires.ftc.teamcode.commands;

import static java.lang.Math.abs;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

public class ArmMotorCommand extends CommandBase {
    private DoubleSupplier armPowerSupplier;
    private BooleanSupplier armSlowMode;
    private ArmSubsystem armSubsystem;
    private Telemetry telemetry;

    public ArmMotorCommand(ArmSubsystem ArmSubsystem, DoubleSupplier ArmPowerSupplier, BooleanSupplier armSlowMode, Telemetry telemetry){
        armPowerSupplier = ArmPowerSupplier;
        armSubsystem = ArmSubsystem;
        this.armSlowMode = armSlowMode;
        this.telemetry = telemetry;
        addRequirements(armSubsystem);
    }

    @Override
    public void execute() {
        telemetry.addLine("arm motor is running");
        double Power = armPowerSupplier.getAsDouble();
        if(abs(Power)<.01){
            armSubsystem.setHolding();
        }
        else {
            armSubsystem.setRunning();
            if (!armSlowMode.getAsBoolean()) {
                armSubsystem.setPower(armPowerSupplier.getAsDouble() * 1.0);
            } else {
                armSubsystem.setPower(armPowerSupplier.getAsDouble());
            }
        }
    }
}
