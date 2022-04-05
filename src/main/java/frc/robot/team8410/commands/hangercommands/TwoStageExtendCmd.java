// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.team8410.commands.hangercommands;

import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.team8410.subsystems.TwoStageClimber;

public class TwoStageExtendCmd extends CommandBase {
  /** Creates a new TwoStageExtendCmd. */
  private TwoStageClimber twoStage;
  private DutyCycleEncoder twoStageEnc;
  private double rotations;
  private double previousValue;


  
 
  public TwoStageExtendCmd(TwoStageClimber twoStage, double EncoderValue, DutyCycleEncoder twoStageEncoder) {
    //twoStageEncoder = new DutyCycleEncoder(Constants.HANGER_TWO_STAGE_ENCODER_PORT);
    
    this.twoStage = twoStage; 
    rotations = EncoderValue;
    twoStageEnc = twoStageEncoder;
    addRequirements(twoStage);

    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    twoStageEnc.reset();
    twoStageEnc.isConnected();
    previousValue = 1000.00;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute()
  {
        twoStage.extend();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) 
  {
    twoStage.stopMotor();
    SmartDashboard.putString("two stage extend", "done");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    boolean retVal = false;

    double value = twoStageEnc.get();
    SmartDashboard.putNumber("tow stage enc - descend", value);


    if((previousValue == value)||(Math.abs(value) >= rotations)) //two stage needs to extend 28.5 in need to fix encoder value
    {
      retVal = true;
    }
    previousValue= value;
    return retVal;
  }
}
