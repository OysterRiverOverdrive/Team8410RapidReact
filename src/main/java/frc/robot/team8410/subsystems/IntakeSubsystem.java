// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.team8410.subsystems;

// import edu.wpi.first.wpilibj.Joystick;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DutyCycleEncoder;



public class IntakeSubsystem extends SubsystemBase {

  private DutyCycleEncoder intakearm = new DutyCycleEncoder(1);
  private double encoderDist = 0;
  private final WPI_TalonSRX shooterUp = new WPI_TalonSRX(5);
  private final WPI_TalonSRX shooterArm = new WPI_TalonSRX(5);

  // private final Joystick shooterUp = new Joystick(6);

  // private static final int kJoystickPort = 1;
  // // Sets shooter joystick port
  // private Joystick  m_joystick;
  // double m_joystick = new Joystick(kJoystickPort);
  // double joystickinput = m_joystick;
  
  @Override
  public void periodic() 
  {
     }
  public double getEncoder () {
    encoderDist = intakearm.get();
    return encoderDist;
  }
  public void intakePull () {
    shooterUp.set(0.75);
    
  }
  public void intakePush () {
    shooterUp.set(-0.75);
    
  }
  public void intakeRaise () {
    shooterArm.set(0.5);
    
  }
  public void intakeDrop () {
    shooterArm.set(-0.5);
    
  }
  public void stopShoot () {
    shooterUp.set(0);
    // Stop shooter group
  }
}
