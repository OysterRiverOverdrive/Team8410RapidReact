// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.team8410.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.team8410.subsystems.OneStageClimber;
import frc.robot.team8410.subsystems.TwoStageClimber;
import frc.robot.team8410.subsystems.WinchSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class hangCmd extends SequentialCommandGroup {
  /** Creates a new SequentialTest. */
  public hangCmd(WinchSubsystem winch, TwoStageClimber twoStage, OneStageClimber oneStage) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
    //need to figure out how to program the start
    new UnwindWinchCommand(winch),
    new TwoStageExtendCmd(twoStage), 
    new WindWinchCommand(winch),
    new TwoStageDescendCmd(twoStage),
    new OneStageExtendCmd(oneStage),
    new WindWinchCommand(winch),
    new OneStageDescendCmd(oneStage),
    //this proccess is repeated two times
    new UnwindWinchCommand(winch),
    new TwoStageExtendCmd(twoStage), 
    new WindWinchCommand(winch),
    new TwoStageDescendCmd(twoStage),
    new OneStageExtendCmd(oneStage),
    new WindWinchCommand(winch),
    new OneStageDescendCmd(oneStage));

  }
}
