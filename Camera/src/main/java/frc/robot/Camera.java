package frc.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import frc.robot.FunctionsThatShouldBeInTheJDK;

/**
 * Add your docs here.
 */
public class Camera {

    
    public static final double robotHeight = 10d;
    public static final double visionTargetHeight = 28.5d;
    public static final double cameraAngle = 58d;
    
    //Calibration
    public static double focalLength = 0d;
    public static final double testDistance = 0d;

    public static final double targetHeight = 1d;
    public static final double targetWidth = 1d;
    
    public static final double heightWidthRatio = targetHeight / targetWidth;

    public static double getRectHeightFromArea () {

        return Math.sqrt(getArea() * heightWidthRatio);
    } 
    
    public static void calibrateF () {
        focalLength = getRectHeightFromArea() * testDistance / targetHeight;
    }

    /***
     *  states for the state of the camera's led
     */
    public static enum ledState {
        on, off, blink, pipeline;

    };
    

    /***
     * returns the Camera network table
     * @return Camera NetworkTable
     */
    public static NetworkTable getTable () {

        return NetworkTableInstance.getDefault().getTable("limelight-chaos");
        
    }

    /***
     * get an entry from the camera network table, access with getDouble()
     * @param name the name of the limelight variable
     * @return the NetworkTableEntry value from the table
     */
    public static NetworkTableEntry getEntry (String name) {

        return getTable().getEntry(name);

    }

    /**
     * 0 = driver, 1 = camera processing
     * @param index - index
     */
    public static void changePipeline (int index) {
        getEntry("pipeline").setNumber(index);
    }

    /***
     * Gets the distance from the camera to the vision target using their heights, the angle of the camera, 
     * and the read ty vartical-angle-from-center-to-target value from the limelight
     * @return the horizontal distance from camera to vision target
     */
    public static double getDistanceFromAngle () {

        return (visionTargetHeight - robotHeight) / (Math.tan( Math.toRadians(getEntry("ty").getDouble(69) + cameraAngle)));

    }

    /***
     * tx: horizontal angle from the center of the camera to the vision target
     * @return angle in degrees
     */
    public static double GetHorizontalAngle () {

        return getEntry("tx").getDouble(0);

    }

    public static double getVerticalAngle () {
        return getEntry("ty").getDouble(0);
    }

    public static double getArea () {
        return getEntry("ta").getDouble(0);
    }

    public static double getDistanceFromArea() {

        return targetHeight * focalLength / getRectHeightFromArea();

    }

    /**
     * sets the state for the camera leds
     * @param set on, off, blink, pipeline
     */
    public static void lightsOn(ledState set) {
       

        if (set.equals(ledState.on)) {
            getEntry("ledMode").setNumber(3);
        } else if (set.equals(ledState.blink)) {
            getEntry("ledMode").setNumber(2); 
        } else if (set.equals(ledState.off)){
            getEntry("ledMode").setNumber(1);
        } else {
            getEntry("ledMode").setNumber(0);
        }
    }
}
