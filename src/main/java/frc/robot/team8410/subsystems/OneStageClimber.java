// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.team8410.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class OneStageClimber extends SubsystemBase {
  /** Creates a new OneStageClimber. */
  PWMVictorSPX oneStageMotor1 = new PWMVictorSPX(Constants.ONE_STAGE_LEFT_HANGER_MOTOR_CANID);
  PWMVictorSPX oneStageMotor2 = new PWMVictorSPX(Constants.ONE_STAGE_RIGHT_HANGER_MOTOR_CANID);
  MotorControllerGroup oneStageGroup = new MotorControllerGroup(oneStageMotor1, oneStageMotor2);


  
  public OneStageClimber() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void extend(){
    oneStageGroup.set(0.4);
    //TODO verify rotation and speed

  }

  public void descend(){
    oneStageGroup.set(-0.4);
    //TODO verify rotation and speed

  }

  public void stopMotor(){
    oneStageGroup.stopMotor();
  }
}