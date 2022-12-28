package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class GrabberServoSubsystem extends SubsystemBase {
    private final ServoEx rightGrabberServo;
    private final ServoEx leftGrabberServo;
    private Telemetry telemetry;

    public GrabberServoSubsystem(HardwareMap hardwareMap, Telemetry telemetry){
        this.telemetry=telemetry;
        rightGrabberServo = new SimpleServo(hardwareMap, "rightGrabberServo", 0,90);
        leftGrabberServo = new SimpleServo(hardwareMap, "LeftGrabberServo", 0,90);
        openServo();
    }

    public void closeServo(){
        leftGrabberServo.setPosition(0.18);
        rightGrabberServo.setPosition(0.87);
    }

    public void openServo(){
        leftGrabberServo.setPosition(0.5);
        rightGrabberServo.setPosition(0.5);
    }

    public void closingServo(){
        leftGrabberServo.setPosition(leftGrabberServo.getPosition() + 0.01);
        rightGrabberServo.setPosition(rightGrabberServo.getPosition() - 0.01);
    }

    public void openingServo(){
        if(updateLeftServo() >= 0.18 && updateRightServo() <= 0.87){
            leftGrabberServo.setPosition(leftGrabberServo.getPosition() - 0.005);
            rightGrabberServo.setPosition(rightGrabberServo.getPosition() + 0.005);
        }
        else {
            leftGrabberServo.setPosition(leftGrabberServo.getPosition() - 0.01);
            rightGrabberServo.setPosition(rightGrabberServo.getPosition() + 0.01);
        }
    }



    public double updateLeftServo(){
        return leftGrabberServo.getPosition();
    }

    public double updateRightServo(){
      return rightGrabberServo.getPosition();
    }

    public void periodic() {
        telemetry.addData("Left Servo", leftGrabberServo.getPosition());
        telemetry.addData("Right Servo", rightGrabberServo.getPosition());
    }

}

