// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.team8410.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.team8410.subsystems.IntakeArmSubSystem;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RaiseIntakeCmd extends CommandBase 
{
  private AnalogInput m_potentiometer;
  private IntakeArmSubSystem intakeArmSubSys;
  private double currPOTVoltage ;
  private double speed;

  /** Creates a new RaiseIntakeCmd. */
  public RaiseIntakeCmd(IntakeArmSubSystem intakeSubSystem, AnalogInput POT) 
  {
    intakeArmSubSys = intakeSubSystem;
    m_potentiometer = POT;
    speed = 0;
    //m_potentiometer = new AnalogInput(Constants.INTAKE_ARM_POT_PORT_ID);
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

    SmartDashboard.putNumber("POT", currPOTVoltage);
    
    if(currPOTVoltage <= Constants.INTAKE_POT_LOW_CAUTION)
    {
      // Make the speed more positive until reaching (max speed)
      speed = speed + .01;
      if(speed >= Constants.INTAKE_RAISE_MAX_SPEED)
         speed = Constants.INTAKE_RAISE_MAX_SPEED;
    }

    else if(currPOTVoltage > Constants.INTAKE_POT_LOW_CAUTION && currPOTVoltage <= Constants.INTAKE_POT_HIGH_CAUTION)
    {
      speed = Constants.INTAKE_RAISE_MAX_SPEED;
    }
    if(currPOTVoltage >= Constants.INTAKE_POT_HIGH_CAUTION)
    {
      // Slowly bring the speed back to zero.
      speed = speed - 0.01 ;
      if(speed <=0)
        speed = 0;
      
    }

    SmartDashboard.putNumber("Speed", speed); //TODO Better Name?
 
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

    if(currPOTVoltage >= 1.9001)// this should go to constants
    {

      retVal = true;
      intakeArmSubSys.stop();

    }
    return retVal;
    
  }
}
