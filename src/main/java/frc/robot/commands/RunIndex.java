package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Index;

public class RunIndex extends CommandBase{

    private Index m_index;

    public RunIndex(Index m_index){
        this.m_index = m_index;
    }

    public void execute(){
        m_index.enableIndex();
    }

    public boolean isFinished(){
        return false;
    }

    public void end(boolean interrupted){
        m_index.stop();
    }
    
}
