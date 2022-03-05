package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Index;

public class InitIndexing extends CommandBase {

    private Index m_index;

    public InitIndexing(Index m_index) {
        this.m_index = m_index;
    }

    public void initialize(){
        m_index.initFiring();
    }

    public void execute() {
    }

    public boolean isFinished(){
        return m_index.isFinihsedIniting();
    }

    public void end(boolean interrupted){
        m_index.stop();
        m_index.finishedInitingIndex();
    }

}
