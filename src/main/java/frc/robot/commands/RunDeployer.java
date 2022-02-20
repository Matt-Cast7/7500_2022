
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Deployer;
import frc.robot.subsystems.Intake;

public class RunDeployer extends CommandBase{

    private Deployer m_deployer;

    public RunDeployer(Deployer m_deployer){
        this.m_deployer = m_deployer;
    }
    
    public void initialize(){
    }

    @Override
    public void execute(){
        m_deployer.enableDeployer();
    }

    @Override
    public void end(boolean interuppted){
        m_deployer.stopDeployer();
    }

    @Override
    public boolean isFinished(){
        return false;
    }
    
}
