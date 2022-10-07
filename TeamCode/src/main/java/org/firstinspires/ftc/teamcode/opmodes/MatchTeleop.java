package org.firstinspires.ftc.teamcode.opmodes;

import com.arcrobotics.ftclib.command.CommandOpMode;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.commands.ArmMotorCommand;
import org.firstinspires.ftc.teamcode.commands.MecanumDriveCommand;
import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;


@TeleOp(name = "Match Teleop", group = "1")
public class MatchTeleop extends CommandOpMode {
    // FtcDashboard dashboard = FtcDashboard.getInstance();

    GamepadEx driver, operator;
    MecanumDriveSubsystem mecanumDriveSubsystem;

    @Override
    public void initialize() {
        driver = new GamepadEx(gamepad1);
        operator = new GamepadEx(gamepad2);
        mecanumDriveSubsystem = new MecanumDriveSubsystem(hardwareMap, telemetry);
        ArmSubsystem armSubsystem = new ArmSubsystem(hardwareMap);

        MecanumDriveCommand driveMecanumCommand = new MecanumDriveCommand(mecanumDriveSubsystem,
                () -> -driver.getLeftY(),
                () -> driver.getRightX(),
                () -> driver.getLeftX(),
                () -> driver.getButton(GamepadKeys.Button.LEFT_BUMPER));
        ArmMotorCommand armMotorCommand = new ArmMotorCommand(armSubsystem,
                () -> operator.getLeftX());

        // Arm commands
        // - Right Bumper - Go up continuous
        // - Left Bumper - Go down continuous
        // - X Button - Arm to pickup
        // - A Button - Level 1
        // - B Button - Level 2
        // - Y Button - Level 3

        schedule(driveMecanumCommand, armMotorCommand);
    }


}
