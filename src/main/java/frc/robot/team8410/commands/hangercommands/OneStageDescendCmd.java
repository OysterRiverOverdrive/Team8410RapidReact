// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot.team8410.commands.hangercommands;

import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
//import frc.robot.team8410.sensors.OneStageLeftEncoder;
import frc.robot.team8410.subsystems.OneStageClimber;

public class OneStageDescendCmd extends CommandBase {
  /** Creates a new OneStageExtendCmd. */
  private OneStageClimber oneStage;
  private DutyCycleEncoder oneStageLeftEncoder;
  private double dist;
  private double previousValue;
  


  public OneStageDescendCmd(OneStageClimber oneStage, double EncValue, DutyCycleEncoder enc) {
  //oneStageLeftEncoder = new DutyCycleEncoder(Constants.HANGER_ONE_STAGE_LEFT_ENCODER_PORT);

  //oneStageLeftEncoder.setDistancePerRotation(Math.PI * 0.787402);

  //System.out.println("Command called &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
  this.oneStage = oneStage;
  dist = EncValue;
  oneStageLeftEncoder = enc;
 addRequirements(oneStage);
  // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() 
  {
    oneStageLeftEncoder.reset();
    oneStageLeftEncoder.isConnected();
    previousValue = 1000.00;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    oneStage.descend();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    SmartDashboard.putString("one stage decend", "done");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() 
  {
    boolean retVal = false;
    double value = oneStageLeftEncoder.get();
    
    SmartDashboard.putNumber("two stage enc descnd", value);
    SmartDashboard.putNumber("Previous Value", value);

    if((value == previousValue) || (Math.abs(value) >= dist))// fix encoder value
    {
      //TODO check # of rotations needed
      oneStage.stopMotor();
      retVal = true;
    }
    previousValue = value;
    return retVal;
  }
}
