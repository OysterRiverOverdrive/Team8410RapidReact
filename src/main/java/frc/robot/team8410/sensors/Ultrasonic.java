package frc.robot.team8410.sensors;

import edu.wpi.first.wpilibj.AnalogInput;

public class Ultrasonic {
    private AnalogInput analogInput;

    public Ultrasonic(AnalogInput analogInput) {
        this.analogInput = analogInput;
    }

    // TODO: are we sure these values are inches
    public double getFrontSensorDistance() {
        return this.analogInput.getValue() * 0.0536;
    }
}
