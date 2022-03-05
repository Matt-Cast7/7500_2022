package frc.robot.subsystems;

import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.function.BooleanSupplier;

import com.revrobotics.CANSparkMax;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Index extends SubsystemBase {

    private final CANSparkMax leftMotor = new CANSparkMax(Constants.Indexer[0], MotorType.kBrushless);
    private final CANSparkMax rightMotor = new CANSparkMax(Constants.Indexer[1], MotorType.kBrushless);

    private final I2C.Port i2c;// = I2C.Port.kOnboard;

    private final I2C.Port i2c2;// = I2C.Port.kOnboard;

    private final ColorSensorV3 frontIndexColorSensor;// = new ColorSensorV3(i2c);
    private final ColorSensorV3 backIndexColorSensor;// = new ColorSensorV3(i2c);

    private final AnalogPotentiometer ultraSonic = new AnalogPotentiometer(Constants.uSensor);

    private final DigitalInput shooterEntry = new DigitalInput(Constants.ShooterLimitSwitch);

    private BooleanSupplier isIntaking;

    private BooleanSupplier endIndex;

    private boolean flipIndexMotors = false;

    private int uDistance = 1000;

    private final Color blueBall = new Color(0.136, 0.288, 0.218);
    private final Color redBall = new Color(0.266, 0.286, 0.139);
    private final Color greenBall = new Color(0.501, 0.1040, 0.260);


    private final ColorMatch colorMatcher = new ColorMatch();

    private final Ball AllianceColor;

    // NetworkTableEntry indexSpeed = Shuffleboard.getTab("TeleOp")
    // .addPersistent("Index Speed", 0)
    // .withWidget(BuiltInWidgets.kNumberSlider)
    // .withProperties(Map.of("min", -1, "max", 1))
    // .getEntry();

    public enum Ball {
        RED,
        BLUE,
        GREEN,
        NONE
    }

    public Index() {

        colorMatcher.addColorMatch(blueBall);
        colorMatcher.addColorMatch(redBall);
        colorMatcher.addColorMatch(greenBall);

        leftMotor.setInverted(!flipIndexMotors);
        rightMotor.setInverted(flipIndexMotors);

        leftMotor.setIdleMode(IdleMode.kBrake);
        rightMotor.setIdleMode(IdleMode.kBrake);

        i2c = I2C.Port.kOnboard;
        frontIndexColorSensor = new ColorSensorV3(i2c);

        i2c2 = I2C.Port.kMXP;

        backIndexColorSensor = new ColorSensorV3(i2c2);

        //AllianceColor = (DriverStation.getAlliance() == DriverStation.Alliance.Red) ? Ball.RED : Ball.BLUE;

        AllianceColor = Ball.RED;

        isIntaking = () -> {
            if (frontIndexColorSensor.getProximity() > uDistance) {
                return true;
            } else {
                return false;
            }
        };

        endIndex = () -> {
            if (ultraSonic.get() > uDistance) {
                return true;
            } else {
                return false;
            }
        };

        Shuffleboard.getTab("TeleOp")
                .addBoolean("Index On", () -> indexOn());

    }

    // public void setLeftMotor(double speed) {
    // indexSpeed.setDouble(speed);
    // leftMotor.set(speed);

    // }

    // public void setRightMotor(double speed) {
    // indexSpeed.setDouble(speed);
    // rightMotor.set(speed);
    // }

    public void setIndex(double speed) {
        rightMotor.set(speed);
        leftMotor.set(speed);
    }

    public void stop() {
        rightMotor.set(0);
        leftMotor.set(0);
    }

    public void enableIndex() {
        // setIndex(indexSpeed.getDouble(0));
        setIndex(0.25);


    }

    public void TTenableIndex(){
        if(!(detectFrontIndexBalls() == Ball.NONE)){

            if(detectFrontIndexBalls() == AllianceColor){
                if(detectBackIndexBalls() == Ball.NONE){
                    while(detectBackIndexBalls() == Ball.NONE){
                        setIndex(0.25);
                    }
                    setIndex(0);


                }else{
                    while(detectFrontIndexBalls() != Ball.NONE){
                        setIndex(0.25);
                    }
                    setIndex(0);

                }
            }else{

            }

        }
    }


    public void indexBall(){

        if(detectBackIndexBalls() == Ball.NONE){

        }else{

        }

    }

    public void spitBallBack(){

    }

    public void spitBallFront(){

    }

    public boolean indexOn() {
        if (leftMotor.get() != 0) {
            return true;
        } else
            return false;
    }


    boolean initingState = false;

    public void initFiring() {

        new Thread(() -> {
        Timer time = new Timer();
        time.start();
        setIndex(0.25);
        System.out.println("Init Firing");
        while(time.get() < 0.5){
            System.out.println("Init Firing");

        }
        stop();

        setIndex(-0.15);
        while(frontIndexColorSensor.getProximity() < 65){

        }

        stop();
        time.stop();
        initingState = true;
        }
         ).start();

        }

        public void finishedInitingIndex(){
            initingState = false;
        }

        public boolean isFinihsedIniting(){
            return initingState;
        }

    boolean firedBalls = false;

    public void fireBall() {
        
        new Thread(() -> {
        setIndex(0.5);
        while(!shooterEntry.get()){

        }
        stop();
        Timer time = new Timer();
        time.start();
        while(time.get() < 0.5){

        }

        setIndex(0.5);
        while(!shooterEntry.get()){

        }
        stop();
        firedBalls = true;
    }).start();

    }

    public void finishedFiring(){
        firedBalls = false;
    }

    public boolean isFiredBalls(){
        return firedBalls;
    }


    

    public void runBackwards() {
        setIndex(-0.25);
    }

    public double getDistanceCentimeters() {
        return (ultraSonic.get());// * 0.00125)*100;
    }

    @Override
    public void periodic() {
        // System.out.println(((double)(((int)(getDistanceCentimeters()*10000))))/10000);
        // System.out.println(ultraSonic.get());
        // System.out.println(colorSensor.getProximity());
        // System.out.println(colorSensor.getGreen());
        // System.out.println("Blue " + frontIndexColorSensor.getBlue() + ": Red " +
        //  frontIndexColorSensor.getRed()+": Green " + frontIndexColorSensor.getGreen() + " : " +
        // frontIndexColorSensor.getProximity());
        //System.out.println(detectFrontIndexBalls().toString() + " : " + detectBackIndexBalls());

    }

    public Ball detectBackIndexBalls() {
        ColorMatchResult match = colorMatcher.matchClosestColor(backIndexColorSensor.getColor());

        if (backIndexColorSensor.getProximity() > 340) {
            if (match.color == blueBall) {
                //System.out.println("Blue Ball : " + backIndexColorSensor.getProximity());
                return Ball.BLUE;
            } else if (match.color == redBall) {
               // System.out.println("Red Ball : " + backIndexColorSensor.getProximity());
                return Ball.RED;
            }else if(match.color == greenBall){
                return Ball.GREEN;
            }
        }
       // System.out.println("Nothing");
        return Ball.NONE;
    }

    public Ball detectFrontIndexBalls() {
        ColorMatchResult match = colorMatcher.matchClosestColor(frontIndexColorSensor.getColor());

        if (frontIndexColorSensor.getProximity() > 75) {
            if (match.color == blueBall) {
                //System.out.println("Blue Ball : " + frontIndexColorSensor.getProximity());
                return Ball.BLUE;
            } else if (match.color == redBall) {
               // System.out.println("Red Ball : " + frontIndexColorSensor.getProximity());

                return Ball.RED;
            }else if(match.color == greenBall){
                return Ball.GREEN;
            }
        }
      //  System.out.println("Nothing");
        return Ball.NONE;

    }

}