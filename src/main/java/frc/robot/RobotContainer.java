// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.robot.team8410.commands.AutoCommand;
import frc.robot.team8410.commands.RaiseIntakeCmd;
import frc.robot.team8410.commands.TeleopDriveCommand;
import frc.robot.team8410.commands.DriverAutoCmd;
import frc.robot.team8410.subsystems.DrivetrainSubsystem;
import frc.robot.team8410.subsystems.IntakeArmSubSystem;
import frc.robot.team8410.commands.RaiseIntakeCmd;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.Joystick;

//package frc.robot.team8410.sensors;




/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
 
  private final DrivetrainSubsystem drivetrain = new DrivetrainSubsystem();
  private final TeleopDriveCommand teleopCommand = new TeleopDriveCommand(drivetrain);
  private final AutoCommand autoCmd = new AutoCommand(drivetrain);

 // private final XboxController joystick = new XboxController(0);
 private final Joystick joystick = new Joystick(0);

  // creating an instance of this will allow for the subsystem perodic method to run in the Diagnostic subsystem
  // so the diagnostic logic is in one place.
 // private DiagnosticsSubSystem diagnosticSubSys = new DiagnosticsSubSystem();
  
  private final IntakeArmSubSystem intakeArmSubSystem = new IntakeArmSubSystem();
  private final RaiseIntakeCmd raiseIntakeCmd = new RaiseIntakeCmd(intakeArmSubSystem);
  
  private final DriverAutoCmd autostraightCmd = new DriverAutoCmd(drivetrain, intakeArmSubSystem);
  
  // The robot's subsystems and commands are defined here...

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    drivetrain.setDefaultCommand(teleopCommand);
    JoystickButton triggerButton = new JoystickButton(joystick, 1);
    triggerButton.whenPressed(autostraightCmd);
    

    
  
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings()
   {



     //POVButton winchButton = new POVButton(joystick, 0);
     JoystickButton winchButton = new JoystickButton(joystick, 1);
     System.out.println("intake button pressed");
     //sets POV Button at angle 0 (top of the dpad on xbox controller)
     winchButton.whenPressed(autostraightCmd);


  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return autoCmd;// TODO change this to the name of the auto command
  }
}
