package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Index;
import frc.robot.subsystems.Shooter;

public class FireBall extends CommandBase{

    private Index m_index;

    public FireBall(Index m_index){
        this.m_index = m_index;

    }


    public void initialize(){
        
    }

    public void execute(){
        m_index.setIndex(0.5);
    }

    public boolean isFinished(){
        return false;
    }

    public void end(boolean interrupted){
        m_index.stop();
    }
    
}
