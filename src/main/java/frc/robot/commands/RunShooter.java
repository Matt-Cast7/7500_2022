package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class RunShooter extends CommandBase{

    Shooter m_shooter;

    public RunShooter(Shooter m_shooter){
        this.m_shooter = m_shooter;
    }

    public void initialize(){
        
        
        //m_shooter.setShooterSpeed(0.1);
        m_shooter.resetPID();
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
