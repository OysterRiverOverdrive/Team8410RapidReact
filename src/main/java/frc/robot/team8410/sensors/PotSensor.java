// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.team8410.sensors;
import edu.wpi.first.wpilibj.AnalogInput;
/** Add your docs here. */
public class PotSensor {
    private static final int kpotentiometer = 0;
    private final static AnalogInput m_potentiometer = new AnalogInput(kpotentiometer);
   public double getPOTVoltage()
   {
      return  m_potentiometer.getAverageVoltage();
   } 
}
