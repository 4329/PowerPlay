package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class GrabberSubsystem extends SubsystemBase {
    protected com.qualcomm.robotcore.hardware.CRServo crServo;

    public GrabberSubsystem(HardwareMap hMap) {
        crServo = hMap.get(com.qualcomm.robotcore.hardware.CRServo.class, "GrabberMotor");
    }

    public void setPower(double output) {
        crServo.setPower(output * 0.5);
    }
}

