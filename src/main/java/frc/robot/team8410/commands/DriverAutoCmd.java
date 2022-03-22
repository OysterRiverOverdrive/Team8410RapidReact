// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.team8410.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.team8410.subsystems.IntakeArmSubSystem;
import frc.robot.team8410.subsystems.DrivetrainSubsystem;
import frc.robot.team8410.sensors.PotSensor;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class DriverAutoCmd extends SequentialCommandGroup {
  /** Creates a new DriverAutoCmd. */
  public DriverAutoCmd(DrivetrainSubsystem drive, IntakeArmSubSystem intake, PotSensor potSensor) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(new RaiseIntakeCmd(intake, potSensor), new AutoDriveTargetCmd(drive));
  }
}
