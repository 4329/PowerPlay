package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.RevIMU;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class MecanumDriveSubsystem extends SubsystemBase {

    private Motor frontLeft, frontRight, backLeft, backRight;
    private MecanumDrive drive;
    private GamepadEx driverOp;

    public void initilizeMotor() throws InterruptedException {
        /* instantiate motors */
        this.drive = new MecanumDrive(
             new Motor(hardwareMap, "frontLeft", Motor.GoBILDA.RPM_435),
             new Motor(hardwareMap, "frontRight", Motor.GoBILDA.RPM_435),
             new Motor(hardwareMap, "backLeft", Motor.GoBILDA.RPM_435),
             new Motor(hardwareMap, "backRight", Motor.GoBILDA.RPM_435)
        );
        driverOp = new GamepadEx(gamepad1);

        RevIMU imu = new RevIMU(hardwareMap);
        imu.init();
    }

    public void loop() {
        drive.driveRobotCentric(
                driverOp.getLeftX(),
                driverOp.getLeftY(),
                driverOp.getRightY()
        );
    }
}