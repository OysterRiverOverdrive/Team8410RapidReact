package frc.robot.team8410.sensors;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class UltrasonicRight
{
  private static final int kUltrasonicPort = 2;
  private final static AnalogInput m_ultrasonic1 = new AnalogInput(kUltrasonicPort);
 
 
   public static void RIGHT_SENSOR_DISTANCE()
   {
      double dist_test1 = m_ultrasonic1.getValue()*0.0536;
      SmartDashboard.putNumber("Ultrasonic Right", dist_test1);
      
      
   } 
 
   
}
