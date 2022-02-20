package frc.robot.subsystems;

import java.util.Map;
import java.util.function.BooleanSupplier;

import com.revrobotics.CANSparkMax;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Index extends SubsystemBase {

    private final CANSparkMax leftMotor = new CANSparkMax(Constants.Indexer[0], MotorType.kBrushless);
    private final CANSparkMax rightMotor = new CANSparkMax(Constants.Indexer[1], MotorType.kBrushless);

    private final I2C.Port i2c;// = I2C.Port.kOnboard;

    private final ColorSensorV3 colorSensor;// = new ColorSensorV3(i2c);

    private final AnalogInput ultraSonic = new AnalogInput(Constants.uSensor);

    private final DigitalInput shooterEntry = new DigitalInput(Constants.ShooterLimitSwitch);
    


    private BooleanSupplier isIntaking;

    private BooleanSupplier endIndex;

    private boolean flipIndexMotors = false;

    private int uDistance = 1000;

    NetworkTableEntry indexSpeed = Shuffleboard.getTab("TeleOp")
            .addPersistent("Index Speed", 0)
            .withWidget(BuiltInWidgets.kNumberSlider)
            .withProperties(Map.of("min", -1, "max", 1))
            .getEntry();

    public Index() {

        leftMotor.setInverted(!flipIndexMotors);
        rightMotor.setInverted(flipIndexMotors);

        leftMotor.setIdleMode(IdleMode.kBrake);
        rightMotor.setIdleMode(IdleMode.kBrake);


        i2c = I2C.Port.kOnboard;
        colorSensor = new ColorSensorV3(i2c);

        isIntaking = () -> {
            if(colorSensor.getProximity() > uDistance){
                return true;
            }else{
                return false;
            }
        };

        endIndex = () ->{
            if(ultraSonic.getValue() > uDistance){
                return true;
            }else{
                return false;
            }
        };

        

        

    }

    // public void setLeftMotor(double speed) {
    //     indexSpeed.setDouble(speed);
    //     leftMotor.set(speed);

    // }

    // public void setRightMotor(double speed) {
    //     indexSpeed.setDouble(speed);
    //     rightMotor.set(speed);
    // }

    public void setIndex(double speed) {        
        rightMotor.set(speed);
        leftMotor.set(speed);
    }

    public void stop() {
        rightMotor.set(0);
        leftMotor.set(0);
    }

    public void enableIndex(){
        setIndex(indexSpeed.getDouble(0));
    }
    

    public void indexBall(){
        if(getDistanceCentimeters() < 40){
            while(isIntaking.getAsBoolean()){
                setIndex(0.10);
            }
            stop();
        }else{
            while(getDistanceCentimeters() > 40){
                setIndex(0.1);
            }
            stop();
        }
    }

    public void initFiring(){
        while(getDistanceCentimeters() < 45){
            setIndex(0.1);
        }
        stop();
    }

    public void fireBall(){
        setIndex(0.5);

        
    }

    public double getDistanceCentimeters(){
        return (ultraSonic.getValue() * 0.00125)*100;
    }

    

    @Override
    public void periodic() {

    }

    
}