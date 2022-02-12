package frc.robot.subsystems;

import java.util.Map;
import java.util.function.BooleanSupplier;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.ArmFeedforward;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {

    private final CANSparkMax deployer = new CANSparkMax(Constants.Deployer, MotorType.kBrushed);

    private final CANSparkMax intake = new CANSparkMax(Constants.Intake, MotorType.kBrushless);

    private final Encoder encoder = new Encoder(Constants.DeployerEncoder[0], Constants.DeployerEncoder[1], false);

    private DigitalInput intakeLimitSwitch = new DigitalInput(Constants.DeployerLimitSwitch);

    private ArmFeedforward feedforward = new ArmFeedforward(0.2 * 12, 0, 0, 0);

    private BooleanSupplier deployState;

    private boolean flipIntake = false;
    private boolean flipDeployer = false;

    private double intakeAngle;

    private double retractedAngle = 140;
    private double deployedAngle = 87;

    NetworkTableEntry intakeSpeed = Shuffleboard.getTab("TeleOp")
            .addPersistent("Intake Speed", 0)
            .withWidget(BuiltInWidgets.kNumberSlider)
            .withProperties(Map.of("min", -1, "max", 1))
            .getEntry();

    public Intake() {

        intake.setInverted(flipIntake);
        deployer.setInverted(flipDeployer);

        deployer.setIdleMode(IdleMode.kCoast);

        Shuffleboard.getTab("TeleOp")
                .addBoolean("Deploy Status", deployState);

        deployState = () -> intakeLimitSwitch.get();

        intakeAngle = (deployState.getAsBoolean()) ? deployedAngle : retractedAngle;

        encoder.reset();

    }

    public void enableIntake() {
        intake.set(intakeSpeed.getDouble(0));
    }

    public void setIntake(double speed) {
        intakeSpeed.setDouble(speed);
        intake.set(speed);
    }

    public void stopIntake() {
        intakeSpeed.setDouble(0);
        intake.set(0);

    }

    public void setDeployerSpeed(double speed) {
        deployer.set(speed);
    }

    public void deployIntake() {
        if (!deployState.getAsBoolean()) {

            while (intakeAngle > deployedAngle) {
                setDeployerSpeed(feedforward.calculate(intakeAngle, -1, 1));
            }
        }
    }

    public void retractIntake() {
        if (deployState.getAsBoolean()) {
            while (intakeAngle < retractedAngle) {
                setDeployerSpeed(feedforward.calculate(intakeAngle, 1, 1));
            }
        }
    }



    public void periodic() {
        intakeAngle += (encoder.get() * 0.0439);// scale the encoding values somehow to make it equal to angle

    }

    public void update() {
        intake.setVoltage(MathUtil.clamp(intakeSpeed.getDouble(0), 0, 12));

    }

}
