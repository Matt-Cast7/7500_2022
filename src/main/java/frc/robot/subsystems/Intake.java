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


    private final CANSparkMax intake = new CANSparkMax(Constants.Intake, MotorType.kBrushless);

    


    private boolean flipIntake = false;


    NetworkTableEntry intakeSpeed = Shuffleboard.getTab("TeleOp")
            .addPersistent("Intake Speed", 0)
            .withWidget(BuiltInWidgets.kNumberSlider)
            .withProperties(Map.of("min", -1, "max", 1))
            .getEntry();

            

    public Intake() {

        intake.setInverted(flipIntake);
        



    }

    public void enableIntake() {
        //intake.set(intakeSpeed.getDouble(0));
        intake.set(intakeSpeed.getDouble(0));
    }

    public void setIntake(double speed) {
        //intakeSpeed.setDouble(speed);
        intake.set(speed);
    }

    public void stopIntake() {
        setIntake(0);

    }




    public void periodic() {
        //intakeAngle += (encoder.get() * 0.0439);// scale the encoding values somehow to make it equal to angle

    }

}
