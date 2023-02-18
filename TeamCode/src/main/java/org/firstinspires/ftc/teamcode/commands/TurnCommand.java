package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.controller.PIDController;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.ImuSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;

public class TurnCommand extends CommandBase {
    private PIDController HeadingPID;
    private double initialHeading;
    private ImuSubsystem imuSubsystem;
    private MecanumDriveSubsystem mecanumDriveSubsystem;
    private double TurnPower;
    private double TurnAngle;
    private Telemetry Telemetry;

    public TurnCommand(MecanumDriveSubsystem mecanumDriveSubsystem, ImuSubsystem imuSubsystem, double TurnPower, double TurnAngle, Telemetry telemetry){
        this.mecanumDriveSubsystem = mecanumDriveSubsystem;
        this.imuSubsystem = imuSubsystem;
        this.TurnPower = TurnPower;
        this.TurnAngle = TurnAngle;
        this.Telemetry = telemetry;
    }

    @Override
    public void initialize() {
        HeadingPID = new PIDController(0.1,0,0);
        HeadingPID.setTolerance(5);
        initialHeading = imuSubsystem.Heading();
    }

    @Override
    public String toString() {
        return "VariableDriveDistanceCommand{" +
                "mecanumDriveSubsystem=" + mecanumDriveSubsystem +
                ", imuSubsystem=" + imuSubsystem +
                ", Telemetry=" + Telemetry +
                ", TurnAngle=" + TurnAngle +
                ", HeadingPID=" + HeadingPID +
                ", initialHeading=" + initialHeading +
                '}';
    }



    @Override
    public void execute() {
        double outputHeading = HeadingPID.calculate(imuSubsystem.Heading(), TurnAngle);
        mecanumDriveSubsystem.Drive(0,outputHeading * TurnPower, 0);
        Telemetry.addData("outputHeading", outputHeading);
        Telemetry.addLine(this.toString());

    }

    public void turnLogic(){

    }

    @Override
    public boolean isFinished() {
        return HeadingPID.atSetPoint();
    }

    @Override
    public void end(boolean interrupted) {
        mecanumDriveSubsystem.Drive(0,0,0);
    }
}
