// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.team8410.commands;

import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.team8410.subsystems.OneStageClimber;

public class OneStageExtendCmd extends CommandBase {
  /** Creates a new OneStageExtendCmd. */
  private OneStageClimber oneStage;
  private DutyCycleEncoder oneStageLeftEncoder;
  private double oneStageExtendRotation;
  
  public OneStageExtendCmd(OneStageClimber stageOne, double rotation, DutyCycleEncoder enc) {
    //oneStageLeftEncoder = new DutyCycleEncoder(Constants.HANGER_ONE_STAGE_LEFT_ENCODER_PORT);
    
  
    oneStage = stageOne;
    oneStageExtendRotation = rotation;
    oneStageLeftEncoder = enc;


    addRequirements(oneStage);
    // Use addRequirements() here to declare subsystem dependencies.

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() 
  {
    oneStageLeftEncoder.reset();
    oneStageLeftEncoder.isConnected();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    oneStage.extend();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    oneStage.stopMotor();
    SmartDashboard.putString("one stage", "one stage extend done");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    boolean retVal = false;
    // System.out.println(Math.abs(twoStageEncoder.getDistance()));

    if(Math.abs(oneStageLeftEncoder.get()) >= oneStageExtendRotation)// change number to real encoder number
    {
      //TODO check # of rotations needed
      
      retVal = true;
    }
    return retVal;
  }
}
