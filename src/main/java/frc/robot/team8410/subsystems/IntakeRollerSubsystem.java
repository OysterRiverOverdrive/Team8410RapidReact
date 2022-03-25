// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.team8410.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeRollerSubsystem extends SubsystemBase {
  /** Creates a new IntakeRollerSubsystem. */

  private final WPI_TalonSRX m_roller = new WPI_TalonSRX(6);

  public IntakeRollerSubsystem() {

  }
  public void pull()
  {
    m_roller.set(-0.8);// was 0.8 speed
  }

  public void push()
  {
    m_roller.set(0.8);
  }

  public void stop() {
    m_roller.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
