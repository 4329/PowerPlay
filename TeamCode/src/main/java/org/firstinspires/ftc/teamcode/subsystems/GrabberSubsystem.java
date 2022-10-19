package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class GrabberSubsystem extends SubsystemBase {
    protected com.qualcomm.robotcore.hardware.CRServo GrabberMotor;

    public GrabberSubsystem(HardwareMap hMap) {
        GrabberMotor = hMap.get(com.qualcomm.robotcore.hardware.CRServo.class, "GrabberMotor");
    }

    public void hold(double power) {
//        if (GrabberMotor.getPower() >= 0.7) {
//            GrabberMotor.setPower(power);
//        }
//        else {
//            GrabberMotor.setPower(power * 0);
//        }
        GrabberMotor.setPower(power * 0.7);
    }
}


