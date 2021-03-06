package frc.robot.team8410.subsystems;

import static org.junit.jupiter.api.Assertions.*;
           
import org.junit.jupiter.api.Test;
import frc.robot.Constants;

public class TestDrivetrainSubsystem {
    private final DrivetrainSubsystem drivetrain = new DrivetrainSubsystem(); 

    //test to check approach speed
    @Test
    public void testCalculateApproachSpeed() {

        // Check the extremes.
        double speed = drivetrain.calculateApproachSpeed(Constants.DRIVER_ASSIST_CAUTION_DISTANCE + 1, Constants.DRIVER_ASSIST_APPROACH_ALG_LINEAR);
        assertEquals(Constants.DRIVER_ASSIST_MAX_DRIVE_SPEED, speed, "should get max speed outside caution distance");

        speed = drivetrain.calculateApproachSpeed(Constants.DRIVER_ASSIST_STOP_DISTANCE - 1, Constants.DRIVER_ASSIST_APPROACH_ALG_LINEAR);
        assertEquals(0, speed, "should stop inside the stop distance");

        // Check speeds using linear.
        speed = drivetrain.calculateApproachSpeed(Constants.DRIVER_ASSIST_CAUTION_DISTANCE - 1, Constants.DRIVER_ASSIST_APPROACH_ALG_LINEAR);
        assertTrue(speed > 0.7, "should still be fast just inside the caution distance: "+speed);

        speed = drivetrain.calculateApproachSpeed(Constants.DRIVER_ASSIST_STOP_DISTANCE + 1, Constants.DRIVER_ASSIST_APPROACH_ALG_LINEAR);
        assertTrue(speed < 0.4, "should be slow just outside the stop distance: "+speed);

        speed = drivetrain.calculateApproachSpeed(Constants.DRIVER_ASSIST_STOP_DISTANCE + 1, Constants.DRIVER_ASSIST_APPROACH_ALG_LINEAR);
        assertTrue(speed > Constants.DRIVER_ASSIST_MIN_DRIVE_SPEED, "speed greater than min "+speed);

        // Check speeds using parabolic.
        speed = drivetrain.calculateApproachSpeed(Constants.DRIVER_ASSIST_CAUTION_DISTANCE - 1, Constants.DRIVER_ASSIST_APPROACH_ALG_PARAB);
        assertTrue(speed > 0.9 * Constants.DRIVER_ASSIST_MAX_DRIVE_SPEED, "should still be fast just inside the caution distance: "+speed);

        speed = drivetrain.calculateApproachSpeed(Constants.DRIVER_ASSIST_STOP_DISTANCE + 1, Constants.DRIVER_ASSIST_APPROACH_ALG_PARAB);
        assertTrue(speed < 1.1 * Constants.DRIVER_ASSIST_MIN_DRIVE_SPEED, "should be slow just outside the stop distance: "+speed);

        speed = drivetrain.calculateApproachSpeed(Constants.DRIVER_ASSIST_STOP_DISTANCE + 1, Constants.DRIVER_ASSIST_APPROACH_ALG_PARAB);
        assertTrue(speed > Constants.DRIVER_ASSIST_MIN_DRIVE_SPEED, "speed greater than min "+speed);

        // Check speeds using inverse parabolic.
        speed = drivetrain.calculateApproachSpeed(Constants.DRIVER_ASSIST_CAUTION_DISTANCE - 1, Constants.DRIVER_ASSIST_APPROACH_ALG_INVPARAB);
        assertTrue(speed > 0.9 * Constants.DRIVER_ASSIST_MAX_DRIVE_SPEED, "should still be fast just inside the caution distance: "+speed);

        speed = drivetrain.calculateApproachSpeed(Constants.DRIVER_ASSIST_STOP_DISTANCE + 1, Constants.DRIVER_ASSIST_APPROACH_ALG_INVPARAB);
        assertTrue(speed < 1.1 * Constants.DRIVER_ASSIST_MIN_DRIVE_SPEED, "should be slow just outside the stop distance: "+speed);

        speed = drivetrain.calculateApproachSpeed(Constants.DRIVER_ASSIST_STOP_DISTANCE + 1, Constants.DRIVER_ASSIST_APPROACH_ALG_INVPARAB);
        assertTrue(speed > Constants.DRIVER_ASSIST_MIN_DRIVE_SPEED, "speed greater than min "+speed);

    }
 }