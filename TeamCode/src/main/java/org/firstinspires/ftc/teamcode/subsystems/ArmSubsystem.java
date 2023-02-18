package org.firstinspires.ftc.teamcode.subsystems;


import static java.lang.Math.abs;

import android.util.Log;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ArmSubsystem extends SubsystemBase {
    public Motor ArmMotor;
    private Telemetry telemetry;
    private TouchSensor touchsensor;
    private static final double DISTANCEPERPULSE = 0.0093356918238994;
    private boolean isHolding = false;
    private boolean isGoing = false;
    private int holdingPosition = 0;
    private double holdingDistance = 0;
    private PIDController ElevatorPID;

    public ArmSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {
        this.telemetry = telemetry;
        ArmMotor = new Motor(hardwareMap, "ArmMotor");
        // ArmMotor = hardwareMap.get(MotorEx.class, "ArmMotor");
        touchsensor = hardwareMap.get(TouchSensor.class, "TouchSensor");

        // ArmMotor.setRunMode(Motor.RunMode.VelocityControl);
        // ArmMotor.setRunMode(Motor.RunMode.RawPower);
        setRunning();
        ArmMotor.setDistancePerPulse(DISTANCEPERPULSE);
//        ArmMotor.setTargetPosition(ArmMotor.getCurrentPosition());
    }

    public boolean atHome() {
        return touchsensor.isPressed();
    }

    public void setPower(double Power) {
        if (touchsensor.isPressed() && Power <= 0) {
            ArmMotor.set(0);
            ArmMotor.resetEncoder();
        } else {
            ArmMotor.set(Power);
        }
    }

    public double readElevatorEncoder(){
        return ArmMotor.getCurrentPosition();
    }

    public void periodic() {
        Log.i("10868",   this.getName()+"-periodic-getCurrentDistance="+ArmMotor.getDistance());
        Log.i("10868",   this.getName()+"-periodic-motor power="+ArmMotor.motor.getPower());
        Log.i("10868",   this.getName()+"-periodic-motor position="+ArmMotor.getCurrentPosition());
        telemetry.addData("arm position", ArmMotor.getCurrentPosition());
        telemetry.addData("arm distance", ArmMotor.getDistance());
        telemetry.addData("arm power", ArmMotor.motor.getPower());
        telemetry.addData("istouchsensorpressed", touchsensor.isPressed());
        telemetry.addData("isHolding", isHolding);
        telemetry.addData("isGoing",isGoing);
        // telemetry.addData("holdingPosition",holdingPosition);
        telemetry.addData("holdingDistance", holdingDistance);
        telemetry.addData("get", ArmMotor.get());
        telemetry.addData("atTargetPosition", ArmMotor.atTargetPosition());
    }

    public void setHolding() {
        if (!isHolding) {
            holdingDistance = ArmMotor.getDistance();
            ArmMotor.setRunMode(Motor.RunMode.PositionControl);
            ArmMotor.setPositionCoefficient(0.400);
            ArmMotor.setPositionTolerance(.5);
            ArmMotor.set(0.5);
            ArmMotor.setTargetDistance(holdingDistance);
            isHolding= true;
        }
        else {
            ArmMotor.set(.5);
        }
    }


    public void setRunning() {
        ArmMotor.setRunMode(Motor.RunMode.RawPower);
        isHolding=false;
    }

    public void goToInches(double TargetInches) {
        holdingDistance=TargetInches;
        ArmMotor.setRunMode(Motor.RunMode.PositionControl);
        ArmMotor.setPositionCoefficient(0.400);
        ArmMotor.setPositionTolerance(.5);
        ArmMotor.setTargetDistance(holdingDistance);
        ArmMotor.set(0.5);
        isGoing=true;
    }

    public boolean atTargetPosition() {
        return ArmMotor.atTargetPosition();
    }

}


