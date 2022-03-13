package frc.robot.team8410.subsystems;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TestDrivetrainSubsystem {
    private final DrivetrainSubsystem drive = new DrivetrainSubsystem(); 

    //test to check approach speed
    @Test
    public void testCalculateApproachSpeed() {
        double speed = drive.calculateApproachSpeed(1, 40);
        assertEquals(1, speed, "should get max speed at caution distance");
    }
 }