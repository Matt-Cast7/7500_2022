package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.PowerManager;
import frc.robot.PowerManager.Mode;

public class SetBrownBot extends CommandBase{
    PowerManager p;

    public SetBrownBot(){
        p = new PowerManager();
    }
    public void execute(){
        p.setMode(Mode.kBrownBot);
    }
    
    public boolean isFinished(){
        return true;
    }
    
}
