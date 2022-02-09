package frc.robot;

import java.util.List;

import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Index;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

public class SystemsUpdater {
    
    Index index;
    Intake intake;
    Shooter shooter;
    DriveTrain driveTrain;

    public SystemsUpdater(Index index, Intake intake, Shooter shooter, DriveTrain driveTrain){
        this.index = index;
        this.intake = intake;
        this.shooter = shooter;
        this.driveTrain = driveTrain;
    }

    public void update(){
        //index.update();
        //intake.update();
        shooter.update();
    }
    
    
}
