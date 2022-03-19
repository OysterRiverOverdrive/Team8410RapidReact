// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.team8410.commands;

import frc.robot.team8410.subsystems.DrivetrainSubsystem;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Encoder;
import frc.robot.Constants;

public class AutoDriveCommand extends CommandBase 
{
  private  DrivetrainSubsystem drive;
  private double speedForDrive;
  private double distanceInInches;

  private final Encoder leftSideEncoder = new Encoder(Constants.LEFT_ENCODER_0, Constants.LEFT_ENCODER_1);
  private final Encoder rightSideEncoder = new Encoder(Constants.RIGHT_ENCODER_2, Constants.RIGHT_ENCODER_3);


  
  
  
  public AutoDriveCommand(DrivetrainSubsystem drv, double speed, double distance) 
  {
    drive = drv;
    speedForDrive = speed;
    distanceInInches = distance;
    addRequirements(drv);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() 
  {
    leftSideEncoder.reset();
    rightSideEncoder.reset();
    leftSideEncoder.setDistancePerPulse(Math.PI*6/360);// pi * wheel dia / counts per a revulution
    rightSideEncoder.setDistancePerPulse(Math.PI*6/360);// pi * wheel dia / counts per a revulution
    System.out.println("encoders reset and distance set");
    
  }

  

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() 
  {
    drive.driveTheBot(speedForDrive,0);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) 
  {
    System.out.println("The command has ended");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() 
  {
    boolean retVal = false;

   if ((leftSideEncoder.getDistance() + rightSideEncoder.getDistance())/2 <= distanceInInches)
    {
       retVal = true; 
       drive.driveTheBot(0, 0);
       System.out.println("i have reached the distance i had to go");
    }

    return retVal;
  }
}
