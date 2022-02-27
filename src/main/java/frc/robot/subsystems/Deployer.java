package frc.robot.subsystems;

import java.util.Map;
import java.util.function.BooleanSupplier;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.controller.ArmFeedforward;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Deployer extends SubsystemBase {

    private final CANSparkMax deployer = new CANSparkMax(Constants.Deployer, MotorType.kBrushed);

    private ArmFeedforward feedforward = new ArmFeedforward(0.2 * 12, 0, 0, 0);
    private DigitalInput intakeLimitSwitch = new DigitalInput(Constants.DeployerLimitSwitch);
    private final Encoder encoder = new Encoder(Constants.DeployerEncoder[0], Constants.DeployerEncoder[1]);
    //private RelativeEncoder encoder;// = deployer.getEncoder();

    private BooleanSupplier deployState;
    private boolean flipDeployer = false;

    private double intakeAngle;

    private double retractedAngle = 140;
    private double deployedAngle = 87;

    double zero = 0;
 
    // NetworkTableEntry deployerSpeed = Shuffleboard.getTab("TeleOp")
    //         .addPersistent("Deployer Speed", 0)
    //         .withWidget(BuiltInWidgets.kNumberSlider)
    //         .withProperties(Map.of("min", -1, "max", 1))
    //         .getEntry();

            
    public Deployer() {
        deployer.setInverted(flipDeployer);

        deployer.setIdleMode(IdleMode.kCoast);

        deployState = () -> intakeLimitSwitch.get();

        // Shuffleboard.getTab("TeleOp")
        // .addBoolean("Deploy Status", deployState);

        // intakeAngle = (deployState.getAsBoolean()) ? deployedAngle : retractedAngle;

        //encoder.reset();
        Shuffleboard.getTab("TeleOp").addNumber("Deployer Amps", () -> deployer.getOutputCurrent());
        //encoder = deployer.getAlternateEncoder(8192);
        
    }

    public void periodic(){
        //System.out.println(encoder.getPosition() + zero);
        //System.out.println(encoder.get());
    }

    public void setDeployerSpeed(double speed) {
        deployer.set(speed);
    }

    public void stopDeployer() {
        setDeployerSpeed(0);
    }

    public void deployIntake() {
        // if (!deployState.getAsBoolean()) {

        //     while (intakeAngle > deployedAngle) {
        //         setDeployerSpeed(feedforward.calculate(intakeAngle, -1, 1));
        //     }
        // }
        new Thread(() ->{

        if(!deployState.getAsBoolean()){
            System.out.println("Deploy");
            Timer time = new Timer();
            time.start();
            while(time.get() < 3){
                setDeployerSpeed(0.18);
            }
            stopDeployer();
            time.stop();
            time = null;
        }}).start();

    }

    public void retractIntake() {
        // if (deployState.getAsBoolean()) {
        //     while (intakeAngle < retractedAngle) {
        //         setDeployerSpeed(feedforward.calculate(intakeAngle, 1, 1));
        //     }
        // }

        //if(deployState.getAsBoolean()){
        new Thread(() ->{

            Timer time = new Timer();
            time.start();
            while(time.get() < 3){
                setDeployerSpeed(-0.18);
            }
            stopDeployer();
            time.stop();
            time = null;
        }).start();
    }

    public void enableDeployer() {
        //deployer.set(deployerSpeed.getDouble(0));
    }

}
