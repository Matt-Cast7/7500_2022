package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class RetractIntake extends CommandBase{

    private Intake m_intake;

    public RetractIntake(Intake m_intake){
        this.m_intake = m_intake;

    }

    public void execute(){
        m_intake.retractIntake();
    }

    public void end(boolean interrupted){
        m_intake.setDeployerSpeed(0);
    }

    public boolean isFinished(){
        return false;
    }
    
}
