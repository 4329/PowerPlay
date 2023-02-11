package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;

public class ElevatorMoveCommand extends CommandBase {
    private ArmSubsystem armSubsystem;
    private double TargetInches;

    public ElevatorMoveCommand(ArmSubsystem armSubsystem, double TargetInches){
        this.armSubsystem = armSubsystem;
        this.TargetInches = TargetInches;
    }

    @Override
    public void initialize() {
        armSubsystem.goToInches(TargetInches);
    }

    @Override
    public boolean isFinished() {
        return armSubsystem.atTargetPosition();
    }

}
