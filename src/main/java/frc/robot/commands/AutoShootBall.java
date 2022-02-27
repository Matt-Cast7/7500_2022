package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Index;
import frc.robot.subsystems.Shooter;

public class AutoShootBall extends CommandBase{

    private Shooter m_Shooter;
    private Index m_Index;

    public AutoShootBall(Shooter m_Shooter, Index m_Index) {
        this.m_Shooter = m_Shooter;
        this.m_Index = m_Index;
    }

    @Override
    public void execute() {
        m_Shooter.enable();
        m_Index.fireBall();
    }

    @Override
    public void end(boolean interrupted) {
        m_Shooter.stop();
        m_Index.stop();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
    
}
