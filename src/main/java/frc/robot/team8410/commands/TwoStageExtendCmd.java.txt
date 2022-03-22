// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.team8410.commands;

import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.team8410.subsystems.TwoStageClimber;

public class TwoStageExtendCmd extends CommandBase {
  /** Creates a new TwoStageExtendCmd. */
  private TwoStageClimber twoStage;
  private DutyCycleEncoder twoStageEncoder;
  private double twoStageExtendDist;
 
  public TwoStageExtendCmd(TwoStageClimber twoStage, double twoStageExtendDist) {
    twoStageEncoder = new DutyCycleEncoder(Constants.HANGER_TWO_STAGE_ENCODER_PORT);
    twoStageEncoder.setDistancePerRotation(Math.PI * 0.787204); //circumference of two stage is 0.787204 pi inches
    System.out.println("Command called &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
    this.twoStage = twoStage; 
    this.twoStageExtendDist = twoStageExtendDist;

    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    twoStageEncoder.reset();
    twoStageEncoder.isConnected();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    System.out.println("Command executed");
    twoStage.extend();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    boolean retVal = false;
    System.out.println(Math.abs(twoStageEncoder.getDistance()));


    if(Math.abs(twoStageEncoder.getDistance()) >= twoStageExtendDist) //two stage needs to extend 28.5 in
    {
     //TODO check # of rotations needed
      twoStage.stopMotor();
      retVal = true;
    }
    return retVal;
  }
}
