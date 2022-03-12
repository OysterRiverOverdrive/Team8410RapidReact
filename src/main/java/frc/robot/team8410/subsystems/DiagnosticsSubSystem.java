// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.team8410.subsystems;


import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.team8410.commands.TeleopDriveCommand;
import frc.robot.team8410.sensors.SensorValues;
import frc.robot.team8410.subsystems.DrivetrainSubsystem;
import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;


import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DiagnosticsSubSystem extends SubsystemBase {
  /** Creates a new DiagnosticsSubSystem. */

  private final SensorValues sensorValues;


  private final PowerDistribution powerDistribution = new PowerDistribution(0, PowerDistribution.ModuleType.kCTRE);
  
  private final AddressableLED m_led= new AddressableLED(4);
  private final AddressableLEDBuffer m_ledBuffer= new AddressableLEDBuffer(72);


  public DiagnosticsSubSystem() 
  {

   sensorValues = new SensorValues(); 

  

   

    // Reuse buffer
    // Default to a length of 60, start empty output
    // Length is expensive to set, so only set it once, then just update data
  
     
     m_led.setLength(m_ledBuffer.getLength());

   


   }
  @Override
  public void periodic() 
  {
    // This method will be called once per scheduler run


    SmartDashboard.putNumber("Lidar Distance", sensorValues.getLidarDistanceInches());
    SmartDashboard.putNumber("Ultrasonic Back", sensorValues.getUltrasonicBackInches());
    SmartDashboard.putNumber("Ultrasonic Left", sensorValues.getUltrasonicLeftInches());
    SmartDashboard.putNumber("Ultrasonic Right", sensorValues.getUltrasonicRightInches());



 
   //displaying power distribution
   SmartDashboard.putNumber("Temperature", powerDistribution.getTemperature());
   SmartDashboard.putNumber("Total Current", powerDistribution.getTotalCurrent());
   SmartDashboard.putNumber("Voltage", powerDistribution.getVoltage());
   SmartDashboard.putNumber("Power", powerDistribution.getTotalPower());
 

   if (powerDistribution.getTemperature() > 35) {
   SmartDashboard.putBoolean("Temp is High", false);
 } else {
   SmartDashboard.putBoolean("Temp is Ok", true);
 }


   if (powerDistribution.getTotalCurrent() < 20) {
   SmartDashboard.putBoolean("Current is High", false);
 } else {
   SmartDashboard.putBoolean("Current is Fine", true);
 }


    if (powerDistribution.getVoltage() > 1 ) {
   SmartDashboard.putBoolean("Voltage is High", true);
 } else {
   SmartDashboard.putBoolean("Voltage is Fine", false);
 }

 
   if (powerDistribution.getTotalPower() < 1) {
   SmartDashboard.putBoolean("Power is Fine", true);
 } else {
   SmartDashboard.putBoolean("Power is High", false);


 
 SmartDashboard.putString("Testing Shuffleboard", "Testing Now");


  }
}

  private void setRBG(int index, int r, int b, int g)
  {


    /**
     * if temp > 35 leds 3,4,5,6,7,8 are red
     * if poer > 50 leds 89765 sre blue
     * 
     * 
     */

     






    
    /*for (int i = 0; i < m_ledBuffer.getLength(); i++) {
      // Sets the specified LED to the RGB values for red
      m_ledBuffer.setRGB(i, 255, 0, 0); */
   //}
   
   m_led.setData(m_ledBuffer);



  

 }



  

public void bakeVanillaGoldfish()
{
   // do   led



   if (powerDistribution.getTotalPower() < 1) {


    for (var i = 0; i < m_ledBuffer.getLength(); i++) {
    
      m_ledBuffer.setRGB(i, 255, 0, 0);
   }


  } else {

    for (var i = 0; i < m_ledBuffer.getLength(); i++) {
      
    }
  }


  if (powerDistribution.getTemperature() > 35) {

    for (int i = 3; i < 9; i++) {

      m_ledBuffer.setRGB(i, 255, 0, 0);

    }  

  } else {
    
    for (int i = 3; i < 9; i++) {

      m_ledBuffer.setRGB(i, 0, 255, 0);
    }
  }


  


  if (powerDistribution.getTotalPower() > 50) {


    for (int i = 8; i < 13; i++) {

      m_ledBuffer.setRGB(i, 0, 0, 255);

    }

  } else {

  

    for (int i = 8; i < 13; i++) {

      m_ledBuffer.setRGB(i, 127, 0, 128);
    }
   
  }

  
}




}
