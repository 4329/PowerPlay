package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;

import java.util.function.DoubleSupplier;


public class DriveStrafeDistanceCommand extends CommandBase {
    public enum Direction {
        RIGHT,
        LEFT
    }
    private MecanumDriveSubsystem mecanumDriveSubsystem;
    private Direction strafeDirection;
    private double DistanceInches;
    private double startPosition;
    static double countsPerRevolution = 28;
    static double gearRatio = 20;
    static double positionsPerRevolution = countsPerRevolution * gearRatio;
    static double wheelDiameterInches = 100 / 26.54;
    static double wheelCircInches = wheelDiameterInches * Math.PI;

    public DriveStrafeDistanceCommand(MecanumDriveSubsystem mecanumDriveSubsystem, Direction strafeDirection, double distanceInches) {
        this.mecanumDriveSubsystem = mecanumDriveSubsystem;
        this.strafeDirection = strafeDirection;
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
        switch(strafeDirection){
            case LEFT:
                mecanumDriveSubsystem.Drive(0,0,-1);
                break;
            case RIGHT:
                mecanumDriveSubsystem.Drive(0,0,1);
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