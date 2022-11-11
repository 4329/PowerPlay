package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class TelemetryUpdateSubsystem extends SubsystemBase {

    private Telemetry telemetry;

    public TelemetryUpdateSubsystem(Telemetry telemetry){
        this.telemetry = telemetry;
    }

    @Override
    public void periodic() {
        telemetry.update();
    }
}
