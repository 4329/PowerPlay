package org.firstinspires.ftc.teamcode.commands;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.arcrobotics.ftclib.hardware.motors.Motor;

public class AutoPushConeIntoTarmack {
    private Motor armMotor;
    private Motor elevatorMotor;

    public void initialize()
    {
        armMotor = new Motor(hardwareMap, "armMotor");
        elevatorMotor = new Motor(hardwareMap, "elevatorMotor");
    }
}
