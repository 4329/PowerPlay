package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;
import static org.firstinspires.ftc.robotcore.external.tfod.TfodCurrentGame.LABELS;
import static org.firstinspires.ftc.robotcore.external.tfod.TfodCurrentGame.TFOD_MODEL_ASSET;

import com.arcrobotics.ftclib.command.SubsystemBase;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.List;

public class CammeraSubsystem extends SubsystemBase {

    private static final String TFOD_MODEL_ASSET = "PowerPlay.tflite";

    private static final String[] Labels = {
            "1 Bolt",
            "2 Bulb",
            "3 Panel"
    };

    private static final String VUFORIA_KEY = "AaxuxUD/////AAABmb6BPHbGkEpZjScMUCBO6ohC2fNW8mzdoCyNq88xLv1mCfKF0KPmUv908XDWyk03Dp4WPAU+R9fI12VuDPmb5NNyImi8TuGjcpcSxlqNzEIzgbZhB439ArVfgDf8VWjgRoaN4780DSnavH/ws5vsBAm/A+zSi79qIAtMcntnrsMW0BZqqtzfBf9t3L1YBCfWbtUt8jUEK4bAP4thlqcrSYTH/qbTOg0Hih+ZWHulci8Zj2MQB8JBLCak1r+w1WGK0BCSTA/kJZZwu2rywOqLer0JGRJa69+K1NGwtcypabGXGVoJfCCoL+eP01HDGol+z6GqmqqQXTYY2dvwbt10ZePBJLV+M1+0gEKS6byj2o4O";

    private VuforiaLocalizer vuforia;
    private TFObjectDetector tfod;
    List<Recognition> updatedRecognitions;

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


    public CammeraSubsystem() {
        initVuforia();
        initTfod();
    }

    public void Updatecameravalues() {
        updatedRecognitions = tfod.getUpdatedRecognitions();
        if (updatedRecognitions != null) {
             telemetry.addData("# Objects Detected", updatedRecognitions.size());
            for (Recognition recognition : updatedRecognitions) {
                telemetry.addData("Image", "%s (%.0f %% Conf.)", recognition.getLabel(), recognition.getConfidence() * 100 );
            }
        }
        else{
            telemetry.addLine("camera is called but null");
        }
    }

    public void periodic(){
        Updatecameravalues();
    }

//            public void
//                if (tfod != null) {
//                    // getUpdatedRecognitions() will return null if no new information is available since
//                    // the last time that call was made.
//
//
//
//                        // step through the list of recognitions and display image position/size information for each one
//                        // Note: "Image number" refers to the randomized image orientation/number
//                        for (Recognition recognition : updatedRecognitions) {
//                            double col = (recognition.getLeft() + recognition.getRight()) / 2 ;
//                            double row = (recognition.getTop()  + recognition.getBottom()) / 2 ;
//                            double width  = Math.abs(recognition.getRight() - recognition.getLeft()) ;
//                            double height = Math.abs(recognition.getTop()  - recognition.getBottom()) ;
//
//
//                            telemetry.addData(""," ");
//                            telemetry.addData("- Position (Row/Col)","%.0f / %.0f", row, col);
//                            telemetry.addData("- Size (Width/Height)","%.0f / %.0f", width, height);
//                        }
//                        telemetry.update();
//                    }
}