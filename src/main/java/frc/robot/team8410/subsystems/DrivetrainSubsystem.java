// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.team8410.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX; // this is real bot


import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.team8410.sensors.UltrasonicFront;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



public class DrivetrainSubsystem extends SubsystemBase {

  // in our robot we have two motors on left
  private final WPI_TalonSRX m_left1 = new WPI_TalonSRX(Constants.LEFT_MOTOR_1_CANID);
  private final WPI_TalonSRX m_left2 = new WPI_TalonSRX(Constants.LEFT_MOTOR_2_CANID);


  

  MotorControllerGroup leftSide = new MotorControllerGroup(m_left1, m_left2);
  
  // in our robot we have two motors on right
  private final WPI_TalonSRX m_right1 = new WPI_TalonSRX(Constants.RIGHT_MOTOR_1_CANID);
  private final WPI_TalonSRX m_right2 = new WPI_TalonSRX(Constants.RIGHT_MOTOR_2_CANID);

  MotorControllerGroup rightSide = new MotorControllerGroup(m_right1, m_right2);

  // we use diffrential drive
  private final DifferentialDrive m_robotDrive = new DifferentialDrive(leftSide, rightSide);



 
  /** Creates a new DrivetrainSubsystem. */
  public DrivetrainSubsystem() 
  {


  }
//allows to input speed and turn for the robot
  public void driveTheBot (double speed, double turn)
  {

    m_robotDrive.arcadeDrive(speed,turn);
    
  }


  public double calculateApproachSpeed(double targetDist, int approachAlg)
  {
    double speed = 0;
    double slope = 0;
    double intercept = 0;

    if (targetDist >= Constants.DRIVER_ASSIST_CAUTION_DISTANCE) {
      // If the distance is greater than cautionDistance, go maxDriveSpeed.
      return Constants.DRIVER_ASSIST_MAX_DRIVE_SPEED;

    } else if (targetDist <= Constants.DRIVER_ASSIST_STOP_DISTANCE) {
      // If at or inside the stop distance, stop.
      return 0.0;
    }

    // The following section defines the speed profile between the caution
    // and stop distances where the profiles go through the following
    // two points.
    // 
    // (x1, y1) = (stop distance, min drive speed)
    // (x2, y2) = (caution distance, max drive speed)

    if (approachAlg == Constants.DRIVER_ASSIST_APPROACH_ALG_LINEAR) {
      // Linear - The speed is proportional to the distance away.
      // 
      // y = m * x + b
      // speed = m * (distance from target) + b

      // m = (y2 - y1) / (x2 - x1)
      slope = (Constants.DRIVER_ASSIST_MAX_DRIVE_SPEED-Constants.DRIVER_ASSIST_MIN_DRIVE_SPEED) / 
        (Constants.DRIVER_ASSIST_CAUTION_DISTANCE-Constants.DRIVER_ASSIST_STOP_DISTANCE);

      // b = y2 - (m * x2)
      intercept = Constants.DRIVER_ASSIST_MAX_DRIVE_SPEED - (slope * Constants.DRIVER_ASSIST_CAUTION_DISTANCE);

      // y = m * x + b
      speed = (slope * targetDist) + intercept;

    } else if (approachAlg == Constants.DRIVER_ASSIST_APPROACH_ALG_PARAB){
      // Parabola
      // 
      // This is a parabola that opens up and centered on (x1, y1) the stop distance and
      // min speed.  This drops the speed off quickly initially, and slowly approaches the stop point.
      // 
      // A standard parabola is:
      //   y = x^2
      // The vertex of the parabola can be translated left/right and up/down by (h, k) respectively with:
      //   y = a(x - h)^2 + k
      // 
      // We'll shift the parabola over to the caution distance and max speed with h = x1 and k = y1.
      //   y = slope (x - x1)^2 + y1
      // 
      // Where the final algorithm will be:
      //   speed = slope (target distance - x1)^2 + y1

      // The parabola above will go through (x1, y1) and we'll adjust the a (slope) value to curve
      // it down to (x2, y2).
      // 
      // y = slope (x - x1)^2 + y1
      // y - y1 = slope (x - x1)^2
      // (y - y1) / (x - x1)^2 = slope 
      // slope = (y - y1) / (x - x1)^2 
      slope = (Constants.DRIVER_ASSIST_MAX_DRIVE_SPEED - Constants.DRIVER_ASSIST_MIN_DRIVE_SPEED) /
        Math.pow(Constants.DRIVER_ASSIST_CAUTION_DISTANCE - Constants.DRIVER_ASSIST_STOP_DISTANCE, 2);

      // speed = slope (target distance - x1)^2 + y1
      speed = (
        slope * Math.pow(targetDist - Constants.DRIVER_ASSIST_STOP_DISTANCE, 2)
      ) + Constants.DRIVER_ASSIST_MIN_DRIVE_SPEED;
    
    } else {
      // Inverse Parabola
      // 
      // This is an upside down parabola with a vertex (x2, y2) the caution distance and max
      // speed.  The speed stays higer longer and quickly drops off when close to the stop
      // distance.
      // 
      // A standard parabola is:
      //   y = x^2
      // The vertex of the parabola can be translated left/right and up/down by (h, k) respectively with:
      //   y = a(x - h)^2 + k
      // 
      // We'll shift the parabola over to the caution distance and max speed with h = x2 and k = y2.
      //   y = slope (x - x2)^2 + y2
      // 
      // Where the final algorithm will be:
      //   speed = slope (target distance - x2)^2 + y2

      // The parabola above will go through (x2, y2) and we'll adjust the a (slope) value to curve
      // it down to (x1, y1).
      // 
      // y = slope (x - x2)^2 + y2
      // y - y2 = slope (x - x2)^2
      // (y - y2) / (x - x2)^2 = slope 
      // slope = (y - y2) / (x - x2)^2 
      slope = (Constants.DRIVER_ASSIST_MIN_DRIVE_SPEED - Constants.DRIVER_ASSIST_MAX_DRIVE_SPEED) / 
        Math.pow(Constants.DRIVER_ASSIST_STOP_DISTANCE - Constants.DRIVER_ASSIST_CAUTION_DISTANCE, 2);

      // speed = slope (target distance - x2)^2 + y2
      speed = (
        slope * Math.pow(targetDist - Constants.DRIVER_ASSIST_CAUTION_DISTANCE, 2)
      ) + Constants.DRIVER_ASSIST_MAX_DRIVE_SPEED;

    };
    return speed;
  }

  public void autoDriveStraight_until_wall(double targetDist, int approachAlg)
  {
    while (targetDist > Constants.DRIVER_ASSIST_STOP_DISTANCE)
    {
      SmartDashboard.putNumber("Ultrasonic", targetDist);
      SmartDashboard.putNumber("Alg", approachAlg);
      rightSide.setInverted(true);

      double speed = calculateApproachSpeed(targetDist, approachAlg);
      m_robotDrive.arcadeDrive(speed, 0);

      targetDist = UltrasonicFront.getFrontSensorDistance();
    }
    m_robotDrive.arcadeDrive(0, 0);
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run ''
  }
}
