// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.team8410.subsystems;

//import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX; // this is real bot
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX; // this is the practice bot


import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;




public class DrivetrainSubsystem extends SubsystemBase {

  // in our robot we have two motors on left

  private final WPI_VictorSPX m_left1 = new WPI_VictorSPX(0);
  private final WPI_VictorSPX m_left2 = new WPI_VictorSPX(1);
  MotorControllerGroup leftSide = new MotorControllerGroup(m_left1, m_left2);


  // in our robot we have two motors on right
  private final WPI_VictorSPX m_right1 = new WPI_VictorSPX(2);
  private final WPI_VictorSPX m_right2 = new WPI_VictorSPX(3);
  MotorControllerGroup rightSide = new MotorControllerGroup(m_right1, m_right2);

  // we use diffrential drive
  private final DifferentialDrive m_robotDrive = new DifferentialDrive(leftSide, rightSide);

  // we use two joysticks.
  private final Joystick m_stick = new Joystick(0);

 
  /** Creates a new DrivetrainSubsystem. */
  public DrivetrainSubsystem() 
  {

  }

  public void driveTheBot ()
  {
    m_robotDrive.arcadeDrive(m_stick.getRawAxis(4) * 0.75, m_stick.getRawAxis(1)*-0.85);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
