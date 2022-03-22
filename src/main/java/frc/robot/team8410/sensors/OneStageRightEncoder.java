// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.team8410.sensors;

import edu.wpi.first.wpilibj.DutyCycleEncoder;
import frc.robot.Constants;

/** Add your docs here. */
public class OneStageRightEncoder {
  private double oneStageExtendDist;
  public double get1RightSideEncoder(){
    DutyCycleEncoder oneStageRightEncoder = new DutyCycleEncoder(Constants.HANGER_ONE_STAGE_RIGHT_ENCODER_PORT);
  oneStageRightEncoder.setDistancePerRotation(Math.PI * 0.787402);
  oneStageExtendDist = Math.abs(oneStageRightEncoder.getDistance());
  return oneStageExtendDist;
  }
  
}
