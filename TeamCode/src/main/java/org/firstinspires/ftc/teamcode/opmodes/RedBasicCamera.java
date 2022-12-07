package org.firstinspires.ftc.teamcode.opmodes;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.robotcore.external.tfod.TfodCurrentGame.LABELS;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.teamcode.commands.DriveDistanceCommand;
import org.firstinspires.ftc.teamcode.commands.DriveStrafeDistanceCommand;
import org.firstinspires.ftc.teamcode.subsystems.CammeraSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.TelemetryUpdateSubsystem;

@Autonomous(name = "Red Biasic Cammera", group = "2")
public class RedBasicCamera extends CommandOpMode{

        TelemetryUpdateSubsystem telemetryUpdateSubsystem;
        CammeraSubsystem cammeraSubsystem;


        @Override
        public void initialize() {
            cammeraSubsystem = new CammeraSubsystem();
            telemetryUpdateSubsystem = new TelemetryUpdateSubsystem(telemetry);
            schedule(new SequentialCommandGroup());
            register(telemetryUpdateSubsystem, cammeraSubsystem);
        }
    }
