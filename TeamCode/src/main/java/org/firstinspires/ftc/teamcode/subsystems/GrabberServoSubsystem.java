package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class GrabberServoSubsystem extends SubsystemBase {
    private final ServoEx rightGrabberServo;
    private final ServoEx leftGrabberServo;
    private Telemetry telemetry;
    private static final double SERVO_CHANGE = .08;
    static final double LEFT_SERVO_CLOSE_POSITION = 1.0;
    static final double LEFT_SERVO_OPEN_POSITION = 0.5;
    private boolean leftServoMoving = false;
    private double previousLeftServoPosition;

    private enum MovingStatus {UNKKNOWN, CLOSED, CLOSING, OPEN, OPENING}

    ;
    private MovingStatus movingStatus = MovingStatus.UNKKNOWN;


    public GrabberServoSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {
        this.telemetry = telemetry;
        rightGrabberServo = new SimpleServo(hardwareMap, "rightGrabberServo", 0, 90);
        leftGrabberServo = new SimpleServo(hardwareMap, "LeftGrabberServo", 0, 90);
        previousLeftServoPosition = leftGrabberServo.getPosition();
        openServo();
    }

    public void closeServo() {
        movingStatus = MovingStatus.CLOSING;
        leftGrabberServo.setPosition(LEFT_SERVO_CLOSE_POSITION);
        rightGrabberServo.setPosition(0.0);
    }

    public boolean isClosed() {
        if (leftGrabberServo.getPosition() != LEFT_SERVO_OPEN_POSITION && !leftServoMoving) {
            return false;
        }
        else {
            return true;
        }
    }

    public void openServo() {
        leftGrabberServo.setPosition(LEFT_SERVO_OPEN_POSITION);
        rightGrabberServo.setPosition(0.5);
        movingStatus = MovingStatus.OPENING;
    }

    public void closingServo() {
        if (updateLeftServo() < 1) {
            leftGrabberServo.setPosition(leftGrabberServo.getPosition() + SERVO_CHANGE);
            rightGrabberServo.setPosition(rightGrabberServo.getPosition() - SERVO_CHANGE);
        }

    }

    public void openingServo() {
        if (updateLeftServo() > .5) {
            leftGrabberServo.setPosition(leftGrabberServo.getPosition() - SERVO_CHANGE);
            rightGrabberServo.setPosition(rightGrabberServo.getPosition() + SERVO_CHANGE);
        }

    }


    public double updateLeftServo() {
        return leftGrabberServo.getPosition();
    }

    public double updateRightServo() {
        return rightGrabberServo.getPosition();
    }

    public void periodic() {
        if (previousLeftServoPosition != leftGrabberServo.getPosition()) {
            // Left Server still moving
            leftServoMoving = true;
            previousLeftServoPosition = leftGrabberServo.getPosition();
        } else {
            // Left Servo not moving. Either blocked or at end of move
            switch (movingStatus) {
                case OPENING:
                    movingStatus = MovingStatus.OPEN;
                    break;
                case CLOSING:
                    movingStatus = MovingStatus.CLOSED;
                    break;
            }
        }

        telemetry.addData("Moving Status", movingStatus);
        telemetry.addData("Left Servo", leftGrabberServo.getPosition());
        telemetry.addData("Left Server Moving", leftServoMoving);
        telemetry.addData("Left Serer Is Closed", this.isClosed());
        telemetry.addData("Right Servo", rightGrabberServo.getPosition());


    }

}

