// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.POVButton;

import frc.robot.team8410.commands.AutoSequeCmd;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.team8410.commands.TeleopDriveCommand;
import frc.robot.team8410.commands.UnwindWinchCommand;
import frc.robot.team8410.subsystems.DrivetrainSubsystem;
import frc.robot.team8410.subsystems.WinchSubsystem;

import frc.robot.team8410.commands.RaiseIntakeCmd;
import frc.robot.team8410.commands.TeleopDriveCommand;
import frc.robot.team8410.commands.DriverAutoCmd;

import frc.robot.team8410.subsystems.DiagnosticsSubSystem;
import frc.robot.team8410.subsystems.IntakeArmSubSystem;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Constants;
import frc.robot.team8410.subsystems.IntakeArmSubSystem;

import frc.robot.team8410.commands.hangCmd;
import frc.robot.team8410.subsystems.OneStageClimber;
import frc.robot.team8410.subsystems.TwoStageClimber;

import frc.robot.team8410.commands.RaiseIntakeCmd;
import frc.robot.team8410.commands.TeleopDriveCommand;
import frc.robot.team8410.commands.RaiseIntakeCmd;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.team8410.subsystems.DrivetrainSubsystem;

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

  private final AutoSequeCmd auto = new AutoSequeCmd(drivetrain);



  private final PowerDistribution powerDistribution = new PowerDistribution();


  

  // private final XboxController joystick = new XboxController(0);
  private final Joystick joystick = new Joystick(0);

  // creating an instance of this will allow for the subsystem perodic method to run in the Diagnostic subsystem
  // so the diagnostic logic is in one place.
 // private DiagnosticsSubSystem diagnosticSubSys = new DiagnosticsSubSystem();
  
  private final IntakeArmSubSystem intakeArmSubSystem = new IntakeArmSubSystem();
  private final RaiseIntakeCmd raiseIntakeCmd = new RaiseIntakeCmd(intakeArmSubSystem);


  private final WinchSubsystem winch = new WinchSubsystem();
  private final TwoStageClimber twoStage = new TwoStageClimber();
  private final OneStageClimber oneStage = new OneStageClimber();
  private final hangCmd hang = new hangCmd(winch, twoStage, oneStage);

  private final DriverAutoCmd autostraightCmd = new DriverAutoCmd(drivetrain, intakeArmSubSystem);
 
  private final DiagnosticsSubSystem diagnosticSubSys = new DiagnosticsSubSystem();// this way the peroidic in the diagnstic will be run

  // The robot's subsystems and commands are defined here...

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() 
  {
    // Configure the button bindings
    configureButtonBindings();


    drivetrain.setDefaultCommand(teleopCommand);

  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    POVButton hangButton = new POVButton(joystick, 0);
    //sets POV Button at angle 0 (top of the dpad on xbox controller)
    System.out.println("hang button pressed");
    hangButton.whenPressed(hang);

     //POVButton winchButton = new POVButton(joystick, 0);
     JoystickButton intakeButton = new JoystickButton(joystick, Constants.INTAKE_BUTTON);
    JoystickButton AutoButton = new JoystickButton(joystick, 1);
     System.out.println("intake button pressed");
     

     AutoButton.whenPressed(autostraightCmd);
     intakeButton.whenPressed(raiseIntakeCmd);

   
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return auto;// TODO change this to the name of the auto command
  }
}
