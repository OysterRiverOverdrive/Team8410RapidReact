// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.team8410.commands;

import frc.robot.team8410.subsystems.DrivetrainSubsystem;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class AutoDriveCommand extends CommandBase {
  /** Creates a new AutoDriveCommand. */
  private final DrivetrainSubsystem drive;
  double speedForDrive;
  double distanceInInches;
  
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
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() 
  {
    drive.autoDriveStraight(distanceInInches, speedForDrive);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
