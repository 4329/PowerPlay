package org.firstinspires.ftc.teamcode.subsystems;


import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ArmSubsystem extends SubsystemBase {
    private MotorEx ArmMotor;
    private Telemetry telemetry;
    private TouchSensor touchsensor;

    public ArmSubsystem(HardwareMap hardwareMap, Telemetry telemetry){
        this.telemetry = telemetry;
        ArmMotor = new MotorEx(hardwareMap,"ArmMotor");
        // ArmMotor = hardwareMap.get(MotorEx.class, "ArmMotor");
        touchsensor = hardwareMap.get(TouchSensor.class, "TouchSensor");
        ArmMotor.setRunMode(Motor.RunMode.VelocityControl);
//        ArmMotor.setTargetPosition(ArmMotor.getCurrentPosition());
    }

    public void setPower(double Power){
        double powerSet = 0;
        if(touchsensor.isPressed() && Power<0 ){
            powerSet = 0;
        }
        else {
            powerSet = Power;
        }
        ArmMotor.motor.setPower(powerSet);
    }

    public void periodic() {
        telemetry.addData("arm position", ArmMotor.getCurrentPosition());
        telemetry.addData("arm power", ArmMotor.motor.getPower());
        telemetry.addData("istouchsensorpressed", touchsensor.isPressed());
        if (touchsensor.isPressed() && ArmMotor.motor.getPower()==0){
            ArmMotor.resetEncoder();
        }
    }

}


