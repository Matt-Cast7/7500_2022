package frc.robot.subsystems;

import java.util.Map;
import java.util.function.BooleanSupplier;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Index extends SubsystemBase {

    private final CANSparkMax leftMotor = new CANSparkMax(Constants.Indexer[0], MotorType.kBrushless);
    private final CANSparkMax rightMotor = new CANSparkMax(Constants.Indexer[1], MotorType.kBrushless);

    private BooleanSupplier position1 = () -> {
        return false;
    };

    private BooleanSupplier position2 = () -> {
        return false;
    };

    private BooleanSupplier position3 = () -> {
        return false;
    };

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

    public Index() {

        leftMotor.setInverted(!flipIndexMotors);
        rightMotor.setInverted(flipIndexMotors);

        leftMotor.setIdleMode(IdleMode.kBrake);
        rightMotor.setIdleMode(IdleMode.kBrake);

        Shuffleboard.getTab("TeleOp").addBoolean("Index Full", () -> isFull());

    }

    public void setLeftMotor(double speed) {
        leftMotorSpeed.setDouble(speed);
        leftMotor.set(speed);

    }

    public void setRightMotor(double speed) {
        rightMotorSpeed.setDouble(speed);
        rightMotor.set(speed);
    }

    public void setIndex(double speed) {
        leftMotorSpeed.setDouble(speed);
        rightMotorSpeed.setDouble(speed);

        rightMotor.set(speed);
        leftMotor.set(speed);
    }

    public void stop() {
        leftMotorSpeed.setDouble(0);
        rightMotorSpeed.setDouble(0);

        rightMotor.set(0);
        leftMotor.set(0);
    }

    public boolean isFull() {
        return (position1.getAsBoolean() && position2.getAsBoolean()) ? true : false;
    }

    public boolean isEmpty() {
        return (!position1.getAsBoolean() && !position2.getAsBoolean()) ? true : false;
    }

    public void intakingBall() {
        if (isFull()) {
            if (isEmpty()) {
                setIndex(0.1);
                
                while (!position1.getAsBoolean()) {}
                
                stop();

            } else if (position1.getAsBoolean()) {
                
                setIndex(0.1);

                while (!position2.getAsBoolean()) {}

                stop();
            }
        }
    }

    public void fireBall(){
        if(isEmpty()){
            if(isFull()){
                setIndex(0.1);

                while(position2.getAsBoolean()){}

                while(!position2.getAsBoolean()){}

                stop();
            }else{
                setIndex(0.1);

                while(!position2.getAsBoolean()){}

                while(position2.getAsBoolean()){}

                while(!position3.getAsBoolean()){}

                stop();
            }
        }
    }

    @Override
    public void periodic() {

    }

    public void update() {
        leftMotor.set(leftMotorSpeed.getDouble(0));
        rightMotor.set(rightMotorSpeed.getDouble(0));
    }
}