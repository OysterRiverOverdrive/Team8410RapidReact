// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.team8410.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

public class IntakeRollerSubsystem extends SubsystemBase {
  /** Creates a new IntakeRollerSubsystem. */
  
  private final WPI_VictorSPX m_roller = new WPI_VictorSPX(6);
  private final Joystick joystick = new Joystick(0);
  
  public IntakeRollerSubsystem() {


    
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
