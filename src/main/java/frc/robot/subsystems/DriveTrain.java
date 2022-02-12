package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase{


    private final CANSparkMax leftMaster = new CANSparkMax(Constants.Motor_Left_Back, MotorType.kBrushless);
    private final CANSparkMax leftSlave = new CANSparkMax(Constants.Motor_Left_Front, MotorType.kBrushless);
    private final CANSparkMax rightMaster = new CANSparkMax(Constants.Motor_Right_Back, MotorType.kBrushless);
    private final CANSparkMax rightSlave = new CANSparkMax(Constants.Motor_Right_Front, MotorType.kBrushless);
    
    boolean lFlip = false;
    boolean rFlip = true;

    

    public DriveTrain(){
        leftSlave.follow(leftMaster);
        rightSlave.follow(rightMaster);

        leftMaster.setInverted(lFlip);
        rightMaster.setInverted(rFlip);

        leftMaster.setIdleMode(IdleMode.kCoast);
        leftSlave.setIdleMode(IdleMode.kCoast);
        rightMaster.setIdleMode(IdleMode.kCoast);
        rightSlave.setIdleMode(IdleMode.kCoast);

        
    }


    public void periodic(){
        
    }


    public void set(double speed){
        leftMaster.set(speed);
        rightMaster.set(speed);
    }

    public void set(double lSpeed, double rSpeed){
        leftMaster.set(lSpeed);
        rightMaster.set(rSpeed);
    }

}
