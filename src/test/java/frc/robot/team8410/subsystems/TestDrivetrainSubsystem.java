package frc.robot.team8410.subsystems;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import frc.robot.Constants;

public class TestDrivetrainSubsystem {
    private final DrivetrainSubsystem drivetrain = new DrivetrainSubsystem(); 

    //test to check approach speed
    @Test
    public void testCalculateApproachSpeed() {
        double speed = drivetrain.calculateApproachSpeed(Constants.DRIVER_ASSIST_CAUTION_DISTANCE, 1);
        assertEquals(1, speed, "should get max speed at caution distance");

        speed = drivetrain.calculateApproachSpeed(Constants.DRIVER_ASSIST_STOP_DISTANCE, 1);
        assertEquals(0, speed, "should stop at stop distance");
    }
 }