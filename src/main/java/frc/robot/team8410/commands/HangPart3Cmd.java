// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.team8410.commands;

import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.team8410.subsystems.OneStageClimber;
import frc.robot.team8410.subsystems.TwoStageClimber;
import frc.robot.team8410.subsystems.WinchSubsystem;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class HangPart3Cmd extends SequentialCommandGroup {
  /** Creates a new HangParrt2Cmd. */
  public HangPart3Cmd( WinchSubsystem winch, 
  TwoStageClimber twoStage, 
  OneStageClimber oneStage,
  DutyCycleEncoder encWinch,
  DutyCycleEncoder encTwoStage,
  DutyCycleEncoder encSingleStage) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands
    (
      new OneStageDescendCmd(oneStage, 2.00, encSingleStage),
      new WindWinchCommand(winch, encWinch,7),
      new TwoStageExtendCmd(twoStage, 2.00, encTwoStage),//have rwo stage 3 quarters
      new OneStageExtendCmd(oneStage, 2.00, encSingleStage),// i dont think we need but could be wrong
      new WindWinchCommand(winch, encWinch,7),// i think we might need this
      new OneStageDescendCmd(oneStage, 2.00, encSingleStage)
    );
  }
}
