package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.controller.PIDController;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;

public class ElevatorMoveCommand extends CommandBase {
    private ArmSubsystem armSubsystem;
    private double TargetInches;
    private Telemetry telemetry;
    private boolean RunOnce = false;
    private PIDController ElevatorPID;
    private static final double DISTANCEPERPULSE = 0.0093356918238994;

    public ElevatorMoveCommand(ArmSubsystem armSubsystem, Telemetry telemetry, double TargetInches) {
        this.armSubsystem = armSubsystem;
        this.TargetInches = TargetInches;
        this.telemetry = telemetry;
        addRequirements(armSubsystem);
    }

    @Override
    public void initialize() {
        ElevatorPID = new PIDController(1,1.6,0);
        ElevatorPID.setTolerance(0.5);
    }

    public double translateEncodeToInch(){
        return Math.abs(armSubsystem.readElevatorEncoder() * DISTANCEPERPULSE);
    }

    @Override
    public void execute() {
        double output = ElevatorPID.calculate(translateEncodeToInch(), TargetInches);
        telemetry.addData("output (power)", output);
        telemetry.addData("encoder to inches", translateEncodeToInch());
        armSubsystem.ArmMotor.set(output);
    //  armSubsystem.goToInches(TargetInches);
    }

    @Override
    public boolean isFinished() {
        return ElevatorPID.atSetPoint();
//        return armSubsystem.atTargetPosition();
    }

    @Override
    public void end(boolean interrupted) {
        armSubsystem.setPower(0);
    }
}
