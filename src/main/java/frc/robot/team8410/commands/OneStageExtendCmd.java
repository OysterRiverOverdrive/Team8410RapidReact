// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.team8410.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.team8410.subsystems.OneStageClimber;
import frc.robot.team8410.sensors.OneStageLeftEncoder;
import frc.robot.team8410.sensors.OneStageRightEncoder;

public class OneStageExtendCmd extends CommandBase {
  /** Creates a new OneStageExtendCmd. */
  private OneStageClimber oneStage;
  private OneStageLeftEncoder oneStageLeftEncoder;
  private OneStageRightEncoder oneStageRightEncoder;
  private double oneStageExtendDist;
  
  public OneStageExtendCmd(OneStageClimber oneStage, double oneStageExtendDist) {
    System.out.println("Command called &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
    this.oneStage = oneStage;
    this.oneStageExtendDist = oneStageExtendDist;

    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    oneStage.extend();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    boolean retVal = false;
    //System.out.println(Math.abs(twoStageEncoder.getDistance()));

    if(oneStageLeftEncoder.get1LeftSideEncoder() >= oneStageExtendDist 
    && oneStageRightEncoder.get1RightSideEncoder() >= oneStageExtendDist)
    {
      //TODO check # of rotations needed
      oneStage.stopMotor();
      retVal = true;
    }
    return retVal;
  }
}
