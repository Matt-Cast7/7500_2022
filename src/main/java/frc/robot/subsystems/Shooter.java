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

public class Shooter extends SubsystemBase{


    private final CANSparkMax shooterMaster = new CANSparkMax(Constants.Shooter[0], MotorType.kBrushless);
    private final CANSparkMax shooterSlave = new CANSparkMax(Constants.Shooter[1], MotorType.kBrushless);

    


    private NetworkTableEntry shooterSpeed = Shuffleboard.getTab("TeleOp")
        .addPersistent("Shooter Speed", 0)
        .withWidget(BuiltInWidgets.kNumberSlider)
        .withProperties(Map.of("min", -1, "max", 1))
        .getEntry();



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


    private double targetSpeed = 2900;


    
    public Shooter(){
        
        shooterMaster.setInverted(flipShooter);
        shooterSlave.setInverted(!flipShooter);

        shooterMaster.setIdleMode(IdleMode.kCoast);
        shooterSlave.setIdleMode(IdleMode.kCoast);
        
        //shooterMaster.follow(shooterSlave, true);

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
        shooterSlave.set(shooterSpeed.getDouble(0));

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
    
}
