package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.PowerManager;
import frc.robot.PowerManager.Mode;

public class SetUtilityMode extends CommandBase{
    
    PowerManager p;

    public SetUtilityMode(){
        p = new PowerManager();
    }
    public void execute(){
        p.setMode(Mode.kUtilities);
    }
    
    public boolean isFinished(){
        return true;
    }
}
