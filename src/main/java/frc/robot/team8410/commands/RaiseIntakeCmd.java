// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.team8410.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.team8410.subsystems.IntakeArmSubSystem;
import edu.wpi.first.wpilibj.AnalogInput;



public class RaiseIntakeCmd extends CommandBase 
{

  private AnalogInput m_potentiometer;
  private IntakeArmSubSystem intakeArmSubSys;
  private double currPOTVoltage ;
  private double speed;

  /** Creates a new RaiseIntakeCmd. */
  public RaiseIntakeCmd(IntakeArmSubSystem intakeSubSystem) 
  {
    intakeArmSubSys = intakeSubSystem;
    m_potentiometer = new AnalogInput(Constants.INTAKE_ARM_POT_PORT_ID);
    addRequirements(intakeSubSystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() 
  {
    

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute()
  {
    currPOTVoltage = m_potentiometer.getAverageVoltage();
    
    if(currPOTVoltage <= 10)
    {
      speed = speed + .01;
    }

    else if(currPOTVoltage > 10 || currPOTVoltage <= 19)
    {
      speed = .6;
    }
    if(currPOTVoltage >= 20)
    {
      speed = speed -1 ;
    }
 
    intakeArmSubSys.rise(speed); 

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() 
  {
    boolean retVal = false;
    currPOTVoltage = m_potentiometer.getAverageVoltage();

    if(currPOTVoltage >= 40)
    {

      retVal = true;
    }
    return retVal;
  }
}
