package frc.robot.commands;

import java.util.concurrent.CountDownLatch;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Index;
import frc.robot.subsystems.Shooter;

public class PrimeShooter extends CommandBase{

    private Shooter m_shooter;
    private Index m_index;

    public PrimeShooter(Shooter m_shooter, Index m_index){
        this.m_shooter = m_shooter;
        this.m_index = m_index;

    }
    public void initialize(){
        
    }

    
    public void execute(){
        m_shooter.enablePID();
    }


    public boolean isFinished(){
        return false;
    }

    public void end(boolean interrupted){
        m_shooter.setShooterSpeed(0);
    }
    

    
    
}
