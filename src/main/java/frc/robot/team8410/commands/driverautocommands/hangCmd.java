// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.team8410.commands.driverautocommands;



import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.team8410.commands.hangercommands.OneStageDescendCmd;
import frc.robot.team8410.commands.hangercommands.OneStageExtendCmd;
import frc.robot.team8410.commands.hangercommands.TwoStageDescendCmd;
import frc.robot.team8410.commands.hangercommands.TwoStageExtendCmd;
import frc.robot.team8410.commands.hangercommands.UnwindWinchCommand;
import frc.robot.team8410.commands.hangercommands.WindWinchCommand;
import frc.robot.team8410.subsystems.OneStageClimber;
import frc.robot.team8410.subsystems.TwoStageClimber;
import frc.robot.team8410.subsystems.WinchSubsystem;

//import frc.robot.team8410.commands.TwoStageExtendCmd;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class hangCmd extends SequentialCommandGroup {
  /** Creates a new SequentialTest. */

  //private  DutyCycleEncoder EncoderSensor;

  public hangCmd(WinchSubsystem winch, 
                 TwoStageClimber twoStage, 
                 OneStageClimber oneStage, 
                 DutyCycleEncoder encSingleStage,
                 DutyCycleEncoder encTwoStage,
                 DutyCycleEncoder encWinch) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());

    addCommands(
    //ground to mid

    //new TwoStageExtendCmd(twoStage, 8.0, encTwoStage), 
    new TwoStageExtendCmd(twoStage, 8.00, encTwoStage),// this is extend the two stage
    new OneStageDescendCmd(oneStage, 8.5, encSingleStage),  // pull down the one stages
    new UnwindWinchCommand(winch, encWinch, 2.00), // pull the fatty back// need to change values
    new TwoStageDescendCmd(twoStage, 7.00, encTwoStage), // pull down the two stage 
    new OneStageExtendCmd(oneStage, 7.9, encSingleStage),
    new WindWinchCommand(winch, encWinch, 2.00)); // let go of the one stage by extending

    // This is if we want to try the keep Hanging thing
    //new KeepHangingTwoStageCommand(twoStage, encTwoStage.get(),encTwoStage));  // keep hanging
   // this will keep the bot hanging

  }
}

