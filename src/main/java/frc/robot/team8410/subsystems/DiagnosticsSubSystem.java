// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.team8410.subsystems;

import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.team8410.sensors.Color_RevroboticsVer3;
import frc.robot.team8410.sensors.Color_TCS34725_I2C;
import frc.robot.team8410.sensors.SensorValues;

public class DiagnosticsSubSystem extends SubsystemBase {
  /** Creates a new DiagnosticsSubSystem. */

  private final SensorValues sensorValues;

  private final PowerDistribution powerDistribution = new PowerDistribution(Constants.PDP_CAN_ID,
      PowerDistribution.ModuleType.kCTRE);

  private final AddressableLED m_led = new AddressableLED(Constants.M_LED_PORT);
  private final AddressableLEDBuffer m_ledBuffer = new AddressableLEDBuffer(Constants.LED_BUFFER_LEGNTH);

  private final I2C.Port i2cPort = I2C.Port.kOnboard;
  private final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);

  private Color_RevroboticsVer3 colorRevSensor;
  private Color_TCS34725_I2C colorTCSSensor;

  private boolean isTCSSensorGood;
  private boolean isRevColorSensorGood;

  Color detectedColor = m_colorSensor.getColor();
  double redOverBlue = detectedColor.red / detectedColor.blue;

  public DiagnosticsSubSystem() {

    sensorValues = new SensorValues();
    // Reuse buffer
    // Default to a length of 60, start empty output
    // Length is expensive to set, so only set it once, then just update data
    m_led.setLength(m_ledBuffer.getLength());
    m_led.setData(m_ledBuffer);
    m_led.start();
    try {
      // TODO this needs to be tested on real bot
      colorTCSSensor = new Color_TCS34725_I2C();
      colorTCSSensor.initialize(2, 1);
      isTCSSensorGood = true;
    } catch (Exception e) {
      // initialization of the sensor failed - do not read this value
      isTCSSensorGood = false;
      System.out.println("Could not initi TCS sensor");
      e.printStackTrace();
    }

    try {
      // initialization of the sensor failed. Do not read this value
      colorRevSensor = new Color_RevroboticsVer3();
      isRevColorSensorGood = true;
    } catch (Exception e) {
      isRevColorSensorGood = false;
      System.out.println("Could not initi Rev sensor");
      e.printStackTrace();
    }

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    if (isTCSSensorGood) {
      sensorValues.setBallColor_TSCSensor(colorTCSSensor.getBallColor());
    } else {
      sensorValues.setBallColor_TSCSensor("BAD");
    }

    if (isRevColorSensorGood) {
      sensorValues.setBallColor_RevSensor(colorRevSensor.getBallColor());
    } else {
      sensorValues.setBallColor_RevSensor("BAD");
    }

    // displaying sensor values
    SmartDashboard.putNumber("Lidar Distance", sensorValues.getLidarDistanceInches());
    SmartDashboard.putNumber("Ultrasonic Back", sensorValues.getUltrasonicBackInches());
    SmartDashboard.putNumber("Ultrasonic Left", sensorValues.getUltrasonicLeftInches());
    SmartDashboard.putNumber("Ultrasonic Right", sensorValues.getUltrasonicRightInches());

    // displaying power distribution
    SmartDashboard.putNumber("Temperature", powerDistribution.getTemperature());
    SmartDashboard.putNumber("Total Current", powerDistribution.getTotalCurrent());
    SmartDashboard.putNumber("Voltage", powerDistribution.getVoltage());
    SmartDashboard.putNumber("Power", powerDistribution.getTotalPower());

    // displaying powerdistribution stuff on smartdashboard

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

    if (powerDistribution.getVoltage() > 1) {
      SmartDashboard.putBoolean("Voltage is High", true);
    } else {
      SmartDashboard.putBoolean("Voltage is Fine", false);
    }

    if (powerDistribution.getTotalPower() < 1) {
      SmartDashboard.putBoolean("Power is Fine", true);
    } else {
      SmartDashboard.putBoolean("Power is High", false);

      // SmartDashboard.putString("Testing Shuffleboard", "Testing Now");

      bakeVanillaGoldfish();

    }
  }

  public void bakeVanillaGoldfish() {
    // code 4 LED stuff

    if (powerDistribution.getTotalPower() < 1) {

      // if robot is dead, the LED Q1 will be red

      for (var i = 0 * m_ledBuffer.getLength() / 4; i < m_ledBuffer.getLength() / 4; i++) {

        m_ledBuffer.setRGB(i, 255, 0, 0);
      }

      for (var i = 1 * m_ledBuffer.getLength() / 4; i < 2 * m_ledBuffer.getLength() / 4; i++) {

        // if robot is fine, LEDs Q2 will be green
        m_ledBuffer.setRGB(i, 0, 0, 0);

        // m_ledBuffer.setRGB(i, 255, 17, 221);
        // use for atunomus, pink
      }

    } else if (powerDistribution.getTotalPower() < 10) {

      // if robot is running low on power, LEDs Q1 will be yellow
      for (var i = 0 * m_ledBuffer.getLength(); i < m_ledBuffer.getLength() / 4; i++) {

        m_ledBuffer.setRGB(i, 255, 238, 0);

      }

      for (var i = 1 * m_ledBuffer.getLength() / 4; i < 2 * m_ledBuffer.getLength() / 4; i++) {

        // if robot is fine, LEDs Q2 will be green
        m_ledBuffer.setRGB(i, 0, 0, 0);

        // m_ledBuffer.setRGB(i, 255, 17, 221);
        // use for atunomus, pink
      }

    } else {

      for (var i = 1 * m_ledBuffer.getLength() / 4; i < 2 * m_ledBuffer.getLength() / 4; i++) {

        // if robot is fine, LEDs Q2 will be green
        m_ledBuffer.setRGB(i, 0, 255, 0);

        // m_ledBuffer.setRGB(i, 255, 17, 221);
        // use for atunomus, pink
      }

      for (var i = 0 * m_ledBuffer.getLength(); i < m_ledBuffer.getLength() / 4; i++) {

        m_ledBuffer.setRGB(i, 0, 0, 0);

      }
    }

    if (colorTCSSensor.getBallColor() == "RED") {

      // Q3

      for (var i = 2 * m_ledBuffer.getLength() / 4; i < 3 * m_ledBuffer.getLength() / 4; i++) {
        m_ledBuffer.setRGB(i, 255, 0, 0);
      }

    } else if (colorTCSSensor.getBallColor() == "Blue") {

      for (var i = 2 * m_ledBuffer.getLength() / 4; i < 3 * m_ledBuffer.getLength() / 4; i++) {

        m_ledBuffer.setRGB(i, 0, 0, 255);

      }

    } else {

      for (var i = 2 * m_ledBuffer.getLength() / 4; i < 3 * m_ledBuffer.getLength() / 4; i++) {

        m_ledBuffer.setRGB(i, 0, 0, 0);

      }

    }

    if (colorRevSensor.getBallColor() == "RED") {

      // Q4

      for (var i = 3 * m_ledBuffer.getLength() / 4; i < 4 * m_ledBuffer.getLength() / 4; i++) {

        m_ledBuffer.setRGB(i, 255, 0, 0);

      }
    } else if (colorRevSensor.getBallColor() == "BLUE") {

      for (var i = 3 * m_ledBuffer.getLength() / 4; i < 4 * m_ledBuffer.getLength() / 4; i++) {

        m_ledBuffer.setRGB(i, 0, 0, 255);
      }
    } else {

      for (var i = 3 * m_ledBuffer.getLength() / 4; i < 4 * m_ledBuffer.getLength() / 4; i++) {

        m_ledBuffer.setRGB(i, 0, 0, 0);
      }

    }

    /*
     * if (powerDistribution.getTemperature() > 35) {
     * 
     * for (int i = 3; i < 9; i++) {
     * 
     * m_ledBuffer.setRGB(i, 255, 0, 0);
     * 
     * }
     * 
     * } else {
     * 
     * for (int i = 3; i < 9; i++) {
     * 
     * m_ledBuffer.setRGB(i, 0, 255, 0);
     * }
     * }
     */

    /*
     * if (powerDistribution.getTotalPower() > 50) {
     * 
     * 
     * for (int i = 8; i < 13; i++) {
     * 
     * m_ledBuffer.setRGB(i, 0, 0, 255);
     * 
     * }
     * 
     * } else {
     * 
     * 
     * 
     * for (int i = 8; i < 13; i++) {
     * 
     * m_ledBuffer.setRGB(i, 127, 0, 128);
     * }
     * 
     * }
     */

  }

}