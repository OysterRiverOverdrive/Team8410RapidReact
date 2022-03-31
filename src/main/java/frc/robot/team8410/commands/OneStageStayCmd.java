// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.team8410.commands;
import frc.robot.Constants;
import frc.robot.team8410.subsystems.OneStageClimber;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.Joystick;


import edu.wpi.first.wpilibj2.command.CommandBase;

public class OneStageStayCmd extends CommandBase {
  /** Creates a new OneStageStay. */
  private final OneStageClimber oneStage;
  private final DutyCycleEncoder enc;
  private final double encSet;
  public OneStageStayCmd(OneStageClimber oneStangeClimb, DutyCycleEncoder encoder, double setEncValue) {
    // Use addRequirements() here to declare subsystem dependencies.
    oneStage = oneStangeClimb;
    enc = encoder;
    encSet = setEncValue;

    addRequirements(oneStage);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() 
  {
    Joystick driver = new Joystick(Constants.DRIVER_PORT);
    JoystickButton release = new JoystickButton(driver, 4);// change button
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() 

  {
    for(double i =enc.get();i != encSet; i= enc.get())
    {
      oneStage.descend();
    }

    
  
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    Joystick driver = new Joystick(Constants.DRIVER_PORT);
    JoystickButton release = new JoystickButton(driver, 4);

     boolean retVal = false;
     
      if (release.getAsBoolean() == true)
      {
        retVal = true;
      }

    return retVal;
  }
}
