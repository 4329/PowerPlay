package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class ArmSubsystem extends SubsystemBase {
    private DcMotorEx ArmMotor;

    public ArmSubsystem(HardwareMap hardwareMap){
        ArmMotor = hardwareMap.get(DcMotorEx.class, "ArmMotor");
//        ArmMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
//        ArmMotor.setTargetPosition(ArmMotor.getCurrentPosition());
    }

    public void setPower(double Power){

        ArmMotor.setPower(Power);
    }

}


