package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class CloseIntake extends CommandBase{

    private Intake m_intake;

    public CloseIntake(Intake m_intake){
        this.m_intake = m_intake;

    }

    public void execute(){
        

    }

    public void end(boolean interrupted){
        
    }

    public boolean isFinished(){
        return false;
    }
    
}
