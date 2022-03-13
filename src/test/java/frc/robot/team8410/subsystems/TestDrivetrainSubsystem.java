package frc.robot.team8410.subsystems;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import frc.robot.Constants;

public class TestDrivetrainSubsystem {
    private final DrivetrainSubsystem drivetrain = new DrivetrainSubsystem(); 

    //test to check approach speed
    @Test
    public void testCalculateApproachSpeed() {
        double speed = drivetrain.calculateApproachSpeed(Constants.DRIVER_ASSIST_CAUTION_DISTANCE + 0.001, Constants.DRIVER_ASSIST_APPROACH_ALG_LINEAR);
        assertEquals(Constants.DRIVER_ASSIST_MAX_DRIVE_SPEED, speed, "should get max speed at caution distance");

        speed = drivetrain.calculateApproachSpeed(Constants.DRIVER_ASSIST_STOP_DISTANCE - 0.001, Constants.DRIVER_ASSIST_APPROACH_ALG_LINEAR);
        assertEquals(0, speed, "should stop at stop distance");
    }
 }