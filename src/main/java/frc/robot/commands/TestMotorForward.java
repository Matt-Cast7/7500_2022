package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.SparkMaxMotor;

public class TestMotorForward extends CommandBase{
    
    private SparkMaxMotor m_motor;

    public TestMotorForward(SparkMaxMotor m){
        m_motor = m;
    }


    public void execute(){
       // m_motor.set(Constants.testMotorPower);
    }



    public void end(boolean interrupted){
        m_motor.set(0);
    }

    public boolean isFinished() {
        return false;
    }

}
