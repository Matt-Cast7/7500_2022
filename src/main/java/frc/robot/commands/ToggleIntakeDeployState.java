package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class ToggleIntakeDeployState extends CommandBase{

    private Intake m_intake;

    public ToggleIntakeDeployState(Intake m_intake){
        this.m_intake = m_intake;
    }

    @Override
    public void execute(){
        m_intake.toggleIntakeDeployState();
    }

    @Override
    public void end(boolean interrupted){
        m_intake.setDeployerSpeed(0);
    }

    @Override
    public boolean isFinished(){
        return true;
    }
    
}
