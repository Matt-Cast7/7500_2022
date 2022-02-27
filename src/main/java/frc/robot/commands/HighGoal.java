package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class HighGoal extends CommandBase{
    
    private Shooter m_shooter;

    public HighGoal(Shooter m_shooter){
        this.m_shooter = m_shooter;
    }

    public void execute(){
        m_shooter.setHighGoal();
    }

    public void end(boolean interrupted){
        
    }

    public boolean isFinished(){
        return true;
    }
}
