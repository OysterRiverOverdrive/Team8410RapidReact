// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.team8410.sensors;


/** Add your docs here. */
public class SensorValues 
{
    // holds the LIDAR value in inches
    private double lidarDistanceInches;

    // holds the ultrasonic distance reading on the left side
    private double ultrasonicLeftInches;

     // holds the ultrasonic distance reading on the right side
    private double ultrasonicRightInches;
    
     // holds the ultrasonic distance reading on the back of the robot
    private double ultrasonicBackInches;

    // holds true if there is a red ball in front of the TCS sensor else  false  
    private String ballColor_TSCSensor;

     // holds true if there is a red ball in front of the Rev sensor else  false  
    private String ballColor_RevSensor;

   // Getters and Setters
    public double getLidarDistanceInches() 
    {
        return lidarDistanceInches;
    }
   
    public String getBallColor_TSCSensor() {
        return ballColor_TSCSensor;
    }

    public void setBallColor_TSCSensor(String ballColor_TSCSensor) {
        this.ballColor_TSCSensor = ballColor_TSCSensor;
    }

    public String getBallColor_RevSensor() {
        return ballColor_RevSensor;
    }

    public void setBallColor_RevSensor(String ballColor_RevSensor) {
        this.ballColor_RevSensor = ballColor_RevSensor;
    }

    public double getUltrasonicBackInches() {
        return ultrasonicBackInches;
    }

    public void setUltrasonicBackInches(double ultrasonicBackInches) {
        this.ultrasonicBackInches = ultrasonicBackInches;
    }

    public double getUltrasonicRightInches() {
        return ultrasonicRightInches;
    }

    public void setUltrasonicRightInches(double ultrasonicRightInches) {
        this.ultrasonicRightInches = ultrasonicRightInches;
    }

    public double getUltrasonicLeftInches() {
        return ultrasonicLeftInches;
    }

    public void setUltrasonicLeftInches(double ultrasonicLeftInches) {
        this.ultrasonicLeftInches = ultrasonicLeftInches;
    }
}
