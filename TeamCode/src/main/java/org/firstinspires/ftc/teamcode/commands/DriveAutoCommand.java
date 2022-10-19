package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;

public class DriveAutoCommand extends CommandBase {
        private MecanumDriveSubsystem mecanumDriveSubsystem;
        private Telemetry telemetry;
        private double forwardPower;
        private double rotatePower;
        private double strafePower;
        private double distanceInches;
        private double startPosition;
        static double countsPerRevolution = 26;
        static double gearRatio = 20;
        static double positionsPerRevolution = countsPerRevolution * gearRatio;
        static double wheelDiameterInches = 75.0 / 26.54;
        static double wheelCircInches = wheelDiameterInches * Math.PI;

        @Override
        public void initialize() {
            startPosition = driveSystem.getRightFrontEncoder();
        }

        private double distanceTraveled(){
            return Math.abs((driveSystem.getRightFrontEncoder() - startPosition))/ positionsPerRevolution * wheelCircInches;
        }

        @Override
        public void execute() {
            driveSystem.Drive(this.forwardPower,this.rotatePower, this.strafePower);

        }

        @Override
        public void end(boolean interrupted) {
            driveSystem.Drive(0,0,0);
        }

        @Override
        public boolean isFinished() {
            telemetry.addLine(this.getName()+":running");
            telemetry.addData(this.getName()+":rightFrontEncoder",MecanumDriveSubsystem.getRightFrontEncoder());
            telemetry.addData(this.getName()+":distanceTraveled",distanceTraveled());
            return distanceTraveled() >= distanceInches;
        }

        public DriveDistance(MecanumDriveSubsystem mecanumDriveSubsystem, double forwardPower, double rotatePower,
                                  double strafePower, double distanceInches, Telemetry telemetry) {
            this.MecanumDriveSubsystem = mecanumDriveSubsystem;
            this.forwardPower = forwardPower;
            this.rotatePower = rotatePower;
            this.strafePower = strafePower;
            this.distanceInches = distanceInches;
            this.telemetry = telemetry;
        }
}
