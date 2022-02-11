package frc.robot.subsystems;

import java.util.Map;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Index extends SubsystemBase{


    private final CANSparkMax leftMotor = new CANSparkMax(Constants.Indexer[0], MotorType.kBrushless);
    private final CANSparkMax rightMotor = new CANSparkMax(Constants.Indexer[1], MotorType.kBrushless);

    boolean flipIndexMotors = false;

    NetworkTableEntry leftMotorSpeed = Shuffleboard.getTab("TeleOp")
        .addPersistent("Left Index Speed", 0)
        .withWidget(BuiltInWidgets.kNumberSlider)
        .withProperties(Map.of("min", -1, "max", 1))
        .getEntry();

    NetworkTableEntry rightMotorSpeed = Shuffleboard.getTab("TeleOp")
        .addPersistent("Right Index Speed", 0)
        .withWidget(BuiltInWidgets.kNumberSlider)
        .withProperties(Map.of("min", -1, "max", 1))
        .getEntry();

    public Index(){

        leftMotor.setInverted(!flipIndexMotors);
        rightMotor.setInverted(flipIndexMotors);

        leftMotor.setIdleMode(IdleMode.kBrake);
        rightMotor.setIdleMode(IdleMode.kBrake);


    }

    public void setLeftMotor(double speed){
        leftMotorSpeed.setDouble(speed);
        leftMotor.set(speed);

    }
    
    public void setRightMotor(double speed){
        rightMotorSpeed.setDouble(speed);
        rightMotor.set(speed);
    }

    public void setIndex(double speed){
        leftMotorSpeed.setDouble(speed);
        rightMotorSpeed.setDouble(speed);

        rightMotor.set(speed);
        leftMotor.set(speed);
    }

    public void stop(){
        leftMotorSpeed.setDouble(0);
        rightMotorSpeed.setDouble(0);

        rightMotor.set(0);
        leftMotor.set(0);
    }


    @Override
    public void periodic(){

    }

    public void update(){
        leftMotor.set(leftMotorSpeed.getDouble(0));
        rightMotor.set(rightMotorSpeed.getDouble(0));
    }
}
