package frc.robot.commands;

import java.util.Map;

import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class OpenIntake extends CommandBase{

    private Intake m_intake;

    

    public OpenIntake(Intake m_intake){
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
