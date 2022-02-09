package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class RunShooter extends CommandBase{

    Shooter m_shooter;

    public RunShooter(Shooter m_shooter){
        this.m_shooter = m_shooter;
    }

    public void execute(){
        
    }
    
}
