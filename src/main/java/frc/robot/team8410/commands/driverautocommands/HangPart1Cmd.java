// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.team8410.commands.driverautocommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.team8410.subsystems.WinchSubsystem;
import frc.robot.team8410.commands.hangercommands.OneStageDescendCmd;
import frc.robot.team8410.commands.hangercommands.OneStageExtendCmd;
import frc.robot.team8410.commands.hangercommands.TwoStageDescendCmd;
import frc.robot.team8410.commands.hangercommands.TwoStageExtendCmd;
import frc.robot.team8410.commands.hangercommands.UnwindWinchCommand;
import frc.robot.team8410.commands.hangercommands.WindWinchCommand;
import frc.robot.team8410.subsystems.OneStageClimber;
import edu.wpi.first.wpilibj.DutyCycleEncoder;



// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class HangPart1Cmd extends SequentialCommandGroup {
  /** Creates a new HangPart1Cmd. */
  public HangPart1Cmd(WinchSubsystem winch,
                      OneStageClimber oneStage,
                      DutyCycleEncoder encWinch,
                      DutyCycleEncoder encSingleStage) 
  {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new UnwindWinchCommand(winch,encWinch,2 ), // this will drop the fatty out// was just wind winch
    //new OneStageExtendCmd(oneStage, 7.9, encSingleStage) // this will extend the one stage out
    new OneStageExtendCmd(oneStage, 7.9, encSingleStage)

    );
  }
}
