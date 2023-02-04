package org.firstinspires.ftc.teamcode.opmodes;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.commands.DistanceCommand;
import org.firstinspires.ftc.teamcode.commands.ImuCommand;
import org.firstinspires.ftc.teamcode.subsystems.DistanceSensorSub;
import org.firstinspires.ftc.teamcode.subsystems.ImuSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.TelemetryUpdateSubsystem;

@Autonomous (name = "SensorTestAuto", group = "2")
public class SensorTestAuto extends CommandOpMode {
    private TelemetryUpdateSubsystem telemetryUpdateSubsystem;
    private DistanceSensorSub distanceSensorSub;
    private DistanceCommand distanceCommand;
    private ImuCommand imuCommand;
    private ImuSubsystem imuSubsystem;

    @Override
    public void initialize(){
        telemetryUpdateSubsystem = new TelemetryUpdateSubsystem(telemetry);
        distanceSensorSub = new DistanceSensorSub(hardwareMap, telemetry);
        distanceCommand = new DistanceCommand(distanceSensorSub);
        imuSubsystem = new ImuSubsystem(hardwareMap, telemetry);
        imuCommand = new ImuCommand(imuSubsystem);
        schedule(new ParallelCommandGroup(distanceCommand, imuCommand));
        register(telemetryUpdateSubsystem);
    }
}
