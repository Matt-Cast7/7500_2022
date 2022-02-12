package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class DeployIntake extends CommandBase{

    private Intake m_intake;

    

    public DeployIntake(Intake m_intake){
        this.m_intake = m_intake;

    }

    public void execute(){
        m_intake.deployIntake();
    }

    public void end(boolean interrupted){
        m_intake.setDeployerSpeed(0);
    }

    public boolean isFinished(){
        return false;
    }
    
}
