// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.team8410.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.Joystick;

public class DrivetrainSubsystem extends SubsystemBase {

  // in our robot we have two motors on left
  private final TalonSRX m_left1 = new TalonSRX(0);
  private final TalonSRX m_left2 = new TalonSRX(1);
  //MotorControllerGroup liftSide = new MotorControllerGroup(m_left1, m_left2);

  // in our robot we have two motors on right
  private final TalonSRX m_right1 = new TalonSRX(2);
  private final TalonSRX m_right2 = new TalonSRX(3);
  //MotorControllerGroup m_right = new MotorControllerGroup(m_right1, m_right2);

  // we use diffrential drive
  //private final DifferentialDrive m_robotDrive = new DifferentialDrive(m_left, m_right);

  // we use two joysticks.
  private final Joystick m_stick = new Joystick(0);
  /** Creates a new DrivetrainSubsystem. */
  public DrivetrainSubsystem() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
