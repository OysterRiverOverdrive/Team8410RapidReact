// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.team8410.commands;

import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj2.command.CommandBase;
//import frc.robot.team8410.sensors.TwoStageEncoder;
import frc.robot.team8410.subsystems.TwoStageClimber;

public class TwoStageDescendCmd extends CommandBase {
  /** Creates a new TwoStageDescendCmd. */
  private TwoStageClimber twoStage;
  private DutyCycleEncoder twoStageEncoder;
  private double rotations;

  public TwoStageDescendCmd(TwoStageClimber twoStage,double DescendDist, DutyCycleEncoder enc) {
    //twoStageEncoder = new DutyCycleEncoder(Constants.HANGER_TWO_STAGE_ENCODER_PORT);
    //enc.setDistancePerRotation(Math.PI * 0.787402);

    twoStageEncoder = enc;
    rotations = DescendDist;
    this.twoStage = twoStage;
    addRequirements(twoStage);



    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() 
  {
    twoStageEncoder.reset();
    twoStageEncoder.isConnected();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() 
  {
    twoStage.descend();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) 
  {
    twoStage.stopMotor();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    boolean retVal = false;

    if(Math.abs(twoStageEncoder.get()) >= rotations) //two stage needs to descend 28.5 in need to change encoder values
    {
      // TODO check # of rotations needed
      //twoStage.stopMotor();
      retVal = true;
    }
    return retVal;
  }

}
