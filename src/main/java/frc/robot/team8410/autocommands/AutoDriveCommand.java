// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.team8410.autocommands;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.team8410.subsystems.AutoDrivetrainSubSystem;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoDriveCommand extends CommandBase {
  private AutoDrivetrainSubSystem drive;
  private double speedForDrive;
  private Timer timer = new Timer();

  public AutoDriveCommand(AutoDrivetrainSubSystem drv, double speed) {
    drive = drv;
    speedForDrive = speed;
    
    addRequirements(drv);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.reset();
    timer.start();   

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() 
  {
    SmartDashboard.putString("autoCommand","driving ");
    drive.driveTheAutoBot(-0.5, 0);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    System.out.println("The command has ended");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    boolean retVal = false;

    double currTime = timer.get();

    SmartDashboard.putNumber("time auto command", currTime);

    if (currTime >= 2.9)
    {
      retVal = true;
    }else
    {
      retVal = false;
    }

    
    return retVal;
  }
}
