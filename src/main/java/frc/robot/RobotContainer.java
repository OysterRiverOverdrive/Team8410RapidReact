// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.POVButton;

import frc.robot.team8410.commands.AutoSequeCmd;
//import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import frc.robot.team8410.commands.AutoDriveCommand;
import frc.robot.team8410.commands.hangCmd;
import frc.robot.team8410.commands.LowerIntakeCmd;
import frc.robot.team8410.commands.OneStageDescendCmd;
import frc.robot.team8410.commands.OneStageExtendCmd;
import frc.robot.team8410.commands.RaiseIntakeCmd;
import frc.robot.team8410.commands.RollerPull;
import frc.robot.team8410.commands.RollerPush;
import frc.robot.team8410.commands.RollerStop;
import frc.robot.team8410.commands.TeleopDriveCommand;
import frc.robot.team8410.commands.TwoStageExtendCmd;
import frc.robot.team8410.commands.TwoStageDescendCmd;
// import frc.robot.team8410.sensors.PotSensor;
// import frc.robot.team8410.sensors.WinchEncoder;
// import frc.robot.team8410.subsystems.DiagnosticsSubSystem;
import frc.robot.team8410.subsystems.DrivetrainSubsystem;
import frc.robot.team8410.subsystems.AutoDrivetrainSubSystem;
import frc.robot.team8410.subsystems.IntakeArmSubSystem;
import frc.robot.team8410.subsystems.IntakeRollerSubsystem;
import frc.robot.team8410.subsystems.OneStageClimber;
import frc.robot.team8410.subsystems.TwoStageClimber;
import frc.robot.team8410.subsystems.WinchSubsystem;
import frc.robot.team8410.commands.HangPart1Cmd;
import frc.robot.team8410.commands.HangPart3Cmd;
import frc.robot.team8410.commands.HangPart4Cmd;
import frc.robot.team8410.commands.hangCmd;
import frc.robot.team8410.commands.UnwindWinchCommand;
import frc.robot.team8410.commands.Winchstop;
import frc.robot.team8410.commands.WindWinchCommand;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import frc.robot.team8410.subsystems.DrivetrainSubsystem;

import edu.wpi.first.wpilibj.Joystick;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  private final DrivetrainSubsystem drivetrain = new DrivetrainSubsystem();
  private final TeleopDriveCommand teleopCommand = new TeleopDriveCommand(drivetrain);

  private final AutoDrivetrainSubSystem autoDrivetrain = new AutoDrivetrainSubSystem();
  private final IntakeRollerSubsystem intakeRollerSubSystem = new IntakeRollerSubsystem();
  private final AutoDriveCommand auto = new AutoDriveCommand(autoDrivetrain, -0.8);
  private final AutoSequeCmd sequ = new AutoSequeCmd(autoDrivetrain, intakeRollerSubSystem);
  
  private final DutyCycleEncoder encSingleStage = new DutyCycleEncoder(Constants.HANGER_ONE_STAGE_LEFT_ENCODER_PORT);
  private final DutyCycleEncoder encTwoStage = new DutyCycleEncoder(Constants.HANGER_TWO_STAGE_ENCODER_PORT) ;
  private final DutyCycleEncoder encWinch = new DutyCycleEncoder(Constants.HANGER_WINCH_ENCODER_PORT);
  
  private final IntakeArmSubSystem intakeArmSubSystem = new IntakeArmSubSystem();
  // private final DriverAutoCmd autostraightCmd = new DriverAutoCmd(drivetrain, intakeArmSubSystem, potSensor);
  //private final DiagnosticsSubSystem diagnosticSubSys = new DiagnosticsSubSystem();// this way the peroidic in the
                                                                                   // diagnstic will be run
  private final WinchSubsystem winchSubSys = new WinchSubsystem();
  private final TwoStageClimber twoStage = new TwoStageClimber();
  private final OneStageClimber oneStage = new OneStageClimber();

private final RollerPull rollerPull = new RollerPull(intakeRollerSubSystem);
private final RollerPush rollerPush = new RollerPush(intakeRollerSubSystem);
private final RollerStop rollerStop = new RollerStop(intakeRollerSubSystem);
private final AnalogInput potSensor = new AnalogInput(Constants.INTAKE_ARM_POT_PORT_ID);

  private final RaiseIntakeCmd raiseIntakeCmd = new RaiseIntakeCmd(intakeArmSubSystem, potSensor);
  private final LowerIntakeCmd lowerIntakeCmd = new LowerIntakeCmd(intakeArmSubSystem, potSensor);

 
  private final TwoStageClimber twoStageSub = new TwoStageClimber();
  private final TwoStageExtendCmd twoStageExtCmd = new TwoStageExtendCmd(twoStageSub, 4,encTwoStage);
  private final TwoStageDescendCmd twoStageDeCmd = new TwoStageDescendCmd(twoStageSub, 4,encTwoStage);
  private final WindWinchCommand windwinch = new WindWinchCommand(winchSubSys, encWinch, 1.80);// change value
  private final UnwindWinchCommand unwindwinch = new UnwindWinchCommand(winchSubSys, encWinch,1.80);// change value
  private final Winchstop stopwinch = new Winchstop(winchSubSys);
  
  //private final DutyCycleEncoder oneStageLeftEncoder = new DutyCycleEncoder(
    //  Constants.HANGER_ONE_STAGE_LEFT_ENCODER_PORT);
  // private final hangCmd hang = new hangCmd(winchSub, twoStageSub, oneStage, oneStageLeftEncoder);
  private final OneStageDescendCmd oneStageDecendCmd = new OneStageDescendCmd(oneStage, 7.9, encSingleStage);
  private final OneStageExtendCmd oneStageExtendCmd = new OneStageExtendCmd(oneStage, 7.9, encSingleStage);// change
                                                                                                               // enc
                                                                                                               // value

     private final hangCmd hang = new hangCmd(winchSubSys, 
     twoStage,
     oneStage,
     encSingleStage,
     encTwoStage,
     encWinch) ;   
     
private final HangPart1Cmd hangPart1 =      new HangPart1Cmd(winchSubSys, 
      oneStage,
      encWinch,
      encSingleStage);


private final HangPart3Cmd hangPart3 = new HangPart3Cmd(winchSubSys,twoStage,oneStage,encWinch,encTwoStage,encSingleStage);
private final HangPart4Cmd hangPart4 = new HangPart4Cmd(winchSubSys, twoStage, oneStage, encWinch, encTwoStage, encSingleStage);


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
  private void configureButtonBindings() 
  {
    
    Joystick driver = new Joystick(Constants.DRIVER_PORT);
    Joystick operator = new Joystick(Constants.OPERATOR_PORT);
    // Trigger rollerPullButton = new Trigger() {
    //   @Override
    //   public boolean get() {
    //     return driver.getRawAxis(Constants.JOYSTICK_LEFT_TRIGGER) > 0.2;
    //   }

    // };
    // rollerPullButton.whenActive(rollerPull);
    // rollerPullButton.whenInactive(rollerStop);

    // This button will drop the two stage and then raise the two onestage hangers.
    POVButton hangPartOne = new POVButton(driver, 0);
    hangPartOne.whenPressed(hangPart1);  



    POVButton hanger = new POVButton(driver, 90);
    hanger.whenPressed(hang); // this button will 

    POVButton winchManualIn = new POVButton(operator, 90);
    winchManualIn.whenPressed(windwinch);

    POVButton winchManualOut = new POVButton(operator, 270);
    winchManualOut.whenPressed(unwindwinch);


    // Trigger rollerPushButton = new Trigger() {
    //   @Override
    //   public boolean get() {
    //     return driver.getRawAxis(Constants.JOYSTICK_RIGHT_TRIGGER) > 0.2;
    //   }
    // };
  //  rollerPushButton.whenActive(rollerPush);
  //   rollerPushButton.whenInactive(rollerStop);

    JoystickButton intakeButtonrise = new JoystickButton(operator, Constants.INTAKE_BUTTON_RISE);
    JoystickButton intakeButtonlower = new JoystickButton(operator, Constants.INTAKE_BUTTON_LOWER);
    JoystickButton oneStageDown = new JoystickButton(operator, 4);
    JoystickButton oneStageUp = new JoystickButton(operator, 3);
    JoystickButton twostagedescend = new JoystickButton(operator, 1);
    JoystickButton rollerPushButton = new JoystickButton(operator, 10);
    JoystickButton rollerPullButton = new JoystickButton(operator, 9);
    JoystickButton twoStageExtend = new JoystickButton(operator, 2);

    POVButton hangerPart3 = new POVButton(driver, 270);
    POVButton hangerPart4 = new POVButton(driver, 180);

    




    rollerPullButton.whenPressed(rollerPull);
    rollerPullButton.whenReleased(rollerStop);
    rollerPushButton.whenPressed(rollerPush);
    rollerPushButton.whenReleased(rollerStop);

    oneStageDown.whenPressed(oneStageDecendCmd);
    oneStageUp.whenPressed(oneStageExtendCmd);

    twostagedescend.whenPressed(twoStageDeCmd);

    


    hangerPart3.whenPressed(hangPart3);
    
    hangerPart4.whenPressed(hangPart4);
    

    intakeButtonrise.whenPressed(raiseIntakeCmd);
    intakeButtonlower.whenPressed(lowerIntakeCmd);

    twoStageExtend.whenPressed(twoStageExtCmd); // extend the two stage  

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() 
  {
    // An ExampleCommand will run in autonomous

    return sequ;// TODO change this to the name of the auto command
  }


public TeleopDriveCommand getTeleOpCommand()
{
  return teleopCommand;
}

}
