// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.team8410.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.team8410.sensors.Ultrasonic;
import frc.robot.team8410.subsystems.DrivetrainSubsystem;

public class AutoDriveTargetCmd extends CommandBase {
  /** Creates a new TeleopDriveCommand. */

  private final DrivetrainSubsystem driveSub;
  private double targetDist;
  private Ultrasonic ultrasonicFront;

  public AutoDriveTargetCmd(DrivetrainSubsystem drive, Ultrasonic ultrasonicFront) {
    driveSub = drive;
    this.ultrasonicFront = ultrasonicFront;
    addRequirements(driveSub);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    targetDist = this.ultrasonicFront.getFrontSensorDistance();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    driveSub.autoDriveStraight_until_wall(targetDist, Constants.DRIVER_ASSIST_APPROACH_ALG_LINEAR);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
