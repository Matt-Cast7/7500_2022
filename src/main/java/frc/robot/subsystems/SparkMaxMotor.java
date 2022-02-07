package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class SparkMaxMotor extends SubsystemBase{

    private final CANSparkMax motor = new CANSparkMax(14, MotorType.kBrushless);

    boolean flipped = true;

    public SparkMaxMotor(){
        motor.setInverted(flipped);
    }

    public void set(double speed){
        motor.set(speed);
    }

    
}
