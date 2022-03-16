// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.team8410.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX; // this is real bot


import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;




public class DrivetrainSubsystem extends SubsystemBase {

  // in our robot we have two motors on left
  private final WPI_TalonSRX m_left1 = new WPI_TalonSRX(Constants.LEFT_MOTOR_1_CANID);
  private final WPI_TalonSRX m_left2 = new WPI_TalonSRX(Constants.LEFT_MOTOR_2_CANID);

  

  MotorControllerGroup leftSide = new MotorControllerGroup(m_left1, m_left2);
  
  // in our robot we have two motors on right
  private final WPI_TalonSRX m_right1 = new WPI_TalonSRX(Constants.RIGHT_MOTOR_1_CANID);
  private final WPI_TalonSRX m_right2 = new WPI_TalonSRX(Constants.RIGHT_MOTOR_2_CANID);

  MotorControllerGroup rightSide = new MotorControllerGroup(m_right1, m_right2);

  // we use diffrential drive
  private final DifferentialDrive m_robotDrive = new DifferentialDrive(leftSide, rightSide);



 
  /** Creates a new DrivetrainSubsystem. */
  public DrivetrainSubsystem() 
  {
  
  }
//allows to input speed and turn for the robot
  public void driveTheBot (double speed, double turn)
  {

    m_robotDrive.arcadeDrive(speed,turn);
    
  }

  

 
  @Override
  public void periodic()
   {
  
    // This method will be called once per scheduler run ''
  }
}
