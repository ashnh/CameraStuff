
package frc.robot;

public class FunctionsThatShouldBeInTheJDK {

    public static double clamp (double value, double min, double max) {
        return Math.max((Math.min(max, value)), min);
    }

    public static double getSign(double value) {
        if (value == 0)
            return 0;
        return Math.abs(value) / value; 
    }

    public static int getSign(int value) {
        if (value == 0)
            return 0;
        return Math.abs(value) / value; 
    }

    /***
     * 
     * @param currentValue
     * @param targetValue
     * @param deadBand - The tolerance, both above and below the target
     * @return - True if the value is within a certain range above or below a target
     */
    public static boolean withinPlusOrMinus(double currentValue, double targetValue, double deadBand) {

        return Math.abs(currentValue - targetValue) < Math.abs(deadBand);

    }

    /**
     * 
     * @param value 
     * @param lowerBound 
     * @param upperBound
     * @return true if the given value is within a given range
     */
    public static boolean withinRange(double value, double lowerBound, double upperBound) {
        return (value >= lowerBound) && (value <= upperBound);
    }
    
}