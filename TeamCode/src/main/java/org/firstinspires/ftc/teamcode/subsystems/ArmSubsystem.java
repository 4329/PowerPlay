package org.firstinspires.ftc.teamcode.subsystems;


import static java.lang.Math.abs;

import android.util.Log;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ArmSubsystem extends SubsystemBase {
    private Motor ArmMotor;
    private Telemetry telemetry;
    private TouchSensor touchsensor;
    private static final double DISTANCEPERPULSE=(88.6-14.2)/5756;
    private boolean isHolding=false;
    private int holdingPosition=0;
    private double holdingDistance=5;


    public ArmSubsystem(HardwareMap hardwareMap, Telemetry telemetry){
        this.telemetry = telemetry;
        ArmMotor = new Motor(hardwareMap,"ArmMotor");
        // ArmMotor = hardwareMap.get(MotorEx.class, "ArmMotor");
        touchsensor = hardwareMap.get(TouchSensor.class, "TouchSensor");

        // ArmMotor.setRunMode(Motor.RunMode.VelocityControl);
        // ArmMotor.setRunMode(Motor.RunMode.RawPower);
        setRunning();
        ArmMotor.setDistancePerPulse(DISTANCEPERPULSE);
//        ArmMotor.setTargetPosition(ArmMotor.getCurrentPosition());
    }

    public void setPower(double Power){
        double powerSet = 0;
        // Power = (abs(Power)<.01)?0.0:Power;
        if(touchsensor.isPressed() && Power<0 ){
            powerSet = 0;

        }
        else {
            if (abs(Power)<.01) {
                if (!isHolding) {
                    holdingPosition = ArmMotor.getCurrentPosition();
                    holdingDistance = ArmMotor.getDistance();
                    isHolding=true;
                    setHolding();
                    powerSet=0;
                }
            } else {
                isHolding=false;
                powerSet = Power;
            }
        }

        if (!isHolding) {
            setRunning();
            ArmMotor.set(powerSet);
        }

    }

    public void periodic() {
        Log.i(this.getName(),"periodic: Start");
        telemetry.addData("arm position", ArmMotor.getCurrentPosition());
        telemetry.addData("arm distance",ArmMotor.getDistance());
        telemetry.addData("arm power", ArmMotor.motor.getPower());
        telemetry.addData("istouchsensorpressed", touchsensor.isPressed());
        telemetry.addData("isHolding",isHolding);
        // telemetry.addData("holdingPosition",holdingPosition);
        telemetry.addData("holdingDistance",holdingDistance);
        telemetry.addData("get",ArmMotor.get());
        telemetry.addData("atTargetPosition",ArmMotor.atTargetPosition());

        if (touchsensor.isPressed() && ArmMotor.motor.getPower()==0){
            ArmMotor.resetEncoder();
        }
        if(isHolding){
            if (!ArmMotor.atTargetPosition()){
                ArmMotor.set(.5);
            }else {
                ArmMotor.set(.5);
            }
        }
    }

    private void setHolding(){
        ArmMotor.setRunMode(Motor.RunMode.PositionControl);
        ArmMotor.setPositionCoefficient(0.400);
        ArmMotor.setPositionTolerance(.5);
        ArmMotor.set(0);
        // ArmMotor.setTargetPosition(holdingPosition);
        ArmMotor.setTargetDistance(holdingDistance);
    }
    private void  setRunning(){
        ArmMotor.setRunMode(Motor.RunMode.RawPower);
    }

}


