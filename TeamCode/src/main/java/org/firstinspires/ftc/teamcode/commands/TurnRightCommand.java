package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.ImuSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;

public class TurnRightCommand extends CommandBase {
    private MecanumDriveSubsystem mecanumDriveSubsystem;
    private ImuSubsystem imuSubsystem;
    private double target;
    private boolean RightTurn = false;
    private boolean LeftTurn = false;
    private boolean Stop = false;
    private double direction;

    public TurnRightCommand(MecanumDriveSubsystem mecanumDriveSubsystem, ImuSubsystem imuSubsystem, double target, double direction){
        this.imuSubsystem = imuSubsystem;
        this.mecanumDriveSubsystem = mecanumDriveSubsystem;
        this.target = target;
        this.direction = direction;
        addRequirements(mecanumDriveSubsystem, imuSubsystem);
    }

    public boolean turnRightCheck(){
        if (direction > 0){
            return RightTurn = true;
        }
        else {
            return RightTurn = false;
        }
    }
    public boolean turnLeftCheck(){
        if (direction < 0) {
            return LeftTurn = true;
        }
        else {
            return LeftTurn = false;
        }
    }

    public void turnRight(){
        if (RightTurn = true && Math.abs(Math.abs(imuSubsystem.Heading())- Math.abs(target)) < target) {
            mecanumDriveSubsystem.Drive(0.0, 3.0, 0.0);
        }
        else{
            stop();
        }
    }

    public void turnLeft(){
        if (LeftTurn = true && Math.abs(Math.abs(imuSubsystem.Heading())- Math.abs(target)) < target) {
            mecanumDriveSubsystem.Drive(0.0, -3.0, 0.0);
        }
        else{
            stop();
        }
    }

    public void stop(){
        if (Stop = true){
            mecanumDriveSubsystem.Drive(0, 0, 0);
        }
    }

    public boolean TurnChooser(){
        if (direction > 0){
            return RightTurn = true;
        }
        else {
            return RightTurn = false;
        }
    }

    @Override
    public void execute() {
        TurnChooser();
        turnRight();
        turnLeft();
    }

    @Override
    public boolean isFinished() {
        return Math.abs(Math.abs(imuSubsystem.Heading()) - Math.abs(target)) > target;
    }

    @Override
    public void end(boolean interrupted) {
        stop();
    }
}
