// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot.team8410.commands;

import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.team8410.subsystems.OneStageClimber;

public class OneStageDescendCmd extends CommandBase {
  /** Creates a new OneStageExtendCmd. */
  private OneStageClimber oneStage;
  private DutyCycleEncoder oneStageLeftEncoder;
  private DutyCycleEncoder oneStageRightEncoder;
  private double oneStageDescendDist;

  public OneStageDescendCmd(OneStageClimber oneStage, double oneStageDescendDist) {
  oneStageLeftEncoder = new DutyCycleEncoder(Constants.HANGER_ONE_STAGE_LEFT_ENCODER_PORT);
  oneStageRightEncoder = new DutyCycleEncoder(Constants.HANGER_ONE_STAGE_RIGHT_ENCODER_PORT);
  oneStageLeftEncoder.setDistancePerRotation(Math.PI * 0.787402);
  oneStageRightEncoder.setDistancePerRotation(Math.PI * 0.787402);
  System.out.println("Command called &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
  this.oneStage = oneStage;
  this.oneStageDescendDist = oneStageDescendDist;
  // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    oneStage.descend();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    boolean retVal = false;
    //System.out.println(Math.abs(twoStageEncoder.getDistance()));

    if(Math.abs(oneStageLeftEncoder.getDistance()) >= oneStageDescendDist && Math.abs(oneStageRightEncoder.getDistance()) >= oneStageDescendDist)
    {
      //TODO check # of rotations needed
      oneStage.stopMotor();
      retVal = true;
    }
    return retVal;
  }
}
