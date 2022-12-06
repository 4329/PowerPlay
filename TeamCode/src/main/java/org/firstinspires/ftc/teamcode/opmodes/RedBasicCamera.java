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
import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.TelemetryUpdateSubsystem;

@Autonomous(name = "Red Biasic Cammera", group = "2")
public class RedBasicCamera extends CommandOpMode{

        TelemetryUpdateSubsystem telemetryUpdateSubsystem;


        @Override
        public void initialize() {
            initVuforia();
            initTfod();

            telemetryUpdateSubsystem = new TelemetryUpdateSubsystem(telemetry);
//            DriveDistance driveDistance = new DriveDistance(mecanumDriveSubsystem,
//                    0, 0, -0.5, 24);
//            schedule(new SequentialCommandGroup(driveDistance.withTimeout(1500)));
            MecanumDriveSubsystem mecanumDriveSubsystem = new MecanumDriveSubsystem(hardwareMap, telemetry);

            DriveStrafeDistanceCommand driveRightStrafeCommand =
                    new DriveStrafeDistanceCommand(mecanumDriveSubsystem,
                            DriveStrafeDistanceCommand.Direction.RIGHT,24, telemetry);
            DriveStrafeDistanceCommand driveLeftStrafeCommand =
                    new DriveStrafeDistanceCommand(mecanumDriveSubsystem,
                            DriveStrafeDistanceCommand.Direction.LEFT,24, telemetry);
            DriveDistanceCommand driveForwardsCommand =
                    new DriveDistanceCommand(mecanumDriveSubsystem,
                            DriveDistanceCommand.DriveDirection.FORWARDS,24);
            DriveDistanceCommand driveBackwardsCommand =
                    new DriveDistanceCommand(mecanumDriveSubsystem,
                            DriveDistanceCommand.DriveDirection.BACKWARDS,7);
            DriveDistanceCommand driveStopCommand =
                    new DriveDistanceCommand(mecanumDriveSubsystem,
                            DriveDistanceCommand.DriveDirection.STOP,0);
            schedule(new SequentialCommandGroup(driveLeftStrafeCommand, driveBackwardsCommand, driveRightStrafeCommand, driveForwardsCommand));
            register(telemetryUpdateSubsystem);
        }

    /**
     * Initialize the Vuforia localization engine.
     */
    private void initVuforia() {
        /*
         * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
         */
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraName = hardwareMap.get(WebcamName.class, "Webcam 1");

        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);
    }

    /**
     * Initialize the TensorFlow Object Detection engine.
     */
    private void initTfod() {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfodParameters.minResultConfidence = 0.75f;
        tfodParameters.isModelTensorFlow2 = true;
        tfodParameters.inputSize = 300;
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);

        // Use loadModelFromAsset() if the TF Model is built in as an asset by Android Studio
        // Use loadModelFromFile() if you have downloaded a custom team model to the Robot Controller's FLASH.
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABELS);
        // tfod.loadModelFromFile(TFOD_MODEL_FILE, LABELS);
    }
    }
}
