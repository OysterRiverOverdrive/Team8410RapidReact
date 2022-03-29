// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.team8410.commands;

import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.team8410.subsystems.WinchSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class WindWinchCommand extends CommandBase {
  /** Creates a new WinchWindCommand. */
  private WinchSubsystem winch;
  private DutyCycleEncoder winchEncoder;
  private double rotations;

  public WindWinchCommand(WinchSubsystem winch, DutyCycleEncoder encoder, double encValue ) 
  {
    // winchEncoder = encoder;
    // this.rotations = rotations;
    this.winch = winch; 
    rotations = encValue;
    
    addRequirements(winch);

    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // winchEncoder.reset();
    // winchEncoder.isConnected();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
    winch.wind();
    }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) 
  {
    winch.stopMotor();
    SmartDashboard.putString("winch","winch wind is done");
    
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    boolean retVal = false;

     if(Math.abs(winchEncoder.get()) >= rotations)// change encoder value
     {
     //TODO check # of rotations needed
     winch.stopMotor();
     retVal = true;
     }
    return retVal;
  }
}
