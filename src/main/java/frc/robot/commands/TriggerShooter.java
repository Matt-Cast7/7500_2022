package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterTest;

public class TriggerShooter extends CommandBase{
    private ShooterTest shooter;

    public TriggerShooter(ShooterTest shooter){
        this.shooter = shooter;
    }

    @Override
    public void execute(){
        shooter.toggle();
    }

    public void end(boolean interrupted) {
        shooter.set(0);
    }


  public boolean isFinished() {
    return true;
  }
    
}
