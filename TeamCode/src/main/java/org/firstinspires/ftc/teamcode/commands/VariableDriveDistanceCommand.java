package org.firstinspires.ftc.teamcode.commands;

import android.util.Log;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.controller.PIDController;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.ImuSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;

public class VariableDriveDistanceCommand extends CommandBase {
    final static String TAG = "10868";
    private MecanumDriveSubsystem mecanumDriveSubsystem;
    private ImuSubsystem imuSubsystem;
    private double Forward;
    private Telemetry Telemetry;
    private double Turn;
    private double Strafe;
    private double DistanceInches;
    private double startPosition;
    static double countsPerRevolution = 25.875;
    static double gearRatio = 20;
    static double positionsPerRevolution = countsPerRevolution * gearRatio;
    static double diameterGobildaMM = 96;
    static double wheelDiameterInches = diameterGobildaMM / 25.4;
    static double wheelCircInches = wheelDiameterInches * Math.PI;
    private PIDController DistancePID;
    private PIDController HeadingPID;
    private double initialHeading;

    public VariableDriveDistanceCommand(MecanumDriveSubsystem mecanumDriveSubsystem, ImuSubsystem imuSubsystem,
                                        Telemetry telemetry, double forward, double turn, double strafe, double distanceInches) {
        this.Forward = forward;
        this.Turn = turn;
        this.Strafe = strafe;
        this.Telemetry = telemetry;
        this.mecanumDriveSubsystem = mecanumDriveSubsystem;
        this.imuSubsystem = imuSubsystem;
        this.DistanceInches = distanceInches;
        addRequirements();
    }

    public double distanceTraveled2() {
        return Math.abs(mecanumDriveSubsystem.getleftBackDrivePosition() - startPosition) / positionsPerRevolution * wheelCircInches;
    }

    @Override
    public void execute() {
        double output = DistancePID.calculate(distanceTraveled2(), DistanceInches);
        double outputHeading = HeadingPID.calculate(imuSubsystem.Heading(), initialHeading);
        mecanumDriveSubsystem.Drive(output * -Forward, outputHeading * -Turn, output * Strafe);
    }

    @Override
    public void initialize() {
        startPosition = mecanumDriveSubsystem.getleftBackDrivePosition();
        DistancePID = new PIDController(0.1, 0, 0);
        DistancePID.setTolerance(0.1);
        HeadingPID = new PIDController(0.1,0,0);
        HeadingPID.setTolerance(0.5);
        initialHeading = imuSubsystem.Heading();
    }

    @Override
    public boolean isFinished() {
        Telemetry.addData("distance", distanceTraveled2());
        Telemetry.addData("start", startPosition);
        Telemetry.addData("encoder", mecanumDriveSubsystem.getleftBackDrivePosition());
        Log.i(TAG, this.getName() + "-isFinished: startPosition=" + startPosition);
        Log.i(TAG, this.getName() + "-isFinished: distance=" + distanceTraveled2());
        Log.i(TAG, this.getName() + "- isFinished: getLeftBackDrivePosition=" + mecanumDriveSubsystem.getleftBackDrivePosition());
        return DistancePID.atSetPoint() && HeadingPID.atSetPoint();
//        return distanceTraveled2() >= DistanceInches;
    }

    @Override
    public void end(boolean interrupted) {
        mecanumDriveSubsystem.Drive(0, 0, 0);
    }
}
