// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.team8410.sensors.Color_RevroboticsVer3;
import frc.robot.team8410.sensors.Color_TCS34725_I2C;
import frc.robot.team8410.sensors.SensorValues;
// import frc.robot.team8410.sensors.UltrasonicFront;
// import frc.robot.team8410.sensors.UltrasonicLeft;
// import frc.robot.team8410.sensors.UltrasonicRight;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot 
{
  private Command m_autonomousCommand;
  private RobotContainer m_robotContainer;
  private SensorValues sensorValues = new SensorValues();
  private Color_RevroboticsVer3 colorRevSensor;
  private Color_TCS34725_I2C colorTCSSensor;
 
  private boolean isTCSSensorGood;
  private boolean isRevColorSensorGood;
  
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() 
  {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();

    sensorValues = new SensorValues();
    


    try
    {
      //TODO this needs to be tested on real bot
      colorTCSSensor = new Color_TCS34725_I2C();
      colorTCSSensor.initialize(2,1);
      isTCSSensorGood= true;
      }
    catch(Exception e)
    {
      // initialization of the sensor failed - do not read this value
      isTCSSensorGood = false;
      System.out.println("Could not initi TCS sensor");
       e.printStackTrace();
    }

    try
    {
      // initialization of the sensor failed. Do not read this value
      colorRevSensor = new Color_RevroboticsVer3();
      isRevColorSensorGood= true;
    }
    catch(Exception e)
    {
      isRevColorSensorGood= false;
      System.out.println("Could not initi Rev sensor");
    

       e.printStackTrace();
    }

    
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {

    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.

    /*******/
    // This is where we will read the sensors and call the set method of the sensorValue object
    

    if(isTCSSensorGood)
    {
       sensorValues.setBallColor_TSCSensor(colorTCSSensor.getBallColor());
    }
    else
    {
      sensorValues.setBallColor_TSCSensor("BAD");
    }

    if(isRevColorSensorGood)
    {
     sensorValues.setBallColor_RevSensor(colorRevSensor.getBallColor());
    }
    else
    {
      sensorValues.setBallColor_RevSensor("BAD");
    }
     



    CommandScheduler.getInstance().run();
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }

  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() 
  {

  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {}
}
