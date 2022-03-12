// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.team8410.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.team8410.subsystems.DrivetrainSubsystem;

public class AutoCommand extends CommandBase {
  /** Creates a new AutoCommand. */
  private final DrivetrainSubsystem driveSub;
  public AutoCommand(DrivetrainSubsystem drive) 
  {
    driveSub = drive;
    addRequirements(drive);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() 
  {

    driveSub.autoDriveStraight(20, 0.5);
    driveSub.autoDriveStraight(50, -0.5);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
