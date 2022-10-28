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

    public GrabberServoSubsystem(HardwareMap hardwareMap){
//        this.telemetry=telemetry;
        rightGrabberServo = new SimpleServo(hardwareMap, "rightGrabberServo", 0,180);
        leftGrabberServo = new SimpleServo(hardwareMap, "LeftGrabberServo", 0,180);
        openServo();
    }

    public void closeServo(){
        leftGrabberServo.setPosition(0);
        rightGrabberServo.setPosition(1);
    }

    public void openServo(){
        leftGrabberServo.setPosition(0.5);
        rightGrabberServo.setPosition(0.5);
    }

    public void closingServo(){
        leftGrabberServo.setPosition(leftGrabberServo.getPosition() + 0.02);
        rightGrabberServo.setPosition(rightGrabberServo.getPosition() - 0.02);
    }

    public void openingServo(){
        leftGrabberServo.setPosition(leftGrabberServo.getPosition() - 0.1);
        rightGrabberServo.setPosition(rightGrabberServo.getPosition() + 0.1);
    }



    public void updateServo(){
        leftGrabberServo.getPosition();
        rightGrabberServo.getPosition();
    }
}
