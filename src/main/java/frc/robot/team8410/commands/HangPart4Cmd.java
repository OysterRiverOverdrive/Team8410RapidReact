// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.team8410.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.team8410.subsystems.OneStageClimber;
import frc.robot.team8410.subsystems.TwoStageClimber;
import frc.robot.team8410.subsystems.WinchSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class HangPart4Cmd extends SequentialCommandGroup {
  /** Creates a new HangPart4Cmd. */
  public HangPart4Cmd( WinchSubsystem winch, 
  TwoStageClimber twoStage, 
  OneStageClimber oneStage,
  DutyCycleEncoder encWinch,
  DutyCycleEncoder encTwoStage,
  DutyCycleEncoder encSingleStage
  ) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands
    (
      new TwoStageExtendCmd(twoStage, 2.00, encTwoStage),
      new UnwindWinchCommand(winch, encTwoStage,2.00), //winchy maybe// might need to be a wind winch
      new TwoStageDescendCmd(twoStage, 2.00, encTwoStage),
      new WindWinchCommand(winch, encWinch, 2.00),
      new TwoStageExtendCmd(twoStage, 2.00, encTwoStage),
      new UnwindWinchCommand(winch, encTwoStage,2.00),
      new TwoStageDescendCmd(twoStage, 2.00, encTwoStage),
      new OneStageExtendCmd(oneStage, 2.00, encSingleStage)

    




    );
  }
}
