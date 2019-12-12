package org.firstinspires.ftc.teamcode;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.detectors.DogeCVDetector;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.FoundationPipeline.Analysis;
import org.firstinspires.ftc.teamcode.FoundationPipeline.Pipeline;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;


@TeleOp(name = "CV Simulator", group = "Auto")
public class CVDisplay extends OpMode {

    private DogeCVDetector detector = new DogeCVDetector() {
        @Override
        public Mat process(Mat rgba) {
            Mat m = Pipeline.process(rgba);
            Pipeline.doFoundations=false;
            Pipeline.doStones=false;
            //Point stonePos = Analysis.skystonePosition(Pipeline.stones,m);
//            telemetry.addData("pos", stonePos.x +" "+stonePos.y);
            telemetry.update();

            Imgproc.resize(m, m, new Size(720, 560));
            return m;
        }
        @Override
        public void useDefaults() {}
    };

    @Override
    public void init() {
        telemetry.setAutoClear(true);
        // Set up detector
        detector.init(hardwareMap.appContext, CameraViewDisplay.getInstance()); // Initialize it with the app context and camera
        detector.enable();
    }
    /*
     * Code to run REPEATEDLY when the driver hits INIT
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {

    }

    /*
     * Code to run REPEATEDLY when the driver hits PLAY
     */
    @Override
    public void loop() {
        telemetry.update();
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
        if(detector != null) detector.disable(); //Make sure to run this on stop!
    }
}
