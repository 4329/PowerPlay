package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
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
//    private IMUSystem imuSystem;
    private MecanumDrive mecanumDrive;

    public MecanumDriveSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {
        this.telemetry = telemetry;
//        this.imuSystem = imuSystem;
        Motor leftFrontDrive = new Motor(hardwareMap, "LeftFrontDrive");
        Motor rightFrontDrive = new Motor(hardwareMap, "RightFrontDrive");
        Motor leftBackDrive = new Motor(hardwareMap, "LeftBackDrive");
        Motor rightBackDrive = new Motor(hardwareMap, "RightBackDrive");

//        rightFrontDrive.encoder.setDistancePerPulse(distanceInchPerPulse);
//        leftFrontDrive.encoder.setDistancePerPulse(distanceInchPerPulse);
//        rightBackDrive.encoder.setDistancePerPulse(distanceInchPerPulse);
//        leftBackDrive.encoder.setDistancePerPulse(distanceInchPerPulse);

        leftFrontDrive.motor.setDirection(DcMotor.Direction.REVERSE);
        rightFrontDrive.motor.setDirection(DcMotor.Direction.REVERSE);
        leftBackDrive.motor.setDirection(DcMotor.Direction.REVERSE);
        rightBackDrive.motor.setDirection(DcMotor.Direction.REVERSE);

        mecanumDrive = new MecanumDrive(leftFrontDrive, rightFrontDrive, leftBackDrive, rightBackDrive);
    }

//    public void loop() {
//        drive.driveRobotCentric(
//                driverOp.getLeftX(),
//                driverOp.getLeftY(),
//                driverOp.getRightY()
//        );
//    }

    public void Drive(double forward, double turn, double strafe){
            mecanumDrive.driveRobotCentric(-strafe, forward, -turn, false);

            //mecanumDrive.driveRobotCentric(strafe,-turn, forward);

    }
}
//I learned Lower count case and upper count case