// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj2.command.Command;
//import frc.robot.team8410.commands.DriverAutoCmd;
import frc.robot.team8410.commands.hangCmd;
//import frc.robot.team8410.commands.LowerIntakeCmd;
import frc.robot.team8410.commands.OneStageDescendCmd;
import frc.robot.team8410.commands.OneStageExtendCmd;
//import frc.robot.team8410.commands.RaiseIntakeCmd;
//import frc.robot.team8410.commands.RollerPull;
//import frc.robot.team8410.commands.RollerPush;
//import frc.robot.team8410.commands.RollerStop;
//import frc.robot.team8410.commands.TeleopDriveCommand;
//import frc.robot.team8410.sensors.PotSensor;
//import frc.robot.team8410.sensors.WinchEncoder;
//import frc.robot.team8410.subsystems.DiagnosticsSubSystem;
//import frc.robot.team8410.subsystems.DrivetrainSubsystem;
//import frc.robot.team8410.subsystems.IntakeArmSubSystem;
//import frc.robot.team8410.subsystems.IntakeRollerSubsystem;
import frc.robot.team8410.subsystems.OneStageClimber;
import frc.robot.team8410.subsystems.TwoStageClimber;
import frc.robot.team8410.subsystems.WinchSubsystem;
import frc.robot.team8410.commands.HangPart1Cmd;
import frc.robot.team8410.subsystems.DrivetrainSubsystem;
import frc.robot.team8410.commands.TeleopDriveCommand;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  //private final DrivetrainSubsystem drivetrain = new DrivetrainSubsystem();
  //private final TeleopDriveCommand teleopCommand = new TeleopDriveCommand(drivetrain);

  private final XboxController joystick1 = new XboxController(0);
  private final Joystick joystick = new Joystick(0);



  private final TwoStageClimber twoStage = new TwoStageClimber();
  private final OneStageClimber oneStage = new OneStageClimber();

  private final WinchSubsystem winchSubSys = new WinchSubsystem();
   
  private final DutyCycleEncoder encSingleStage = new DutyCycleEncoder(Constants.HANGER_ONE_STAGE_LEFT_ENCODER_PORT);
  private final DutyCycleEncoder encTwoStage = new DutyCycleEncoder(Constants.HANGER_TWO_STAGE_ENCODER_PORT) ;
  private final DutyCycleEncoder encWinch = new DutyCycleEncoder(Constants.HANGER_WINCH_ENCODER_PORT);

    

     private final hangCmd hangAutonomousCommand = new hangCmd(winchSubSys, 
                                                                twoStage,
                                                                oneStage,
                                                                encSingleStage,
                                                                encTwoStage,
                                                                encWinch) ;   
                                                                
    private final HangPart1Cmd hangPart1 =      new HangPart1Cmd(winchSubSys, 
                                                                 oneStage,
                                                                 encWinch,
                                                                 encSingleStage);


  private final DrivetrainSubsystem drivetrainSubSys = new DrivetrainSubsystem();
  private final TeleopDriveCommand teleop = new TeleopDriveCommand(drivetrainSubSys);
  // private final AutoSequeCmd auto = new AutoSequeCmd(drivetrain);
  // private final PowerDistribution powerDistribution = new PowerDistribution();

 // private final PotSensor potSensor = new PotSensor();
  //private final WinchEncoder winchEncoder = new WinchEncoder();
  //private final IntakeArmSubSystem intakeArmSubSystem = new IntakeArmSubSystem();
  //private final DriverAutoCmd autostraightCmd = new DriverAutoCmd(drivetrain, intakeArmSubSystem, potSensor);
  //private final DiagnosticsSubSystem diagnosticSubSys = new DiagnosticsSubSystem();// this way the peroidic in the
                                                                                   // diagnstic will be run
  //private final WinchSubsystem winch = new WinchSubsystem();


  //private final RaiseIntakeCmd raiseIntakeCmd = new RaiseIntakeCmd(intakeArmSubSystem, potSensor);
  //private final LowerIntakeCmd lowerIntakeCmd = new LowerIntakeCmd(intakeArmSubSystem, potSensor);

  //private final IntakeRollerSubsystem intakeRollerSubSystem = new IntakeRollerSubsystem();
  //private final RollerPull rollerPull = new RollerPull(intakeRollerSubSystem);
  //private final RollerPush rollerPush = new RollerPush(intakeRollerSubSystem);
  //private final RollerStop rollerStop = new RollerStop(intakeRollerSubSystem);

  //private final TwoStageClimber twoStageSub = new TwoStageClimber();
  // private final TwoStageExtendCmd twoStageExtCmd = new
  // TwoStageExtendCmd(twoStageSub);
  //private final WinchSubsystem winchSub = new WinchSubsystem();
  //private final DutyCycleEncoder oneStageLeftEncoder = new DutyCycleEncoder(
    //  Constants.HANGER_ONE_STAGE_LEFT_ENCODER_PORT);
  //private final hangCmd hang = new hangCmd(winchSub, twoStageSub, oneStage, oneStageLeftEncoder);
  private final OneStageDescendCmd oneStageDecendCmd = new OneStageDescendCmd(oneStage, 7.9, encSingleStage);
  private final OneStageExtendCmd oneStageExtendCmd = new OneStageExtendCmd(oneStage, 7.9, encSingleStage);// change
                                                                                                               // enc
                                                                                                               // value

  // The robot's subsystems and commands are defined here...

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    drivetrainSubSys.setDefaultCommand(teleop);

  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing
   * it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() 
  {
    /*
     * Joystick joystick = new Joystick(Constants.JOYSTICK_PORT);
     * POVButton hangButton = new POVButton(joystick, 0);
     * //sets POV Button at angle 0 (top of the dpad on xbox controller)
     * System.out.println("hang button pressed");
     * hangButton.whenPressed(hang);
     * 
     * //POVButton winchButton = new POVButton(joystick, 0);
     * JoystickButton AutoButton = new JoystickButton(joystick,
     * Constants.DRIVER_ASSIST_BUTTON);
     * // System.out.println("intake button pressed");
     * //sets POV Button at angle 0 (top of the dpad on xbox controller)
     * 
     * AutoButton.whenPressed(autostraightCmd);
     * 
     * // winchButton.whenReleased(stop);
     */

    Joystick joystick = new Joystick(Constants.JOYSTICK_PORT);
    Trigger rollerPullButton = new Trigger() {
      @Override
      public boolean get() {
        return joystick.getRawAxis(Constants.JOYSTICK_LEFT_TRIGGER) > 0.2;
      }

    };
    //rollerPullButton.whenActive(rollerPull);
    //rollerPullButton.whenInactive(rollerStop);

    // This button will drop the two stage and then raise the two onestage hangers.
    POVButton oneStageUp = new POVButton(joystick, 0);
    oneStageUp.whenPressed(hangPart1);  


    // This button will pull down the one stage so the robot does a pull up
    // then extends the tw stage
    // then pulls the two stage back so it latches to the top bar
    // then pulls in the two stage so the robot is hanging from the two stage and the 
    // one stage are off from the lower bar. 

    POVButton oneStageDown = new POVButton(joystick, 180);
    oneStageDown.whenReleased(hangAutonomousCommand); // this button will 

    Trigger rollerPushButton = new Trigger() {
      @Override
      public boolean get() {
        return joystick.getRawAxis(Constants.JOYSTICK_RIGHT_TRIGGER) > 0.2;
      }
    };
   // rollerPushButton.whenActive(rollerPush);
    //rollerPushButton.whenInactive(rollerStop);

    JoystickButton intakeButtonrise = new JoystickButton(joystick, Constants.INTAKE_BUTTON_RISE);
    JoystickButton intakeButtonlower = new JoystickButton(joystick, Constants.INTAKE_BUTTON_LOWER);
    //intakeButtonrise.whenPressed(raiseIntakeCmd);
    //intakeButtonlower.whenPressed(lowerIntakeCmd);

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
