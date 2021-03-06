// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.team8410.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class OneStageClimber extends SubsystemBase 
{
  /** Creates a new OneStageClimber. */
  WPI_TalonSRX oneStageMotor1 = new WPI_TalonSRX(Constants.ONE_STAGE_LEFT_HANGER_MOTOR_CANID);
  WPI_TalonSRX oneStageMotor2 = new WPI_TalonSRX(Constants.ONE_STAGE_RIGHT_HANGER_MOTOR_CANID);
  MotorControllerGroup oneStageGroup = new MotorControllerGroup(oneStageMotor1, oneStageMotor2);

  public OneStageClimber() 
  {
    oneStageMotor2.setInverted(true);
    oneStageMotor2.setNeutralMode(NeutralMode.Brake); // set this to brake
    oneStageMotor1.setNeutralMode(NeutralMode.Brake); // set this to brake
  }

  @Override
  public void periodic() 
  {
    // This method will be called once per scheduler run
  }

  public void extend() 
  {
    oneStageGroup.set(1);//  changed to 100 %
  }

  public void descend() 
  {
    oneStageGroup.set(-1); // changed to 100 %
  }

  public void stopMotor() 
  {
    oneStageGroup.stopMotor();
  }
}
