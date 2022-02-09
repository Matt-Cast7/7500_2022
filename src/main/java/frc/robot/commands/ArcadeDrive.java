package frc.robot.commands;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class ArcadeDrive extends CommandBase{
    
    private DriveTrain m_DriveTrain;

    private DoubleSupplier m_leftPower;
    private DoubleSupplier m_rightPower;

    public ArcadeDrive(DriveTrain m_DriveTrain, DoubleSupplier m_leftPower, DoubleSupplier m_rightPower){
        this.m_DriveTrain = m_DriveTrain;
        this.m_leftPower = m_leftPower;
        this.m_rightPower = m_rightPower;
        addRequirements(m_DriveTrain);
    }

    @Override
    public void execute(){
        m_DriveTrain.set(m_leftPower.getAsDouble() + m_rightPower.getAsDouble(), m_leftPower.getAsDouble() - m_rightPower.getAsDouble());
    }

    @Override
    public void end(boolean interrupted){
        m_DriveTrain.set(0);
    }

    public boolean isFinished() {
        return false;
    }
    

}
