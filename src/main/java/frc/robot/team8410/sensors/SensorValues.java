// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.team8410.sensors;

/** Add your docs here. */
public class SensorValues 
{

    // holds the LIDAR value in inches
    private int lidarDistanceInches;

    // holds the ultrasonic distance reading on the left side
    private double ultrasonicLeftInches;

     // holds the ultrasonic distance reading on the right side
    private double ultrasonicRightInches;
    
     // holds the ultrasonic distance reading on the back of the robot
    private double ultrasonicBackInches;

    // holds true if there is a red ball in front of the TCS sensor else  false  
    private boolean redBall_TSCSensor;

     // holds true if there is a blue ball in front of the TCS sensor else  false  
    private boolean blueBall_TSCSEnsor;

     // holds true if there is a red ball in front of the Rev sensor else  false  
    private boolean redBall_RevSensor;

     // holds true if there is a blue ball in front of the Rev sensor else  false  
    private boolean blueBall_revSensor;
   


   
   // Getters and Setters
   
    public int getLidarDistanceInches() 
    {
        return lidarDistanceInches;
    }
    
    public boolean isBlueBall_revSensor() 
    {
        return blueBall_revSensor;
    }
    
    public void setBlueBall_revSensor(boolean blueBall_revSensor)
    {
        this.blueBall_revSensor = blueBall_revSensor;
    }

    public boolean isRedBall_RevSensor() 
    {
        return redBall_RevSensor;
    }
    
    public void setRedBall_RevSensor(boolean redBall_RevSensor) 
    {
        this.redBall_RevSensor = redBall_RevSensor;
    }

    public boolean isBlueBall_TSCSEnsor() 
    {
        return blueBall_TSCSEnsor;
    }

    public void setBlueBall_TSCSEnsor(boolean blueBall_TSCSEnsor) 
    {
        this.blueBall_TSCSEnsor = blueBall_TSCSEnsor;
    }

    public boolean isRedBall_TSCSensor() 
    {
        return redBall_TSCSensor;
    }

    public void setRedBall_TSCSensor(boolean redBall_TSCSensor) 
    {
        this.redBall_TSCSensor = redBall_TSCSensor;
    }

    public double getUltrasonicBackInches() 
    {
        return ultrasonicBackInches;
    }

    public void setUltrasonicBackInches(double ultrasonicBackInches) 
    {
        this.ultrasonicBackInches = ultrasonicBackInches;
    }

    public double getUltrasonicRightInches()
    {
        return ultrasonicRightInches;
    }

    public void setUltrasonicRightInches(double ultrasonicRightInches)
    {
        this.ultrasonicRightInches = ultrasonicRightInches;
    }

    public double getUltrasonicLeftInches()
    {
        return ultrasonicLeftInches;
    }

    public void setUltrasonicLeftInches(double ultrasonicLeftInches)
    {
        this.ultrasonicLeftInches = ultrasonicLeftInches;
    }

    public void setLidarDistanceInches(int lidarDistanceInches) 
    {
        this.lidarDistanceInches = lidarDistanceInches;
    }

}
