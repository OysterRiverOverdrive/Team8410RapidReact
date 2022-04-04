// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.team8410.autocommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.team8410.subsystems.IntakeRollerSubsystem;
import edu.wpi.first.wpilibj.Timer;


public class RollerPushAuto extends CommandBase {
  private Timer timer = new Timer();
  /** Creates a new RollerPush. */
  IntakeRollerSubsystem roller;
  public RollerPushAuto(IntakeRollerSubsystem rollers) {
    roller = rollers;
    addRequirements(rollers);
    // Use addRequirements() here to declare subsystem dependencies.
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
  public void execute() {
    roller.push();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) 
  {
    roller.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    boolean retVal = false;

    double currTime = timer.get();

   
    if (currTime >= 3)
    {
      retVal = true;
    }else
    {
      retVal = false;
    }

    
    return retVal;
  }
}
