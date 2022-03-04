package frc.robot.team8410.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

public class HangerSubsystem extends SubsystemBase {
    
    PWMVictorSPX armMotor1 = new PWMVictorSPX(Constants.TOP_RIGHT_HANGER_MOTOR_CANID);
    PWMVictorSPX armMotor2 = new PWMVictorSPX(Constants.TOP_LEFT_HANGER_MOTOR_CANID);
    MotorControllerGroup armGroup = new MotorControllerGroup(armMotor1, armMotor2);
    PWMVictorSPX clipMotor1 = new PWMVictorSPX(Constants.BOTTOM_RIGHT_HANGER_MOTOR_CANID);
    PWMVictorSPX clipMotor2 = new PWMVictorSPX(Constants.BOTTOM_LEFT_HANGER_MOTOR_CANID);
    MotorControllerGroup clipGroup = new MotorControllerGroup(clipMotor1, clipMotor2);

    //using conforce spring + winch to rotate arm
    PWMVictorSPX winchMotor = new PWMVictorSPX(10);
    
    private DutyCycleEncoder arm_Encoder = new DutyCycleEncoder(0);
    private DutyCycleEncoder clip_Encoder = new DutyCycleEncoder(1);
    private DutyCycleEncoder winch_Encoder = new DutyCycleEncoder(2);
    
    //winch causes arm to rotate up

    public void armUp(double upDist){
        arm_Encoder.reset();
        if(arm_Encoder.getDistance() < upDist){
            armGroup.set(1.0);
        }
        else{
            armGroup.set(0.0);
        }
    }

    public void armDown(double downDist){
        arm_Encoder.reset();
        armGroup.setInverted(true);
        if(arm_Encoder.getDistance() < downDist){
            armGroup.set(1.0);
        }
        else{
            armGroup.set(0.0);
        }
    }
    
    public void clipUp(double clipUpDist){
        clip_Encoder.reset();
        if(clip_Encoder.getDistance() < clipUpDist){
            clipGroup.set(1.0);
        }
        else{
            clipGroup.set(0.0);
        }
    }

    public void clipDown(double clipDownDist){
        clip_Encoder.reset();
        if(clip_Encoder.getDistance() < clipDownDist){
            clipGroup.set(1.0);
        }
        else{
            clipGroup.set(0.0);
        }
    }

    //rotate method causes arm to rotate up
    public void rotate(double rotateDist){
        winch_Encoder.reset();
        if(winch_Encoder.getDistance() < rotateDist){ 
            winchMotor.set(1.0); //sets speed of winch motor to 1
        }
        else{
            winchMotor.set(0.0); //sets speed of winch motor to 0
        }
    }


    public HangerSubsystem() {}

    @Override
    public void periodic() {
      arm_Encoder.setDistancePerRotation(1.0);
      clip_Encoder.setDistancePerRotation(1.0);
      winch_Encoder.setDistancePerRotation(1.0);
    }
    // This method will be called once per scheduler run
}
