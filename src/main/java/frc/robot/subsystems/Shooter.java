package frc.robot.subsystems;

import java.util.Map;
import java.util.function.BooleanSupplier;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.controller.BangBangController;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase{


    private final CANSparkMax shooterMaster = new CANSparkMax(Constants.Shooter[0], MotorType.kBrushless);
    private final CANSparkMax shooterSlave = new CANSparkMax(Constants.Shooter[1], MotorType.kBrushless);


    private NetworkTableEntry shooterSpeed = Shuffleboard.getTab("TeleOp")
        .addPersistent("Shooter Speed", 0)
        .withWidget(BuiltInWidgets.kNumberSlider)
        .withProperties(Map.of("min", -1, "max", 1))
        .getEntry();



    private boolean flipShooter = false;

    private NetworkTableEntry wheelSpeed = Shuffleboard.getTab("TeleOp")
            .addPersistent("Shooter Wheel RPM", 0)
            .withWidget(BuiltInWidgets.kDial)
            .getEntry();


    private double targetSpeed = 1;


    
    public Shooter(){
        
        shooterMaster.setInverted(flipShooter);
        shooterSlave.setInverted(flipShooter);

        shooterMaster.setIdleMode(IdleMode.kCoast);
        shooterSlave.setIdleMode(IdleMode.kCoast);
        
        shooterMaster.follow(shooterSlave, true);

        shooterMaster.getEncoder().setVelocityConversionFactor(Constants.shooterGearing);

        Shuffleboard.getTab("TeleOp")
            .addBoolean("Shooter Ready to Fire", () -> isShooterUptoSpeed());


    }

    @Override
    public void periodic(){

        wheelSpeed.setDouble(shooterMaster.getEncoder().getVelocity());


    }

    public void enable(){
        shooterMaster.set(shooterSpeed.getDouble(0));
    }

    public void stop(){
        shooterMaster.set(0);
    }



    public void setShooterSpeed(double speed){
        shooterSpeed.setDouble(speed);
        shooterMaster.set(speed);
    }

    public void update(){
        shooterMaster.set(shooterSpeed.getDouble(0));
    }

    public boolean isShooterUptoSpeed(){
        if(wheelSpeed.getDouble(0) < targetSpeed){
            return false;
        }else{
            return true;
        }
    }
    
}
