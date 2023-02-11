package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class MecanumDriveSubsystem extends SubsystemBase {

    private MecanumDrive drive;
    private GamepadEx driverOp;
    private Telemetry telemetry;
    private ImuSubsystem imuSubsystem;
    private MecanumDrive mecanumDrive;
    private Motor leftBackDrive;
    private Motor rightBackDrive;
    private Motor rightFrontDrive;
    private Motor leftFrontDrive;
    private PIDController turnPID;


    public MecanumDriveSubsystem(HardwareMap hardwareMap, Telemetry telemetry, ImuSubsystem imuSubsystem) {
        this.telemetry = telemetry;
        this.imuSubsystem = imuSubsystem;
        leftFrontDrive = new Motor(hardwareMap, "LeftFrontDrive");
        rightFrontDrive = new Motor(hardwareMap, "RightFrontDrive");
        leftBackDrive = new Motor(hardwareMap, "LeftBackDrive");
        rightBackDrive = new Motor(hardwareMap, "RightBackDrive");

//        rightFrontDrive.encoder.setDistancePerPulse(distanceInchPerPulse);
//        leftFrontDrive.encoder.setDistancePerPulse(distanceInchPerPulse);
//        rightBackDrive.encoder.setDistancePerPulse(distanceInchPerPulse);
//        leftBackDrive.encoder.setDistancePerPulse(distanceInchPerPulse);

        leftFrontDrive.motor.setDirection(DcMotor.Direction.REVERSE);
        rightFrontDrive.motor.setDirection(DcMotor.Direction.REVERSE);
        leftBackDrive.motor.setDirection(DcMotor.Direction.REVERSE);
        rightBackDrive.motor.setDirection(DcMotor.Direction.REVERSE);

        leftFrontDrive.motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFrontDrive.motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBackDrive.motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBackDrive.motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        mecanumDrive = new MecanumDrive(leftFrontDrive, rightFrontDrive, leftBackDrive, rightBackDrive);
        turnPID = new PIDController(1,0,0);
        turnPID.setTolerance(15);
    }

    public enum DriveDegrees {
        RightFortyFive,
        LeftFortyFive,
        RightOneHundred35,
        LeftOneHundred35,
        RightNinety,
        LeftNinety
    }

    public enum TurnDirection{
        Right,
        Left
    }




    public void Drive(double forward, double turn, double strafe){
            mecanumDrive.driveRobotCentric(-strafe, forward, -turn, false);
    }


    public double getleftBackDrivePosition (){
        return leftBackDrive.getCurrentPosition();
    }

    public double getEncoderPosition(){
        return leftBackDrive.getCurrentPosition();
    }

//    public void periodic() {
//        double TPower = turnPID.calculate(imuPosition, turn);
//        telemetry.addData("encoder distance", getEncoderPosition());
//    }
}
//I learned Lower count case and upper count case