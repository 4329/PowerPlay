package org.firstinspires.ftc.teamcode.commands;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
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
    private Telemetry telemetry;

    public DriveStrafeDistanceCommand(MecanumDriveSubsystem mecanumDriveSubsystem, Direction strafeDirection, double distanceInches, Telemetry telemetry) {
        this.mecanumDriveSubsystem = mecanumDriveSubsystem;
        this.strafeDirection = strafeDirection;
        this.telemetry = telemetry;
        DistanceInches = distanceInches;
    }

    public double distanceTraveled(){
        return Math.abs((mecanumDriveSubsystem.getleftBackDrivePosition() - startPosition))/ positionsPerRevolution * wheelCircInches;
    }

    @Override
    public void initialize() {
        startPosition = mecanumDriveSubsystem.getleftBackDrivePosition();
    }

    @Override
    public void execute() {
//        telemetry.addData("Distance Traveled", distanceTraveled());
        switch(strafeDirection){
            case LEFT:
                mecanumDriveSubsystem.Drive(0,0,-0.5);
                break;
            case RIGHT:
                mecanumDriveSubsystem.Drive(0,0,0.5);
                break;
        }
    }


    //it works

    @Override
    public boolean isFinished() {
        return distanceTraveled() >= DistanceInches;
    }



    @Override
    public void end(boolean interrupted) {
        mecanumDriveSubsystem.Drive(0,0,0);

    }
}