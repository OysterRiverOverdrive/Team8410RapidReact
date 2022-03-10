// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants 
{

   
   public static int LEFT_MOTOR_1_CANID = 0; // first motor that runs drivetrain on the left side
   public static int LEFT_MOTOR_2_CANID = 1;// second motor ont he left that runs drivetrain
   public static int RIGHT_MOTOR_1_CANID = 2;// first motor that runs drivetrain on the right
   public static int RIGHT_MOTOR_2_CANID = 3;// second motor that runs drivetrain on the right

   public static int LIFTER_MOTOR_CANID = 4;// motor to raise intake 
   public static int INTAKE_MOTOR_CANID = 6;// motor to run the intake rollers
   public static int WINCH_MOTOR_CANID = 7;// the motor for winch
   public static int TWO_STAGE_HANGER_MOTOR_CANID = 8;// the motor for two stage
   public static int ONE_STAGE_LEFT_HANGER_MOTOR_CANID = 9;// the motor for left one stage
   public static int ONE_STAGE_RIGHT_HANGER_MOTOR_CANID = 10;//the motor for right one stage

   public static int HANGER_WINCH_ENCODER_PORT = 5;//the winch for hanging
   public static int HANGER_ONE_STAGE_LEFT_ENCODER_PORT = 6; //left one stage for hanging
   public static int HANGER_ONE_STAGE_RIGHT_ENCODER_PORT = 7; //right one stage for hanging
   public static int HANGER_TWO_STAGE_ENCODER_PORT = 8; //the two stage for hanging




}
