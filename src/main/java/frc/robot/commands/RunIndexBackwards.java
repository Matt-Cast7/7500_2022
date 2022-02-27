package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Index;
import frc.robot.subsystems.Shooter;

public class RunIndexBackwards extends CommandBase{

    Index m_index;
    Shooter m_shooter;
    
    public RunIndexBackwards(Index m_index, Shooter m_shooter){
        this.m_index = m_index;
    }

    @Override
    public void execute() {
        m_index.runBackwards();
       // m_shooter.runBackwards();
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        m_index.stop();
        //m_shooter.stop();
    }


    
}
