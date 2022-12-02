package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;

public class MecanumDpadCommand extends CommandBase {

    private MecanumDriveSubsystem mecanumDriveSystem;
    private double forwardPower, rotatePower, strafePower;
    private Telemetry telemetry;

    public MecanumDpadCommand(MecanumDriveSubsystem mecanumDriveSubsystem,
                              double forwardPower, double rotatePower,
                              double strafePower, Telemetry telemetry) {
        this.mecanumDriveSystem = mecanumDriveSubsystem;
        this.forwardPower = forwardPower;
        this.rotatePower = rotatePower;
        this.strafePower = strafePower;
        this.telemetry = telemetry;
        addRequirements(mecanumDriveSubsystem);
    }

    @Override
    public void execute() {
        mecanumDriveSystem.Drive(forwardPower, rotatePower, strafePower);
    }

}
