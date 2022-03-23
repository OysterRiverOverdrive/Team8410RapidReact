// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.team8410.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class TwoStageClimber extends SubsystemBase {
  /** Creates a new TwoStageClimber. */
  WPI_TalonSRX twoStageMotor = new WPI_TalonSRX(Constants.TWO_STAGE_HANGER_MOTOR_CANID);

  public TwoStageClimber() {
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void extend() {
    twoStageMotor.set(0.4);
    // TODO verify rotation and speed
  }

  public void descend() {
    twoStageMotor.set(-0.4);
    // TODO verify rotation and speed
  }

  public void stopMotor() {
    twoStageMotor.stopMotor();
  }
}
