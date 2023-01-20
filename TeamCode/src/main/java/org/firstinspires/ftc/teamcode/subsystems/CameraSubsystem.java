package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.robotcore.external.tfod.TfodCurrentGame.LABELS;

import android.util.Log;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.ArrayList;
import java.util.List;

public class CameraSubsystem extends SubsystemBase {

    private static final String TAG = "ROBOT";
     private static final String TFOD_MODEL_ASSET = "PowerPlay.tflite";
      private static final String TFOD_MODEL_ASSET_CUSTOM = "PowerPlay-10868-Model1.tflite";
//    private static final String TFOD_MODEL_FILE = "/sdcard/FIRST/models/model1a.tflite";
    private static final String TFOD_MODEL_FILE = "/sdcard/FIRST/models/model1a.tflite";
    private static final String VUFORIA_KEY = "AaxuxUD/////AAABmb6BPHbGkEpZjScMUCBO6ohC2fNW8mzdoCyNq88xLv1mCfKF0KPmUv908XDWyk03Dp4WPAU+R9fI12VuDPmb5NNyImi8TuGjcpcSxlqNzEIzgbZhB439ArVfgDf8VWjgRoaN4780DSnavH/ws5vsBAm/A+zSi79qIAtMcntnrsMW0BZqqtzfBf9t3L1YBCfWbtUt8jUEK4bAP4thlqcrSYTH/qbTOg0Hih+ZWHulci8Zj2MQB8JBLCak1r+w1WGK0BCSTA/kJZZwu2rywOqLer0JGRJa69+K1NGwtcypabGXGVoJfCCoL+eP01HDGol+z6GqmqqQXTYY2dvwbt10ZePBJLV+M1+0gEKS6byj2o4O";
    private static final String[] Labeles = {
            "1 Bolt",
            "2 Bulb",
            "3 Panel"
    };
    private static final String[] Labels_Custom_ZoneOnly = {
            "Z1",
            "Z2",
            "Z3"
    };

    private static final String[] CustomLabeles = {
            "BlueStack",
            "Pole",
            "RedStack",
            "Z1",
            "Z2",
            "Z3"
    };

    public enum DriveDirection {
        Unknown,
        One,
        Two,
        Three
    }

    private VuforiaLocalizer vuforia;
    private TFObjectDetector tfObjectDetector;
    private Telemetry telemetry;
    private HardwareMap hardwareMap;
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
        int tfodMonitorViewId = this.hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", this.hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfodParameters.minResultConfidence = 0.25f;
        tfodParameters.isModelTensorFlow2 = true;
        tfodParameters.inputSize = 300;
        tfObjectDetector = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);


        // Use loadModelFromAsset() if the TF Model is built in as an asset by Android Studio
        // Use loadModelFromFile() if you have downloaded a custom team model to the Robot Controller's FLASH.
//        tfObjectDetector.loadModelFromAsset(TFOD_MODEL_ASSET, Labeles);
//        tfObjectDetector.loadModelFromFile(TFOD_MODEL_FILE, CustomLabeles);
//        tfObjectDetector.loadModelFromAsset(TFOD_MODEL_ASSET_CUSTOM, CustomLabeles);
        tfObjectDetector.loadModelFromFile(TFOD_MODEL_FILE, CustomLabeles);
        if (tfObjectDetector == null) Log.e("ROBOT", "initTfod: tfObjectDetector is null");
        else Log.d("ROBOT", "initTfod: tfObjectDetector is NOT null");
    }


    public CameraSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {
        this.hardwareMap = hardwareMap;
        this.telemetry = telemetry;
        initVuforia();
        initTfod();
    }

    public void cameraActicate() {
        tfObjectDetector.activate();
    }

    public void cameraDeactivate() {
        tfObjectDetector.deactivate();
    }

    public void detectObjects() {
        List<Recognition> newUpdatedRecognitions = null;
        if (tfObjectDetector != null) {
            newUpdatedRecognitions = tfObjectDetector.getUpdatedRecognitions();
            if (newUpdatedRecognitions != null) updatedRecognitions = newUpdatedRecognitions;
        }
        if (updatedRecognitions != null) {
            this.telemetry.addData("# Objects Detected", updatedRecognitions.size());
            for (Recognition recognition : removeObjects(updatedRecognitions)) {
                telemetry.addData("Image", "%s (%.0f %% Conf.)", recognition.getLabel(), recognition.getConfidence() * 100);
                telemetry.addData("left", recognition.getLeft());
                telemetry.addData("with", recognition.getWidth());
            }
        } else {
            telemetry.addLine("camera is called but no changes");
        }
    }

    public List<Recognition> removeObjects(List<Recognition> uncleanReconditions) {
        List<Recognition> cleanRecognitions = new ArrayList<Recognition>();
        if (uncleanReconditions != null) {
            for (Recognition recognition : uncleanReconditions) {
                if (recognition.getWidth() < 100 && recognition.getLabel().charAt(0) == 'Z') {
                    cleanRecognitions.add(recognition);
                }
            }
        }
        return cleanRecognitions;
    }

//    public DriveDirection directionChooser() {
//        List<Recognition> cleanedUpList = removeObjects(updatedRecognitions);
//        if (cleanedUpList != null && cleanedUpList.size() > 0) {
//            Recognition recognition = cleanedUpList.get(0);
//            Log.i(TAG, "directionChooser: getLabel=" + recognition.getLabel());
////            if (recognition.getLabel() == "1 Bolt" || recognition.getLabel() == "Z1") {
//                if (recognition.getLabel() == "1 Bolt") {
//                    telemetry.addLine("1yxyx");
//                return DriveDirection.One;
//            } else if (recognition.getLabel() == "2 Bulb") {
//                telemetry.addLine("2txyx");
//                return DriveDirection.Two;
//            } else if (recognition.getLabel() == "3 Panel") {
//                telemetry.addLine("3ttttt");
//                return DriveDirection.Three;
//            }
//        }
//        return DriveDirection.Unknown;
//    }

    public DriveDirection directionChooser() {
        List<Recognition> cleanedUpList = removeObjects(updatedRecognitions);
        if (cleanedUpList != null && cleanedUpList.size() > 0) {
            Recognition recognition = cleanedUpList.get(0);
            Log.i(TAG, "directionChooser: getLabel=" + recognition.getLabel());
            if (recognition.getLabel() == "Z1") {
                telemetry.addLine("one dd is called");
                return DriveDirection.One;
            } else if (recognition.getLabel() == "Z2") {
                telemetry.addLine("two dd is called");
                return DriveDirection.Two;
            } else if (recognition.getLabel() == "Z3") {
                telemetry.addLine("three dd is called");
                return DriveDirection.Three;
            }
        }
        return DriveDirection.Unknown;
    }

    @Override
    public void periodic() {
        detectObjects();
//        if (updatedRecognitions != null) {
//            telemetry.addData("Recog.size()",updatedRecognitions.size());
//            for (Recognition recog : updatedRecognitions) {
//                telemetry.addData("Label:", recog.getLabel());
//            }
//        }

    }
}
