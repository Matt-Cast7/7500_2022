package frc.robot.subsystems;

import java.util.Map;
import java.util.function.BooleanSupplier;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase{

    private final CANSparkMax deployer = new CANSparkMax(5, MotorType.kBrushed);
    private final CANSparkMax intake = new CANSparkMax(0, MotorType.kBrushless);

    private final Encoder deployerEncoder = new Encoder(0, 1);

    private final DigitalInput intakeSwitch = new DigitalInput(0);

    
    BooleanSupplier deployState = () -> {
        return false;
    };
    
    boolean flipIndex = false;

    NetworkTableEntry intakeMaxSpeed = Shuffleboard.getTab("TeleOp")
        .addPersistent("Max Intake Speed", 0.1)
        .withWidget(BuiltInWidgets.kNumberSlider)
        .withProperties(Map.of("min", 0, "max", 1))
        .getEntry();

    


    

    public Intake(){

        Shuffleboard.getTab("TeleOp")
            .addBoolean("Deploy Status", deployState);

    }



    public void enableIntake(){
        intake.set(intakeMaxSpeed.getDouble(0.1));
    }

    public void stopIntake(){
        intake.set(0);
    }

    public void toggleIntakeStatus(){

        if(deployState.getAsBoolean()){
            deployState = () ->{
                return false;
            };






        }else{
            deployState = () ->{
                return true;
            };




        }



    }

    public void periodic(){
        
    }
    
}
