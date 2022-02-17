package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Gimbal extends SubsystemBase{
    
    
    private Servo servox = new Servo(0);
    private Servo servoy = new Servo(0);
    
    
    public Gimbal(){
        //add servo.set something//
    }

    public void setServoX(double value){
        servox.set(value);
    }
    public void setServoY(double value){
        servoy.set(value);
    }

}
