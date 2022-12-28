package org.firstinspires.ftc.teamcode.opmodes;

import com.arcrobotics.ftclib.command.CommandOpMode;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.commands.ArmMotorCommand;
import org.firstinspires.ftc.teamcode.commands.GrabberCommand;
import org.firstinspires.ftc.teamcode.commands.MecanumDpadCommand;
import org.firstinspires.ftc.teamcode.commands.MecanumDriveCommand;
import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.GrabberServoSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.GrabberSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.TelemetryUpdateSubsystem;


@TeleOp(name = "Match Teleop", group = "1")
public class MatchTeleop extends CommandOpMode {
    // FtcDashboard dashboard = FtcDashboard.getInstance();

    GamepadEx driver, operator;
    MecanumDriveSubsystem mecanumDriveSubsystem;
    GrabberSubsystem grabberSubsystem;
    GrabberServoSubsystem garbberServoSubsystem;
    TelemetryUpdateSubsystem telemetryUpdateSubsystem;

    @Override
    public void initialize() {
        driver = new GamepadEx(gamepad1);
        operator = new GamepadEx(gamepad2);
        mecanumDriveSubsystem = new MecanumDriveSubsystem(hardwareMap, telemetry);
        ArmSubsystem armSubsystem = new ArmSubsystem(hardwareMap, telemetry);
        GrabberSubsystem grabberSubsystem = new GrabberSubsystem(hardwareMap);
        GrabberServoSubsystem grabberServoSubsystem = new GrabberServoSubsystem(hardwareMap, telemetry);
        telemetryUpdateSubsystem = new TelemetryUpdateSubsystem(telemetry);

        MecanumDriveCommand driveMecanumCommand = new MecanumDriveCommand(mecanumDriveSubsystem,
                () -> -driver.getLeftY(),
                () -> driver.getRightX(),
                () -> driver.getLeftX(),
                () -> driver.getButton(GamepadKeys.Button.B),
                telemetry);

        driver.getGamepadButton(GamepadKeys.Button.DPAD_LEFT)
                .and(driver.getGamepadButton(GamepadKeys.Button.B))
                .whenActive(new MecanumDpadCommand(mecanumDriveSubsystem,
                        0.0, 0.0, -1.0, telemetry))
                .whenInactive(driveMecanumCommand);

        driver.getGamepadButton(GamepadKeys.Button.DPAD_LEFT)
                .and(driver.getGamepadButton(GamepadKeys.Button.B).negate())
                .whenActive(new MecanumDpadCommand(mecanumDriveSubsystem,
                        0.0, 0.0, -0.333,
                        telemetry))
                .whenInactive(driveMecanumCommand);

        driver.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT)
                .and(driver.getGamepadButton(GamepadKeys.Button.B).negate())
                .whenActive(new MecanumDpadCommand(mecanumDriveSubsystem,
                        0.0, 0.0, 0.333,
                        telemetry))
                .whenInactive(driveMecanumCommand);

        driver.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT)
                .and(driver.getGamepadButton(GamepadKeys.Button.B))
                .whenActive(new MecanumDpadCommand(mecanumDriveSubsystem,
                        0.0, 0.0, 1.0,
                        telemetry))
                .whenInactive(driveMecanumCommand);

        driver.getGamepadButton(GamepadKeys.Button.DPAD_UP)
                .and(driver.getGamepadButton(GamepadKeys.Button.B).negate())
                .whenActive(new MecanumDpadCommand(mecanumDriveSubsystem,
                        -0.333, 0.0, 0.0,
                        telemetry))
                .whenInactive(driveMecanumCommand);

        driver.getGamepadButton(GamepadKeys.Button.DPAD_UP)
                .and(driver.getGamepadButton(GamepadKeys.Button.B))
                .whenActive(new MecanumDpadCommand(mecanumDriveSubsystem,
                        -1.0, 0.0, 0.0,
                        telemetry))
                .whenInactive(driveMecanumCommand);

        driver.getGamepadButton(GamepadKeys.Button.DPAD_DOWN)
                .and(driver.getGamepadButton(GamepadKeys.Button.B).negate())
                .whenActive(new MecanumDpadCommand(mecanumDriveSubsystem,
                        0.333, 0.0, 0.0,
                        telemetry))
                .whenInactive(driveMecanumCommand);

        driver.getGamepadButton(GamepadKeys.Button.DPAD_DOWN)
                .and(driver.getGamepadButton(GamepadKeys.Button.B))
                .whenActive(new MecanumDpadCommand(mecanumDriveSubsystem,
                        1.0, 0.0, 0.0,
                        telemetry))
                .whenInactive(driveMecanumCommand);

        ArmMotorCommand armMotorCommand = new ArmMotorCommand(armSubsystem,
                () -> operator.getLeftY(),
                () -> operator.getButton(GamepadKeys.Button.RIGHT_BUMPER));

//        GrabberCommand grabberCommand = new GrabberCommand(grabberSubsystem,
//                () -> operator.getRightY());

        operator.getGamepadButton(GamepadKeys.Button.A).whileHeld(new InstantCommand(grabberServoSubsystem::closingServo, grabberServoSubsystem));
        operator.getGamepadButton(GamepadKeys.Button.Y).whileHeld(new InstantCommand(grabberServoSubsystem::openingServo, grabberServoSubsystem));


        // Arm commands
        // - Right Bumper - Go up continuous
        // - Left Bumper - Go down continuous
        // - X Button - Arm to pickup
        // - A Button - Level 1
        // - B Button - Level 2
        // - Y Button - Level 3

        schedule(driveMecanumCommand, armMotorCommand
                //grabberCommand
        );
        register(telemetryUpdateSubsystem);
    }
}

