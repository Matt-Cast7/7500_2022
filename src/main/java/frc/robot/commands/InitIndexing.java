package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Index;

public class InitIndexing extends CommandBase {

    private Index m_index;

    public InitIndexing(Index m_index) {
        this.m_index = m_index;
    }

    public void enable() {
        m_index.initFiring();
    }

    public boolean isFinihsed(){
        return true;
    }

    public void end(boolean interrupted){
        m_index.stop();
    }

}
