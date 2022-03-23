package frc.robot.team8410.sensors;

import edu.wpi.first.wpilibj.AnalogInput;

public class UltrasonicRight {

   // TODO : use constants.java please
   private static final int kUltrasonicPort = 2;
   private final static AnalogInput m_ultrasonic1 = new AnalogInput(kUltrasonicPort);

   // TODO: are we sure these values are inches
   public static double getRightSensorDistance() {
      return m_ultrasonic1.getValue() * 0.0536;

   }

}
