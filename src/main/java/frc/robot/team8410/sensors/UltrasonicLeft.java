package frc.robot.team8410.sensors;

import edu.wpi.first.wpilibj.AnalogInput;

public class UltrasonicLeft {

   // TODO : use constants.java please
   private static final int kUltrasonicPort = 1;
   private final static AnalogInput m_ultrasonic1 = new AnalogInput(kUltrasonicPort);

   // TODO: are we sure these values are inches
   public static double getLeftSensorDistance() {
      return m_ultrasonic1.getValue() * 0.0536;

   }

}
