package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;

public class ElevatorToGroundCommand extends CommandBase {
    private ArmSubsystem armSubsystem;

    public ElevatorToGroundCommand(ArmSubsystem armSubsystem){
        this.armSubsystem = armSubsystem;
        addRequirements(armSubsystem);
    }


    @Override
    public void execute() {
        armSubsystem.setPower(-0.9);
    }

    @Override
    public boolean isFinished(){
        return armSubsystem.atHome();
    }

    @Override
    public void end(boolean interrupted){
        armSubsystem.setPower(0);
    }
}
