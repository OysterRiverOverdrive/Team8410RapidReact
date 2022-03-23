// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.team8410.commands;

import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.team8410.sensors.WinchEncoder;
import frc.robot.team8410.subsystems.WinchSubsystem;

public class UnwindWinchCommand extends CommandBase {
  /** Creates a new UnwindWinch. */
  private WinchSubsystem winch;
  private DutyCycleEncoder winchEncoder;
  private double unwindWinchDist;
  
  public UnwindWinchCommand(WinchSubsystem winch, double winchDist) 
  {
    winchEncoder = new DutyCycleEncoder(Constants.HANGER_WINCH_ENCODER_PORT);  //TODO move this out to robot container
    winchEncoder.setDistancePerRotation(1.0);
    
    this.winch = winch; 
    unwindWinchDist = winchDist;
    addRequirements(winch);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    winchEncoder.reset();
    winchEncoder.isConnected();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    System.out.println("Command executed");
    winch.unWind();

  }

  // Called once the command ends or is interrupted.
  @Override

  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    boolean retVal = false;
   


    if(Math.abs(winchEncoder.getDistance()) >= unwindWinchDist)// change and mesure encoder value
    {
      //TODO check # of rotations needed
      winch.stopMotor();
      retVal = true;
    }
    return retVal;
  }

}
