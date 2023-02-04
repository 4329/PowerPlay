package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.DistanceSensorSub;

public class DistanceCommand extends CommandBase {
    private DistanceSensorSub distanceSensorSub;


    public DistanceCommand(DistanceSensorSub DistanceSensorSub){
        this.distanceSensorSub = DistanceSensorSub;
        addRequirements(distanceSensorSub);
    }

    @Override
    public void execute() {
        distanceSensorSub.distanceTelemetry();
    }
}
