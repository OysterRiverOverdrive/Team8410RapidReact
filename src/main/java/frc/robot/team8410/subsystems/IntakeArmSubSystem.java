// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.team8410.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX; 



public class IntakeArmSubSystem extends SubsystemBase 
{

  private final WPI_TalonSRX intakeLifterMotor = new WPI_TalonSRX(Constants.INTAKE_LIFTER_MOTOR_CANID);  

  /** Creates a new IntakeRiseSubSystem. */
  public IntakeArmSubSystem() 
  {

  }

  @Override
  public void periodic() 
  {
    // This method will be called once per scheduler run
  }


  // this will run the motor
  public void rise(double speed)
  {
    intakeLifterMotor.set(speed);
  }


  // stop the motor
  public void stop()
  {
    intakeLifterMotor.stopMotor();
  }

}
