// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.team8410.sensors;

import edu.wpi.first.wpilibj.DutyCycleEncoder;
import frc.robot.Constants;

/** Add your docs here. */
public class OneStageLeftEncoder {
  private double oneStageExtendDist;

  public double get1LeftSideEncoder() {
    DutyCycleEncoder oneStageLeftEncoder = new DutyCycleEncoder(Constants.HANGER_ONE_STAGE_LEFT_ENCODER_PORT);
    oneStageLeftEncoder.setDistancePerRotation(Math.PI * 0.787402);
    oneStageExtendDist = Math.abs(oneStageLeftEncoder.getDistance());
    return oneStageExtendDist;
  }
}
