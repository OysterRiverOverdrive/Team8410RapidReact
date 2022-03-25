// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.team8410.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class WinchSubsystem extends SubsystemBase 
{
  /** Creates a new Winch. */
  private final WPI_TalonSRX winchMotor = new WPI_TalonSRX(Constants.WINCH_MOTOR_CANID);

  public WinchSubsystem() 
  {
    
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  // wind motor
  public void wind() 
  {
    winchMotor.set(0.4);
  }

  // unwind
  public void unWind() 
  {
    winchMotor.set(-0.4);
  }

  // stop the winch motor

  public void stopMotor() {
    winchMotor.stopMotor();
  }

}
