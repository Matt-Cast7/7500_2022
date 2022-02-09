package frc.robot.subsystems;

import java.util.Map;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase{


    private final CANSparkMax shooterMaster = new CANSparkMax(Constants.Shooter[0], MotorType.kBrushless);
    private final CANSparkMax shooterSlave = new CANSparkMax(Constants.Shooter[1], MotorType.kBrushless);

    NetworkTableEntry shooterSpeed = Shuffleboard.getTab("TeleOp")
        .addPersistent("Shooter Speed", 0)
        .withWidget(BuiltInWidgets.kNumberSlider)
        .withProperties(Map.of("min", -1, "max", 1))
        .getEntry();



    boolean flipShooter = false;

    public Shooter(){
        
        shooterMaster.setInverted(flipShooter);
        shooterSlave.setInverted(flipShooter);

        shooterMaster.setIdleMode(IdleMode.kCoast);
        shooterSlave.setIdleMode(IdleMode.kCoast);
        
        shooterMaster.follow(shooterSlave, true);
        

    }

    @Override
    public void periodic(){
        
    }

    public void enable(){
        shooterSpeed.setDouble(0.1);
        shooterMaster.set(0.1);
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
    
}
