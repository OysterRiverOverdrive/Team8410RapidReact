// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.team8410.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.team8410.subsystems.IntakeArmSubSystem;
// import frc.robot.team8410.sensors.PotSensor;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RaiseIntakeCmd extends CommandBase 
{
  private AnalogInput pot;
  private IntakeArmSubSystem intakeArmSubSys;
  private double speed;
  private double prevValue;

  /** Creates a new RaiseIntakeCmd. */
  public RaiseIntakeCmd(IntakeArmSubSystem intakeSubSystem, AnalogInput potSensor) 
  {
    intakeArmSubSys = intakeSubSystem;
    pot = potSensor;
    speed = 0;
    addRequirements(intakeSubSystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    speed = 0.3;
    prevValue = 10000;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute()
  {
   
    intakeArmSubSys.rise(0.9);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) 
  {
    intakeArmSubSys.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() 
  {
    boolean retVal = false;
    double currPOTVoltage = pot.getAverageVoltage();
    SmartDashboard.putNumber("POT Value intake rise", currPOTVoltage);
    SmartDashboard.putNumber("POT Value MAX", Constants.INTAKE_POT_HIGH_STOP);

    if((prevValue == currPOTVoltage)||(currPOTVoltage >= Constants.INTAKE_POT_HIGH_STOP))
    {
      retVal = true;
     
    }

    prevValue = currPOTVoltage;
    return retVal;
  }
}
