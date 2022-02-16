
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class RunDeployer extends CommandBase{

    private Intake m_intake;

    public RunDeployer(Intake m_intake){
        this.m_intake = m_intake;
    }
    
    public void initialize(){
        m_intake.setDeployerSpeed(0);
    }

    @Override
    public void execute(){
        //m_intake.setDeployerSpeed(0);
        m_intake.enableDeployer();
    }

    @Override
    public void end(boolean interuppted){
        m_intake.setDeployerSpeed(0);
    }

    @Override
    public boolean isFinished(){
        return false;
    }
    
}
