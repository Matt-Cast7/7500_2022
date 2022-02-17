package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.PowerManager;
import frc.robot.PowerManager.Mode;

public class SetSpeedMode extends CommandBase{

    PowerManager p;

    public SetSpeedMode(){
        p = new PowerManager();
    }
    public void execute(){
        p.setMode(Mode.kSpeed);
    }
    
    public boolean isFinished(){
        return true;
    }
}
