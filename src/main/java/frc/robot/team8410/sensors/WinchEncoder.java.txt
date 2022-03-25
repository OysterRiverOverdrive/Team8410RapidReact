// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.team8410.sensors;

import edu.wpi.first.wpilibj.DutyCycleEncoder;
import frc.robot.Constants;

/** Add your docs here. */
public class WinchEncoder {
  private double winchExtendDist;

  public double getWinchEncoder() {
    DutyCycleEncoder winchEncoder = new DutyCycleEncoder(Constants.HANGER_WINCH_ENCODER_PORT);
    winchEncoder.setDistancePerRotation(1.0);
    winchExtendDist = Math.abs(winchEncoder.getDistance());
    return winchExtendDist;
  }

  public void encoderReset() {
    DutyCycleEncoder winchEncoder = new DutyCycleEncoder(Constants.HANGER_WINCH_ENCODER_PORT);
    winchEncoder.reset();
    winchEncoder.isConnected();
  }

}
