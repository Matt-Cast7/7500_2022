package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj.motorcontrol.Victor;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterTest extends SubsystemBase{

    private PWMVictorSPX shooter = new PWMVictorSPX(16);

    public ShooterTest(){
    }

    public void set(double power){
        shooter.set(power);
    }

    public void toggle(){
        if(shooter.get() == 0){
            set(shooter.get() == 1 ? -1 : 1);
        }else{
            set(0);
        }
    }

    
}
