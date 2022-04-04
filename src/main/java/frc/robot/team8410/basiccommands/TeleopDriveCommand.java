// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.team8410.commands;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.team8410.subsystems.DrivetrainSubsystem;
import frc.robot.Constants;


public class TeleopDriveCommand extends CommandBase {
  /** Creates a new TeleopDriveCommand. */

  private final DrivetrainSubsystem driveSub;
  private final SlewRateLimiter slrForTurn = new SlewRateLimiter(4.5);// from 2 to 2.5 to 3.5 to 4.0 to 4.5
  private final SlewRateLimiter slrForDrive = new SlewRateLimiter(2.2);

  private final Joystick m_stick = new Joystick(Constants.DRIVER_PORT);
  private boolean isTeleOp = false;

  public TeleopDriveCommand( DrivetrainSubsystem drive)  
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

    if(isTeleOp)
    {
      double turn = slrForTurn.calculate(m_stick.getRawAxis(4)*0.75);
      //double speed = slrForDrive.calculate (m_stick.getRawAxis(1)*-0.85);
      double speed = slrForDrive.calculate (m_stick.getRawAxis(1)*-0.95);//todo new value .95

      driveSub.driveTheBot(speed, turn);
    }

  }

  public void setTeleOpMode(boolean teleOPMode)
  {
    isTeleOp = teleOPMode;
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
