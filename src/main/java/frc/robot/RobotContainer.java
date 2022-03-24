// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.team8410.commands.DriverAutoCmd;
import frc.robot.team8410.commands.hangCmd;
import frc.robot.team8410.commands.LowerIntakeCmd;
import frc.robot.team8410.commands.OneStageExtendCmd;
import frc.robot.team8410.commands.RaiseIntakeCmd;
import frc.robot.team8410.commands.RollerPull;
import frc.robot.team8410.commands.RollerPush;
import frc.robot.team8410.commands.RollerStop;
import frc.robot.team8410.commands.TeleopDriveCommand;
import frc.robot.team8410.sensors.PotSensor;
import frc.robot.team8410.sensors.Ultrasonic;
import frc.robot.team8410.sensors.WinchEncoder;
import frc.robot.team8410.subsystems.DiagnosticsSubSystem;
import frc.robot.team8410.subsystems.DrivetrainSubsystem;
import frc.robot.team8410.subsystems.IntakeArmSubSystem;
import frc.robot.team8410.subsystems.IntakeRollerSubsystem;
import frc.robot.team8410.subsystems.OneStageClimber;
import frc.robot.team8410.subsystems.TwoStageClimber;
import frc.robot.team8410.subsystems.WinchSubsystem;

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

  private final Joystick driver = new Joystick(0);
  private final Joystick operator = new Joystick(1);

  // private final AutoSequeCmd auto = new AutoSequeCmd(drivetrain);
  // private final PowerDistribution powerDistribution = new PowerDistribution();

  private final PotSensor potSensor = new PotSensor();
  private final Ultrasonic ultrasonicLeft = new Ultrasonic(new AnalogInput(Constants.ULTRASONIC_LEFT_PORT_ID));
  private final Ultrasonic ultrasonicRight = new Ultrasonic(new AnalogInput(Constants.ULTRASONIC_RIGHT_PORT_ID));
  private final Ultrasonic ultrasonicFront = new Ultrasonic(new AnalogInput(Constants.ULTRASONIC_FRONT_PORT_ID));
  private final WinchEncoder winchEncoder = new WinchEncoder();
  private final IntakeArmSubSystem intakeArmSubSystem = new IntakeArmSubSystem();
  private final DrivetrainSubsystem drivetrain = new DrivetrainSubsystem(ultrasonicFront);
  private final DriverAutoCmd autostraightCmd = new DriverAutoCmd(drivetrain, intakeArmSubSystem, potSensor, ultrasonicFront);
  private final TeleopDriveCommand teleopCommand = new TeleopDriveCommand(drivetrain);
  private final DiagnosticsSubSystem diagnosticSubSys = new DiagnosticsSubSystem();// this way the peroidic in the
                                                                                   // diagnstic will be run
  private final WinchSubsystem winch = new WinchSubsystem();
  private final TwoStageClimber twoStage = new TwoStageClimber();
  private final OneStageClimber oneStage = new OneStageClimber();

  private final RaiseIntakeCmd raiseIntakeCmd = new RaiseIntakeCmd(intakeArmSubSystem, potSensor);
  private final LowerIntakeCmd lowerIntakeCmd = new LowerIntakeCmd(intakeArmSubSystem, potSensor);

  private final IntakeRollerSubsystem intakeRollerSubSystem = new IntakeRollerSubsystem();
  private final RollerPull rollerPull = new RollerPull(intakeRollerSubSystem);
  private final RollerPush rollerPush = new RollerPush(intakeRollerSubSystem);
  private final RollerStop rollerStop = new RollerStop(intakeRollerSubSystem);

  private final TwoStageClimber twoStageSub = new TwoStageClimber();
  // private final TwoStageExtendCmd twoStageExtCmd = new
  // TwoStageExtendCmd(twoStageSub);
  private final WinchSubsystem winchSub = new WinchSubsystem();
  private final DutyCycleEncoder oneStageLeftEncoder = new DutyCycleEncoder(
      Constants.HANGER_ONE_STAGE_LEFT_ENCODER_PORT);
  private final hangCmd hang = new hangCmd(winchSub, twoStageSub, oneStage, oneStageLeftEncoder);
  private final OneStageExtendCmd oneStageExtendCmd = new OneStageExtendCmd(oneStage, 11, oneStageLeftEncoder);// change
                                                                                                               // enc
                                                                                                               // value

  // The robot's subsystems and commands are defined here...

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    drivetrain.setDefaultCommand(teleopCommand);

  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing
   * it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
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

    // Joystick joystick = new Joystick(Constants.JOYSTICK_PORT);
    Trigger rollerPullButton = new Trigger() {
      @Override
      public boolean get() {
        return driver.getRawAxis(Constants.JOYSTICK_LEFT_TRIGGER) > 0.2;
      }

    };
    rollerPullButton.whenActive(rollerPull);
    rollerPullButton.whenInactive(rollerStop);

    POVButton oneStageUp = new POVButton(operator, 0);
    oneStageUp.whenPressed(oneStageExtendCmd);

    POVButton startHang = new POVButton(operator, 90);
    startHang.whenPressed(hang);

    Trigger rollerPushButton = new Trigger() {
      @Override
      public boolean get() {
        return driver.getRawAxis(Constants.JOYSTICK_RIGHT_TRIGGER) > 0.2;
      }
    };
    rollerPushButton.whenActive(rollerPush);
    rollerPushButton.whenInactive(rollerStop);

    JoystickButton intakeButtonrise = new JoystickButton(operator, Constants.INTAKE_BUTTON_RISE);
    JoystickButton intakeButtonlower = new JoystickButton(operator, Constants.INTAKE_BUTTON_LOWER);
    intakeButtonrise.whenPressed(raiseIntakeCmd);
    intakeButtonlower.whenPressed(lowerIntakeCmd);

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
