// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.team8410.diagnostics;


import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.team8410.commands.TeleopDriveCommand;

import frc.robot.team8410.sensors.SensorValues;
import frc.robot.team8410.subsystems.DrivetrainSubsystem;
//import frc.robot.team8410.subsystems.HangerSubsystem;

/** Add your docs here. */
public class Diagnostics8410 
{

 
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


    
    }
       
   }



