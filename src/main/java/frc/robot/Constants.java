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
   public static int WINCH_MOTOR_CANID = 7;// the motor for winch
   public static int TWO_STAGE_HANGER_MOTOR_CANID = 8;// the motor for two stage
   public static int ONE_STAGE_LEFT_HANGER_MOTOR_CANID = 9;// the motor for left one stage
   public static int ONE_STAGE_RIGHT_HANGER_MOTOR_CANID = 10;//the motor for right one stage

   public static int INTAKE_ARM_POT_PORT_ID = 0; // Analog for intake ARM port
   public static int HANGER_WINCH_ENCODER_PORT = 5;//the winch for hanging
   public static int HANGER_ONE_STAGE_LEFT_ENCODER_PORT = 6; //left one stage for hanging
   public static int HANGER_ONE_STAGE_RIGHT_ENCODER_PORT = 7; //right one stage for hanging
   public static int HANGER_TWO_STAGE_ENCODER_PORT = 8; //the two stage for hanging

   public static int BALL_IS_RED = 3; // this is the threshold for red color ball
   public static int BALL_IS_BLUE = 1; // this is the threshold for Blue color ball

   public static int DRIVER_PORT = 0;//Xbox pro
   public static int OPERATOR_PORT = 1;//Logi Dual action

   public static int WINCH_BUTTON = 1;
   public static int DRIVER_ASSIST_BUTTON = 8;
   public static int INTAKE_BUTTON_RISE = 5;
   public static int INTAKE_BUTTON_LOWER = 6;
   public static int JOYSTICK_LEFT_TRIGGER = 2;
   public static int JOYSTICK_RIGHT_TRIGGER = 3;

   // Intake prameters
   public static double INTAKE_RAISE_MAX_SPEED = 0.8; // The fastest in both directions we want the motor to run.// was 0.6 speed
   public static double INTAKE_POT_HIGH_STOP = 2.2; // The point we consider fully up.// was 2.1
   public static double INTAKE_POT_HIGH_CAUTION = 1.8; // The point during raise where we start slowing down.
   public static double INTAKE_POT_LOW_CAUTION = 1.8; // The point during lowering where we start slowing down.
   public static double INTAKE_POT_LOW_STOP = 1.0; // The point we consider fully down.

   // these are when the goldfish bakes in the bakeChocolateGoldfish method
   public static int PDP_CAN_ID = 0;
   public static int BAKE_POWER_TEMPERATURE = 35;
   public static int BAKE_POWER_CURRENT = 20;
   public static int BAKE_POWER_VOLTAGE = 1;
   public static int BAKE_TOTAL_POWER = 1;

   public static int LEFT_ENCODER_0 = 0;
   public static int LEFT_ENCODER_1 = 1;
   public static int RIGHT_ENCODER_2 = 2;
   public static int RIGHT_ENCODER_3 = 3;

   // Parameters to adjust driver assist algorithms. 
   public static double DRIVER_ASSIST_STOP_DISTANCE = 20.0; // inches
   public static double DRIVER_ASSIST_CAUTION_DISTANCE = 150.0; // inches
   public static double DRIVER_ASSIST_MAX_DRIVE_SPEED = 0.8; // range 0-1
   public static double DRIVER_ASSIST_MIN_DRIVE_SPEED = 0.3; // range 0-1
   public static int DRIVER_ASSIST_APPROACH_ALG_LINEAR = 0;
   public static int DRIVER_ASSIST_APPROACH_ALG_PARAB = 1;
   public static int DRIVER_ASSIST_APPROACH_ALG_INVPARAB = 2;
   public boolean armTrue = true;

}
