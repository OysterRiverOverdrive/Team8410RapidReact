// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.team8410.commands.hangercommands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import edu.wpi.first.wpilibj.DutyCycleEncoder;
import frc.robot.team8410.subsystems.TwoStageClimber;
import edu.wpi.first.wpilibj.Timer;

public class KeepHangingTwoStageCommand extends CommandBase 
{
  private TwoStageClimber twoStage;
  private DutyCycleEncoder twoStageEncoder;
  private Timer timer;
  private double previousTime;
  private double time1;
  private double setValue;
  /** Creates a new KeepHangingTwoStageCommand. */
  public KeepHangingTwoStageCommand(TwoStageClimber twoStage,double setValue, DutyCycleEncoder enc)
  {
    this.twoStage = twoStage;
    twoStageEncoder = enc;
    timer = new Timer();
    this.setValue = Math.abs(setValue);
    addRequirements(twoStage);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() 
  {
    timer.reset();
    timer.start();  
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() 
  {
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) 
  {
    twoStage.stopMotor();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() 
  {
    double value = twoStageEncoder.get();
    value = Math.abs(value);

    if(value >= setValue)
      twoStage.stopMotor();
   
    if(setValue != value && (time1 - previousTime)>=1)
     twoStage.descend();
 
   previousTime = time1;

   return false;
  }
}
