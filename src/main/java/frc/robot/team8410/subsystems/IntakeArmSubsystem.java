// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.team8410.subsystems;

// import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX; 



public class IntakeArmSubsystem extends SubsystemBase {

  // private DutyCycleEncoder intakearm = new DutyCycleEncoder(1);

  private final WPI_TalonSRX shooterUp = new WPI_TalonSRX(5);
  private final WPI_TalonSRX shooterArm = new WPI_TalonSRX(5);

  // private final Joystick shooterUp = new Joystick(6);

  // private static final int kJoystickPort = 1;
  // // Sets shooter joystick port
  // private Joystick  m_joystick;
  // double m_joystick = new Joystick(kJoystickPort);
  // double joystickinput = m_joystick;
  
  @Override
  public void periodic() {
    
  }
  public void intakepull () {
    shooterUp.set(0.75);
    
  }
  public void intakepush () {
    shooterUp.set(-0.75);
    
  }
  public void intakeraise () {
    shooterArm.set(0.5);
    
  }
  public void intakedrop () {
    shooterArm.set(-0.5);
    
  }
  public void stopShoot () {
    shooterUp.set(0);
    // Stop shooter group
  }
}
