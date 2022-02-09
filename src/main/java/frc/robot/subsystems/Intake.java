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
import frc.robot.Constants;

public class Intake extends SubsystemBase{

    private final CANSparkMax deployer = new CANSparkMax(Constants.Deployer, MotorType.kBrushed);
    private final CANSparkMax intake = new CANSparkMax(Constants.Intake, MotorType.kBrushless);

    private final Encoder deployerEncoder = new Encoder(Constants.DeployerEncoder[0], Constants.DeployerEncoder[1]);

    private  DigitalInput intakeLimitSwitch = new DigitalInput(Constants.DeployerLimitSwitch);

    
    private BooleanSupplier deployState;
    
    private boolean flipIntake = false;
    private boolean flipDeployer = false;

    NetworkTableEntry intakeSpeed = Shuffleboard.getTab("TeleOp")
        .addPersistent("Intake Speed", 0)
        .withWidget(BuiltInWidgets.kNumberSlider)
        .withProperties(Map.of("min", -1, "max", 1))
        .getEntry();

    


    

    public Intake(){

        intake.setInverted(flipIntake);
        deployer.setInverted(flipDeployer);

        Shuffleboard.getTab("TeleOp")
            .addBoolean("Deploy Status", deployState);

        deployState = () -> intakeLimitSwitch.get();

    }



    public void enableIntake(){
        intake.set(intakeSpeed.getDouble(0));
    }


    public void stopIntake(){
        intakeSpeed.setDouble(0);
        intake.set(0);
        
    }

    public void setDeployerSpeed(double speed){
        deployer.set(speed);
    }

    public void toggleIntakeDeployState(){

        if(deployState.getAsBoolean()){
            

            //return intake back to stored position





        }else{
            


            //deploy intake


        }



    }

    public void periodic(){
        
    }

    public void update(){
        intake.set(intakeSpeed.getDouble(0));

    }

    
    
}
