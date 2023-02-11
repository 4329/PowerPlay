package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.geometry.Pose2d;
import com.arcrobotics.ftclib.geometry.Rotation2d;
import com.arcrobotics.ftclib.geometry.Translation2d;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.kinematics.wpilibkinematics.MecanumDriveKinematics;
import com.arcrobotics.ftclib.kinematics.wpilibkinematics.MecanumDriveOdometry;
import com.arcrobotics.ftclib.kinematics.wpilibkinematics.MecanumDriveWheelSpeeds;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class DriveSubsystem extends SubsystemBase {

    private Telemetry telemetry;
    private MecanumDrive mecanumDrive;
    private ImuSubsystem imuSubsystem;
    private Motor leftBackDrive;
    private Motor rightBackDrive;
    private Motor rightFrontDrive;
    private Motor leftFrontDrive;

    MecanumDriveOdometry mecanumDriveOdometry;
    MecanumDriveKinematics kinematics;
    Pose2d currentPose;



    public DriveSubsystem(HardwareMap hardwareMap, Telemetry telemetry, ImuSubsystem imuSubsystem) {
        this.telemetry = telemetry;
        this.imuSubsystem = imuSubsystem;
        leftFrontDrive = new Motor(hardwareMap, "LeftFrontDrive");
        rightFrontDrive = new Motor(hardwareMap, "RightFrontDrive");
        leftBackDrive = new Motor(hardwareMap, "LeftBackDrive");
        rightBackDrive = new Motor(hardwareMap, "RightBackDrive");


        leftFrontDrive.motor.setDirection(DcMotor.Direction.REVERSE);
        rightFrontDrive.motor.setDirection(DcMotor.Direction.REVERSE);
        leftBackDrive.motor.setDirection(DcMotor.Direction.REVERSE);
        rightBackDrive.motor.setDirection(DcMotor.Direction.REVERSE);

        leftFrontDrive.motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFrontDrive.motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBackDrive.motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBackDrive.motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        mecanumDrive = new MecanumDrive(leftFrontDrive, rightFrontDrive, leftBackDrive, rightBackDrive);

        Translation2d frontLeftLocation = new Translation2d(0.23, 0.23);
        Translation2d frontRightLocation = new Translation2d(0.23, -0.23);
        Translation2d backLeftLocation = new Translation2d(-0.23, 0.23);
        Translation2d backRightLocation = new Translation2d(-0.23, -0.23);

        kinematics = new MecanumDriveKinematics(
                frontLeftLocation, frontRightLocation, backLeftLocation, backRightLocation
        );

        Rotation2d gyroAngle = Rotation2d.fromDegrees(imuSubsystem.Heading());

        mecanumDriveOdometry = new MecanumDriveOdometry(
                kinematics,gyroAngle,
                new Pose2d(0,0, new Rotation2d())
        );

    }


    public void Drive(double forwardPositive, double rotateClockwisePositive, double strafeRightPositive) {
        mecanumDrive.driveRobotCentric(-strafeRightPositive, forwardPositive, -rotateClockwisePositive, false);
    }

    public double getleftBackEncoderPosition() {
        return leftBackDrive.getCurrentPosition();
    }

    @Override
    public void periodic() {
        MecanumDriveWheelSpeeds wheelSpeeds = new MecanumDriveWheelSpeeds(
                leftFrontDrive.encoder.getRate(), rightFrontDrive.encoder.getRate(),
                leftBackDrive.encoder.getRate(), rightBackDrive.encoder.getRate()
        );

        Rotation2d gyroAngle = Rotation2d.fromDegrees(imuSubsystem.Heading());


    }
}
