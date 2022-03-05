package frc.robot.subsystems;

import java.util.Map;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.drive.DifferentialDrive.WheelSpeeds;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase{


    private final CANSparkMax shooterMaster = new CANSparkMax(Constants.Shooter[0], MotorType.kBrushless);
    private final CANSparkMax shooterSlave = new CANSparkMax(Constants.Shooter[1], MotorType.kBrushless);

    

    // private NetworkTableEntry shooterSpeed = Shuffleboard.getTab("TeleOp")
    //     .addPersistent("Shooter Speed", 0)
    //     .withWidget(BuiltInWidgets.kNumberSlider)
    //     .withProperties(Map.of("min", -1, "max", 1))
    //     .getEntry();

    private PIDController pid = new PIDController(0.0000001, 0.0003, 0);
    private SimpleMotorFeedforward feedforward = new SimpleMotorFeedforward(0.02, 0.01);

    private boolean flipShooter = true;

    // private NetworkTableEntry targetRpm = Shuffleboard.getTab("TeleOp")
    //         .addPersistent("Target Shooter Wheel RPM", 0)
    //         .withWidget(BuiltInWidgets.kNumberSlider)
    //         .withProperties(Map.of("min", 0, "max", 100))
    //         .getEntry();

    private NetworkTableEntry wheelSpeed = Shuffleboard.getTab("TeleOp")
            .add("Shooter Wheel RPM", 0)
            .withWidget(BuiltInWidgets.kDial)
            .getEntry();

    


    private double targetSpeed = 4500;

    private static double shooterSpeed = 0.75;

    private static boolean targetGoal = true; 

    
    
    public Shooter(){
        
        shooterMaster.setInverted(flipShooter);
        shooterSlave.setInverted(!flipShooter);

        shooterMaster.setIdleMode(IdleMode.kCoast);
        shooterSlave.setIdleMode(IdleMode.kCoast);
        
        //shooterMaster.follow(shooterSlave, true);

        shooterMaster.getEncoder().setVelocityConversionFactor(Constants.shooterGearing);

        Shuffleboard.getTab("TeleOp")
            .addBoolean("Shooter Ready to Fire", () -> isShooterUptoSpeed());

        
            Shuffleboard.getTab("TeleOp")
            .addBoolean("High or Low Goal", () -> getTargetGoal());
            
            Shuffleboard.getTab("TeleOp").addNumber("Motor Power", () -> shooterMaster.get());

    }

    @Override
    public void periodic(){

        wheelSpeed.setDouble(shooterMaster.getEncoder().getVelocity());


    }

    public void enable(){
        // shooterMaster.set(shooterSpeed.getDouble(0));
        // shooterSlave.set(shooterSpeed.getDouble(0));


        shooterMaster.set(shooterSpeed);
        shooterSlave.set(shooterSpeed);

        
    }

    public void resetPID(){
        pid.reset();
    }

    public void enablePID(){
        //double power = MathUtil.clamp((pid.calculate(wheelSpeed.getDouble(0) + feedforward.calculate(targetSpeed), targetSpeed)), -1, 1);
        double power = MathUtil.clamp((pid.calculate(Math.abs(wheelSpeed.getDouble(0)), targetSpeed)), -1, 1);
        
        shooterMaster.set(power);
        shooterSlave.set(power);
    }

    public void stop(){
        shooterMaster.set(0);
        shooterSlave.set(0);
    }



    public void setShooterSpeed(double speed){
        
        shooterMaster.set(speed);
        shooterSlave.set(speed);
    }


    public boolean isShooterUptoSpeed(){
        if(wheelSpeed.getDouble(0) < targetSpeed){
            return false;
        }else{
            return true;
        }
    }

    public void setHighGoal(){
        //shooterSpeed = 0.67;
        targetSpeed = 4500;
        targetGoal = true;
    }

    public void setLowGoal(){
        //shooterSpeed = 0.45;
        targetSpeed = 2100;
        targetGoal = false;
    }

    public boolean getTargetGoal(){
        return targetGoal;
    }

    public void runBackwards(){
        setShooterSpeed(0.1);
    }
    
    public double getAngularVelocity(){
        return ((2*Math.PI)/60)*wheelSpeed.getDouble(0);
    }
}
