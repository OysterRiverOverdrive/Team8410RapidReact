// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.team8410.sensors;

/** Add your docs here. */

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;

public class Color_RevroboticsVer3
{

  /**
   * Change the I2C port below to match the connection of your color sensor
   */
  private final I2C.Port i2cPort = I2C.Port.kOnboard;
  private final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);

  /**
   * A Rev Color Sensor V3 object is constructed with an I2C port as a 
   * parameter. The device will be automatically initialized with default 
   * parameters.
   */





  public boolean isRed()  // else its blue
  {
  
    boolean retVal = false;

    Color detectedColor = m_colorSensor.getColor();
    double redOverBlue = detectedColor.red/detectedColor.blue;


    /*if (redOverBlue > 3) {
      SmartDashboard.putBoolean("Ball is Red", true);
    
    } else {
      SmartDashboard.putBoolean("Ball is Blue", false);
    } */
  
    if (redOverBlue>3)
   {
    SmartDashboard.putString("Ball Color", "RED");
    retVal = true;
    } 
   else if(redOverBlue <1)
   {
    SmartDashboard.putString("Ball Color", "Blue");
    retVal = false;
   } 
   return retVal;
  } 
  

   

  
}
