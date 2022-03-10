// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.team8410.commands;

import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.team8410.subsystems.TwoStageClimber;

public class TwoStageDescendCmd extends CommandBase {
  /** Creates a new TwoStageDescendCmd. */
  private TwoStageClimber twoStage;
  private DutyCycleEncoder twoStageEncoder;

  public TwoStageDescendCmd(TwoStageClimber twoStage) {
    twoStageEncoder = new DutyCycleEncoder(Constants.HANGER_TWO_STAGE_ENCODER_PORT);
    twoStageEncoder.setDistancePerRotation(1.0);
    System.out.println("Command called &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
    this.twoStage = twoStage;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    System.out.println("Command executed");
    twoStage.descend();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    boolean retVal = false;
    System.out.println(Math.abs(twoStageEncoder.getDistance()));

    if(Math.abs(twoStageEncoder.getDistance()) >= 5)
    {
      //TODO check # of rotations needed
      twoStage.stopMotor();
      retVal = true;
    }
    return retVal;
  }

}
