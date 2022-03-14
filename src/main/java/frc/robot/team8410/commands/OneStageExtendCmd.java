// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.team8410.commands;

import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.team8410.subsystems.OneStageClimber;

public class OneStageExtendCmd extends CommandBase {
  /** Creates a new OneStageExtendCmd. */
  private OneStageClimber oneStage;
  private DutyCycleEncoder oneStageLeftEncoder;
  private DutyCycleEncoder oneStageRightEncoder;
  public OneStageExtendCmd(OneStageClimber oneStage) {
    oneStageLeftEncoder = new DutyCycleEncoder(Constants.HANGER_ONE_STAGE_LEFT_ENCODER_PORT);
    oneStageRightEncoder = new DutyCycleEncoder(Constants.HANGER_ONE_STAGE_RIGHT_ENCODER_PORT);
    oneStageLeftEncoder.setDistancePerRotation(Math.PI * 0.787402); //double check this value
    oneStageRightEncoder.setDistancePerRotation(Math.PI * 0.787402);
    System.out.println("Command called &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
    this.oneStage = oneStage;
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
    if(Math.abs(oneStageLeftEncoder.getDistance()) >= 3 && Math.abs(oneStageRightEncoder.getDistance()) >= 3)
    {
      //TODO check # of rotations needed
      oneStage.stopMotor();
      retVal = true;
    }
    return retVal;
  }
}
