package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class ArmSubsystem extends SubsystemBase {
    private DcMotorEx ArmMoter;

    public ArmSubsystem(HardwareMap hardwareMap){
        ArmMoter = hardwareMap.get(DcMotorEx.class, "ArmMotor");
    }

    public void setPower(double Power){
        ArmMoter.setPower(Power);
    }
}
