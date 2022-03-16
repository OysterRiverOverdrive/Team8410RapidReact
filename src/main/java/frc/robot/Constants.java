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

   
   public static int LEFT_MOTOR_1_CANID = 1; // first motor that runs drivetrain on the left side
   public static int LEFT_MOTOR_2_CANID = 2;// second motor ont he left that runs drivetrain
   public static int RIGHT_MOTOR_1_CANID = 3;// first motor that runs drivetrain on the right
   public static int RIGHT_MOTOR_2_CANID = 4;// second motor that runs drivetrain on the right

   public static int INTAKE_LIFTER_MOTOR_CANID = 5;// motor to raise intake 
   public static int INTAKE_ROLLER_MOTOR_CANID = 6;// motor to run the intake rollers
   public static int TOP_RIGHT_HANGER_MOTOR_CANID = 7;// the motor for hanger on the top right
   public static int BOTTOM_RIGHT_HANGER_MOTOR_CANID = 8;// the motor for hanger on the bottom right
   public static int TOP_LEFT_HANGER_MOTOR_CANID = 9;// the motor for hanger on the top left
   public static int BOTTOM_LEFT_HANGER_MOTOR_CANID = 10;// the motor for hanger on the bottom left

   public static int BALL_IS_RED = 3; // this is the threshold for red color ball
   public static int BALL_IS_BLUE = 1; // this is the threshold for Blue color ball

   public static int INTAKE_ARM_POT_PORT_ID = 0; // Analog for intake ARM port
   
   public static int JOYSTICK_PORT = 0;

   // these are when the goldfish bakes in the bakeChocolateGoldfish method
   public static int BAKE_POWER_TEMPERATURE = 35;
   public static int BAKE_POWER_CURRENT = 20;
   public static int BAKE_POWER_VOLTAGE = 1;
   public static int BAKE_TOTAL_POWER = 1;

   public static int LEFT_ENCODER_0 = 0;
   public static int LEFT_ENCODER_1 = 1;
   public static int RIGHT_ENCODER_2 = 2;
   public static int RIGHT_ENCODER_3 = 3;

}
