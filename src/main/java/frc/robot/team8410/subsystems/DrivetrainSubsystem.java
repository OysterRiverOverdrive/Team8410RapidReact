// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.team8410.subsystems;

//import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX; // this is real bot
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX; // this is the practice bot

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.team8410.sensors.UltrasonicFront;



public class DrivetrainSubsystem extends SubsystemBase {

  // in our robot we have two motors on left

  private final WPI_VictorSPX m_left1 = new WPI_VictorSPX(0);
  private final WPI_VictorSPX m_left2 = new WPI_VictorSPX(1);

  //TODO 

  //ashish added Please use constants for CAN IDs

  private final Encoder leftSideEncoder = new Encoder(0, 1);
  



  MotorControllerGroup leftSide = new MotorControllerGroup(m_left1, m_left2);


  // in our robot we have two motors on right

  private final WPI_VictorSPX m_right1 = new WPI_VictorSPX(2);
  private final WPI_VictorSPX m_right2 = new WPI_VictorSPX(3);

  MotorControllerGroup rightSide = new MotorControllerGroup(m_right1, m_right2);

  // we use diffrential drive
  private final DifferentialDrive m_robotDrive = new DifferentialDrive(leftSide, rightSide);

  //we also use the slew controled to help mitigate jerking

   private final SlewRateLimiter slrForTurn ;//= new SlewRateLimiter(0.5);
   private final SlewRateLimiter slrForDrive ;


  // we use two joysticks.
  private final Joystick m_stick = new Joystick(0);

 
  /** Creates a new DrivetrainSubsystem. */
  public DrivetrainSubsystem() 
  {
    slrForTurn = new SlewRateLimiter(2);
    slrForDrive = new SlewRateLimiter(1.8);

    leftSideEncoder.setDistancePerPulse(Math.PI*6/360);// pi * wheel dia / counts per a revulution

  }

  public void driveTheBot ()
  {
    m_robotDrive.arcadeDrive(slrForTurn.calculate(m_stick.getRawAxis(4)*0.75),slrForDrive.calculate (m_stick.getRawAxis(1)*-0.85));
    System.out.println(slrForDrive.calculate(m_stick.getRawAxis(3)*-0.85));
   //m_robotDrive.arcadeDrive(m_stick.getRawAxis(2) * 0.75, m_stick.getRawAxis(3)*-0.85);
  
  }

  public void autoDriveStraight (double distanceToGo, double speed)
  {
    while (leftSideEncoder.getDistance()<distanceToGo)
    {
      m_robotDrive.arcadeDrive(speed, 0);
    }
    m_robotDrive.arcadeDrive(0, 0);

  }
  public void autoDriveStraight_until_wall(double targetDist)
  {
    double stopDistance = 25.0;
    double cautionDistance = 50.0;
    double maxDriveSpeed = 1.0;
    double speed = 0;
    double slope = 0;
    int approachAlgorithm = 0;
    // double intercept = 0;
    // slope = maxDriveSpeed/(cautionDistance-stopDistance);
    // intercept = -1*(slope*stopDistance);
    while (targetDist > stopDistance)
    {
      rightSide.setInverted(true);

      if (targetDist > cautionDistance) {
        // If the distance is greater than cautionDistance, go maxDriveSpeed.
        m_robotDrive.arcadeDrive(maxDriveSpeed, 0);
      } else if  (targetDist <= cautionDistance) {

        if (approachAlgorithm = 0) {
          // speed = (slope*targetDist)+intercept;
          // If less than or equal to cautionDistance, progressively go slower ... somehow.
        } else if (approachAlgorithm = 1){
        slope = maxDriveSpeed/((cautionDistance - stopDistance)*(cautionDistance - stopDistance));
        speed = slope*((targetDist-stopDistance)*(targetDist-stopDistance));
        
        } else {

        };
        m_robotDrive.arcadeDrive(speed, 0);

      } else {
        // If at or stop distance stop and exit loop.
        m_robotDrive.arcadeDrive(0, 0);
        break;
      }
      targetDist = UltrasonicFront.getFrontSensorDistance();
    }
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run ''
  }
}
