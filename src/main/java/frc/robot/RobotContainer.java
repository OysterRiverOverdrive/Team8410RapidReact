// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.robot.team8410.commands.hangCmd;
import frc.robot.team8410.commands.stopHangCmd;
import frc.robot.team8410.subsystems.HangerSubsystem;

import frc.robot.team8410.subsystems.DrivetrainSubsystem;
import frc.robot.team8410.commands.TeleopDriveCommand;

//package frc.robot.team8410.sensors;




/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  private final HangerSubsystem hanger = new HangerSubsystem();
  private final hangCmd hang = new hangCmd(hanger);
  private final stopHangCmd stopHang = new stopHangCmd(hanger);

  private final DrivetrainSubsystem drivetrain = new DrivetrainSubsystem();
  private final TeleopDriveCommand teleopCommand = new TeleopDriveCommand(drivetrain);
  private final PowerDistribution powerDistribution;
  
  // The robot's subsystems and commands are defined here...

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    drivetrain.setDefaultCommand(teleopCommand);

    powerDistribution = new PowerDistribution(0, PowerDistribution.ModuleType.kCTRE);

    // displaying subsystems
    SmartDashboard.putData(hanger);
    SmartDashboard.putData(drivetrain);

    //display commands
    SmartDashboard.putData(teleopCommand);
    SmartDashboard.putData(hang);
    SmartDashboard.putData(stopHang);
    SmartDashboard.putData(teleopCommand);

    
    //Displaying power distribution
    SmartDashboard.putNumber("Temperature", powerDistribution.getTemperature());
    SmartDashboard.putNumber("Total Current", powerDistribution.getTotalCurrent());
    SmartDashboard.putNumber("Voltage", powerDistribution.getVoltage());
    SmartDashboard.putNumber("Power", powerDistribution.getTotalPower());
    

    if (powerDistribution.getTemperature() > 35) {
      SmartDashboard.putBoolean("Temp is High", false);
    } else {
      SmartDashboard.putBoolean("Temp is Ok", true);
    }


    if (powerDistribution.getTotalCurrent() < 20) {
      SmartDashboard.putBoolean("Current is High", false);
    } else {
      SmartDashboard.putBoolean("Current is Fine", true);
    }

    if (powerDistribution.getVoltage() > 1 ) {
      SmartDashboard.putBoolean("Voltage is High", true);
    } else {
      SmartDashboard.putBoolean("Voltage is Fine", false);
    }

    if (powerDistribution.getTotalPower() < 1) {
      SmartDashboard.putBoolean("Power is Fine", true);
    } else {
      SmartDashboard.putBoolean("Power is High", false);


    
    SmartDashboard.putString("Testing Shuffleboard", "Testing Now");
    

    }
  
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    POVButton traverseButton = new POVButton(new Joystick(0), 0);
    //sets POV Button at angle 0 (top of the dpad on xbox controller)
    traverseButton.whenPressed(hang);
    //when button is pressed, robot will hang
    traverseButton.whenReleased(stopHang);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;// TODO change this to the name of the auto command
  }
}
