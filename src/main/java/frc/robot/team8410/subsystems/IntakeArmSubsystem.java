// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.team8410.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class IntakeArmSubsystem extends SubsystemBase {

  private DutyCycleEncoder intakearm = new DutyCycleEncoder(1);

  private final TalonSRX m_lift = new TalonSRX(4); 

  private final Joystick m_stick = new Joystick(5);

  /** Creates a new IntakeArmSubsystem. */
  public IntakeArmSubsystem() 
  {

  }

  @Override
  public void periodic()
   {

    intakearm.setDistancePerRotation(1);

    // This method will be called once per scheduler run
  }


   public void raiseArm()
   {
    //
   }


   public void lowerArm()
   {

   }

}
