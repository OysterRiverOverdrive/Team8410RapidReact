// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.team8410.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.team8410.subsystems.DrivetrainSubsystem;
import frc.robot.team8410.subsystems.WinchSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ParallelTest extends ParallelCommandGroup {
  /** Creates a new ParallelTest. */
  public ParallelTest(WinchSubsystem winch, DrivetrainSubsystem drivetrain) {
    // Add your commands in the addCommands() call, e.g.
    addCommands(new UnwindWinchCommand(winch), new TeleopDriveCommand(drivetrain));
    // addCommands(new FooCommand(), new BarCommand());
    addCommands();
  }
}
