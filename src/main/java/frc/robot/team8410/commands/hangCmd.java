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
        // ground to mid
        new UnwindWinchCommand(winch, 1.0),
        new TwoStageExtendCmd(twoStage, 15.79),
        new WindWinchCommand(winch, 1.0), // TODO verify distances
        new TwoStageDescendCmd(twoStage, 15.79),
        new OneStageExtendCmd(oneStage, 3.0),
        new OneStageDescendCmd(oneStage, 3.0),
        // mid to highs
        new UnwindWinchCommand(winch, 5.0),
        new TwoStageExtendCmd(twoStage, 28.5),
        new WindWinchCommand(winch, 5.0),
        new TwoStageDescendCmd(twoStage, 28.5),
        new OneStageExtendCmd(oneStage, 3.0),
        new WindWinchCommand(winch, 5.0),
        new OneStageDescendCmd(oneStage, 3.0),
        // high to traversal
        new UnwindWinchCommand(winch, 5.0),
        new TwoStageExtendCmd(twoStage, 28.5),
        new WindWinchCommand(winch, 5.0),
        new TwoStageDescendCmd(twoStage, 28.5),
        new OneStageExtendCmd(oneStage, 3.0),
        new WindWinchCommand(winch, 5.0),
        new OneStageDescendCmd(oneStage, 3.0));

  }
}
