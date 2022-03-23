// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.POVButton;

//import frc.robot.team8410.commands.AutoSequeCmd;
//import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.team8410.subsystems.DrivetrainSubsystem;
import frc.robot.team8410.commands.TeleopDriveCommand;
//import frc.robot.team8410.subsystems.WinchSubsystem;



/*
import frc.robot.team8410.commands.DriverAutoCmd;
import frc.robot.team8410.commands.UnwindWinchCommand;
import frc.robot.team8410.subsystems.DiagnosticsSubSystem;
*/

import frc.robot.team8410.subsystems.IntakeRollerSubsystem;
import frc.robot.team8410.commands.RollerPull;
import frc.robot.team8410.commands.RollerPush;
import frc.robot.team8410.commands.RollerStop;

import frc.robot.team8410.subsystems.IntakeArmSubSystem;
import frc.robot.team8410.commands.RaiseIntakeCmd;
import frc.robot.team8410.commands.LowerIntakeCmd;
import frc.robot.team8410.sensors.PotSensor;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.Trigger;

import frc.robot.team8410.subsystems.OneStageClimber;
import frc.robot.team8410.commands.OneStageExtendCmd;
import frc.robot.team8410.commands.hangCmd;
import frc.robot.team8410.subsystems.WinchSubsystem;
import frc.robot.team8410.subsystems.TwoStageClimber;
import frc.robot.team8410.commands.TwoStageExtendCmd;

import edu.wpi.first.wpilibj.DutyCycleEncoder;




/*import frc.robot.team8410.commands.hangCmd;
import frc.robot.team8410.subsystems.OneStageClimber;
import frc.robot.team8410.subsystems.TwoStageClimber;*/


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

  //private final AutoSequeCmd auto = new AutoSequeCmd(drivetrain);



  //  ashish commented private final PowerDistribution powerDistribution = new PowerDistribution();


  

  // private final XboxController joystick = new XboxController(0);
  private final Joystick joystick = new Joystick(0);

  // creating an instance of this will allow for the subsystem perodic method to run in the Diagnostic subsystem
  // so the diagnostic logic is in one place.
 // private DiagnosticsSubSystem diagnosticSubSys = new DiagnosticsSubSystem();
  
  /*private final IntakeArmSubSystem intakeArmSubSystem = new IntakeArmSubSystem();
  private final IntakeRollerSubsystem intakeRollerSubSystem = new IntakeRollerSubsystem();
  private final RollerPull rollerPull = new RollerPull(intakeRollerSubSystem);
  private final RollerPush rollerPush = new RollerPush(intakeRollerSubSystem);
  private final RaiseIntakeCmd raiseIntakeCmd = new RaiseIntakeCmd(intakeArmSubSystem);
  private final LowerIntakeCmd lowerIntakeCmd = new LowerIntakeCmd(intakeArmSubSystem);
  private final DriverAutoCmd autostraightCmd = new DriverAutoCmd(drivetrain, intakeArmSubSystem);
  private final DiagnosticsSubSystem diagnosticSubSys = new DiagnosticsSubSystem();// this way the peroidic in the diagnstic will be run
  private final WinchSubsystem winch = new WinchSubsystem();
  private final TwoStageClimber twoStage = new TwoStageClimber();
  private final OneStageClimber oneStage = new OneStageClimber();
  private final hangCmd hang = new hangCmd(winch, twoStage, oneStage);
*/

private final IntakeRollerSubsystem intakeRollerSubSystem = new IntakeRollerSubsystem();
private final RollerPull rollerPull = new RollerPull(intakeRollerSubSystem);
private final RollerPush rollerPush = new RollerPush(intakeRollerSubSystem);
private final RollerStop rollerStop = new RollerStop(intakeRollerSubSystem);
private final IntakeArmSubSystem intakeArmSubSystem = new IntakeArmSubSystem();
private final PotSensor potSensor = new PotSensor();

private final RaiseIntakeCmd raiseIntakeCmd = new RaiseIntakeCmd(intakeArmSubSystem, potSensor);
private final LowerIntakeCmd lowerIntakeCmd = new LowerIntakeCmd(intakeArmSubSystem, potSensor);


private final OneStageClimber oneStage = new OneStageClimber();

private final TwoStageClimber twoStageSub = new TwoStageClimber();
//private final TwoStageExtendCmd twoStageExtCmd = new TwoStageExtendCmd(twoStageSub);
private final WinchSubsystem winchSub = new WinchSubsystem();
private final DutyCycleEncoder oneStageLeftEncoder = new DutyCycleEncoder(Constants.HANGER_ONE_STAGE_LEFT_ENCODER_PORT);
private final hangCmd hang = new hangCmd(winchSub, twoStageSub, oneStage, oneStageLeftEncoder);
private final OneStageExtendCmd oneStageExtendCmd  = new OneStageExtendCmd(oneStage,11,oneStageLeftEncoder );// change enc value



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
   /* Joystick joystick = new Joystick(Constants.JOYSTICK_PORT);
    POVButton hangButton = new POVButton(joystick, 0);
    //sets POV Button at angle 0 (top of the dpad on xbox controller)
    System.out.println("hang button pressed");
    hangButton.whenPressed(hang);

     //POVButton winchButton = new POVButton(joystick, 0);
    JoystickButton AutoButton = new JoystickButton(joystick, Constants.DRIVER_ASSIST_BUTTON);
    // System.out.println("intake button pressed");
     //sets POV Button at angle 0 (top of the dpad on xbox controller)

     AutoButton.whenPressed(autostraightCmd);
   
    //  winchButton.whenReleased(stop);
    */

    Joystick joystick = new Joystick(Constants.JOYSTICK_PORT);

    Trigger rollerPullButton = new Trigger() {
      @Override
      public boolean get() {
        return joystick.getRawAxis(Constants.JOYSTICK_LEFT_TRIGGER) > 0.2;
      }
     };
      rollerPullButton.whenActive(rollerPull);
      rollerPullButton.whenInactive(rollerStop);

     Trigger rollerPushButton = new Trigger() {
        @Override
        public boolean get() {
          return joystick.getRawAxis(Constants.JOYSTICK_RIGHT_TRIGGER) > 0.2;
        }
       };
        rollerPushButton.whenActive(rollerPush);
        rollerPushButton.whenInactive(rollerStop);


        JoystickButton intakeButtonrise = new JoystickButton(joystick, Constants.INTAKE_BUTTON_RISE);
        JoystickButton intakeButtonlower = new JoystickButton(joystick, Constants.INTAKE_BUTTON_LOWER);
        intakeButtonrise.whenPressed(raiseIntakeCmd);
        intakeButtonlower.whenPressed(lowerIntakeCmd);

        POVButton oneStageUp = new POVButton(joystick, 1);
        oneStageUp.whenPressed(oneStageExtendCmd);

        

        POVButton startHang = new POVButton(joystick, 2);
        startHang.whenPressed(hang);



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
