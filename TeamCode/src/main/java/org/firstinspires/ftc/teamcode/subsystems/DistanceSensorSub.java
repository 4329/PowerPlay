package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class DistanceSensorSub extends SubsystemBase {
    private DistanceSensor sensorRange;
    private DistanceSensor sensorRange2;
    private Telemetry telemetry;

    public DistanceSensorSub(HardwareMap hardwareMap, Telemetry telemetry){
        this.telemetry = telemetry;
        sensorRange = hardwareMap.get(DistanceSensor.class, "DistanceSensor1");
        sensorRange2 = hardwareMap.get(DistanceSensor.class, "DistanceSensor2");
    }

    public void distanceTelemetry(){
        Rev2mDistanceSensor sensorTimeOfFlight = (Rev2mDistanceSensor)sensorRange;

        telemetry.addData("deviceName",sensorRange.getDeviceName() );
//        telemetry.addData("range", String.format("%.01f mm", sensorRange.getDistance(DistanceUnit.MM)));
//        telemetry.addData("range", String.format("%.01f cm", sensorRange.getDistance(DistanceUnit.CM)));
//        telemetry.addData("range", String.format("%.01f m", sensorRange.getDistance(DistanceUnit.METER)));
        telemetry.addData("range", String.format("%.01f in", sensorRange.getDistance(DistanceUnit.INCH)));
        telemetry.addData("range", String.format("%.01f in", sensorRange2.getDistance(DistanceUnit.INCH)));

        // Rev2mDistanceSensor specific methods.
        telemetry.addData("ID", String.format("%x", sensorTimeOfFlight.getModelID()));
        telemetry.addData("did time out", Boolean.toString(sensorTimeOfFlight.didTimeoutOccur()));
    }

    @Override
    public void periodic() {
        telemetry.update();
    }
}
