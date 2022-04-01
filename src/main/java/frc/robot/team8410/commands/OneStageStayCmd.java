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
  private Joystick driver;
  double previousValue;
  JoystickButton release;



  public OneStageStayCmd(OneStageClimber oneStangeClimb, double setEncValue,DutyCycleEncoder encoder) 
  {
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
    System.out.println("Initialized");
    enc.reset();
    previousValue = 1000;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() 
  {
     double currValue;
     currValue = Math.abs(enc.get());
     System.out.println("currValue " + currValue);

    if((currValue >= encSet) || (currValue == previousValue)) 
    {
       // we have reached the top stop motor
       oneStage.stopMotor();
    }

    if(Math.abs(currValue - encSet) >.5)// this value should be tested
    {
      // we have slipped lets start the motor again
      oneStage.descend();
    }
     previousValue = currValue;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) 
  {
    oneStage.stopMotor();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() 
  {
    // this command is canncelled by another button press
    return false;
  }
}
