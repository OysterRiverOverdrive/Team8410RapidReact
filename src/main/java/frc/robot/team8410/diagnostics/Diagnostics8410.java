// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.team8410.diagnostics;


import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.team8410.commands.TeleopDriveCommand;
import frc.robot.team8410.commands.hangCmd;
import frc.robot.team8410.commands.stopHangCmd;
import frc.robot.team8410.sensors.SensorValues;
import frc.robot.team8410.subsystems.DrivetrainSubsystem;
import frc.robot.team8410.subsystems.HangerSubsystem;

/** Add your docs here. */
public class Diagnostics8410 
{

   private final HangerSubsystem hanger = new HangerSubsystem();
   private final hangCmd hang = new hangCmd(hanger);
   private final stopHangCmd stopHang = new stopHangCmd(hanger);
 
   private final DrivetrainSubsystem drivetrain = new DrivetrainSubsystem();
   private final TeleopDriveCommand teleopCommand = new TeleopDriveCommand(drivetrain);

   private final PowerDistribution powerDistribution = new PowerDistribution(0, PowerDistribution.ModuleType.kCTRE);
   //powerDistribution

   public Diagnostics8410()
   {

   }

   //TODO 
   // based on the values of the passed in obj reference Sensor Values 
   //Program the addressable LEDs

   public void setLEDsAndDashboard(SensorValues sensorValues)
   {

   }

   public void bakeChocolateGoldfish( SensorValues sensorValues) {

      
       SmartDashboard.putNumber("Lidar Distance", sensorValues.getLidarDistanceInches());
       SmartDashboard.putNumber("Ultrasonic Back", sensorValues.getUltrasonicBackInches());
       SmartDashboard.putNumber("Ultrasonic Left", sensorValues.getUltrasonicLeftInches());
       SmartDashboard.putNumber("Ultrasonic Right", sensorValues.getUltrasonicRightInches());


     // displaying subsystems
       SmartDashboard.putData(hanger);
       SmartDashboard.putData(drivetrain);

      //display commands
      SmartDashboard.putData(teleopCommand);
      SmartDashboard.putData(hang);
      SmartDashboard.putData(stopHang);
      SmartDashboard.putData(teleopCommand);

    
      //displaying power distribution
      SmartDashboard.putNumber("Temperature", powerDistribution.getTemperature());
      SmartDashboard.putNumber("Total Current", powerDistribution.getTotalCurrent());
      SmartDashboard.putNumber("Voltage", powerDistribution.getVoltage());
      SmartDashboard.putNumber("Power", powerDistribution.getTotalPower());
    
// make this to a constant
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


}
