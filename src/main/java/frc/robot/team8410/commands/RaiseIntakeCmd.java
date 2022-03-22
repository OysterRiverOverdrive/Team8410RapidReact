package frc.robot.team8410.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.team8410.subsystems.IntakeArmSubSystem;
import frc.robot.team8410.sensors.PotSensor;

public class RaiseIntakeCmd extends CommandBase 
{
  private PotSensor pot;
  private IntakeArmSubSystem intakeArmSubSys;
  private double speed;

  /** Creates a new RaiseIntakeCmd. */
  public RaiseIntakeCmd(IntakeArmSubSystem intakeSubSystem, PotSensor potSensor) 
  {
    intakeArmSubSys = intakeSubSystem;
    pot = potSensor;
    speed = 0;
    addRequirements(intakeSubSystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() 
  {
    speed = 0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute()
  {
    double currPOTVoltage = pot.getPOTVoltage();
    
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
    double currPOTVoltage = pot.getPOTVoltage();

    if(currPOTVoltage >= Constants.INTAKE_POT_HIGH_STOP)
    {

      retVal = true;
      intakeArmSubSys.stop();

    }
    return retVal;
  }
}