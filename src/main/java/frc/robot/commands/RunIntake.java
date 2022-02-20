
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class RunIntake extends CommandBase{

    private Intake m_intake;

    public RunIntake(Intake m_intake){
        this.m_intake = m_intake;
        addRequirements(m_intake);
    }

    @Override
    public void execute(){
        m_intake.enableIntake();
    }

    @Override
    public void end(boolean interuppted){
        m_intake.stopIntake();
    }

    @Override
    public boolean isFinished(){
        return false;
    }
    
}
