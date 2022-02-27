package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class MoveFromTarmac extends CommandBase{

    private DriveTrain m_DriveTrain;

    public MoveFromTarmac(DriveTrain m_DriveTrain){
        this.m_DriveTrain = m_DriveTrain;

    }

    public void execute(){
        m_DriveTrain.driveDistance(-48, 0.25);
    }

    public boolean isFinished(){
        return true;
    }

    public void end(boolean interrupted){
        m_DriveTrain.set(0);
    }
    
}
