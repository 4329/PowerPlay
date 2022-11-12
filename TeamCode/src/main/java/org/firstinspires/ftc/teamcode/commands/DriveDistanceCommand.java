package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;

public class DriveDistanceCommand extends CommandBase {
    public enum DriveDirection {
        FORWARDS,
        BACKWARDS,
        STOP
    }
    private MecanumDriveSubsystem mecanumDriveSubsystem;
    private DriveDistanceCommand.DriveDirection DriveDirection;
    private double DistanceInches;
    private double startPosition;
    static double countsPerRevolution = 28;
    static double gearRatio = 20;
    static double positionsPerRevolution = countsPerRevolution * gearRatio;
    static double wheelDiameterInches = 100 / 26.54;
    static double wheelCircInches = wheelDiameterInches * Math.PI;

    public DriveDistanceCommand(MecanumDriveSubsystem mecanumDriveSubsystem, DriveDistanceCommand.DriveDirection driveDirection, double distanceInches) {
        this.mecanumDriveSubsystem = mecanumDriveSubsystem;
        this.DriveDirection = driveDirection;
        DistanceInches = distanceInches;
    }

    private double distanceTraveled(){
        return Math.abs((mecanumDriveSubsystem.getleftBackDrivePosition() - startPosition))/ positionsPerRevolution * wheelCircInches;
    }

    @Override
    public void initialize() {
        startPosition = mecanumDriveSubsystem.getleftBackDrivePosition();
    }

    @Override
    public void execute() {
        switch(DriveDirection){
            case FORWARDS:
                mecanumDriveSubsystem.Drive(-0.5,0,0);
                break;
            case BACKWARDS:
                mecanumDriveSubsystem.Drive(0.5,0,0);
                break;
            case STOP:
                mecanumDriveSubsystem.Drive(0,0,0);
                break;
        }
    }

    @Override
    public boolean isFinished() {
        return distanceTraveled() >= DistanceInches;
    }

    @Override
    public void end(boolean interrupted) {
        mecanumDriveSubsystem.Drive(0,0,0);

    }
}
