// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;


public class Robot extends TimedRobot {
  private Command tankDrive;
  private Command arcadeDrive;

  private RobotContainer m_robotContainer;

  private Camera camera;


  @Override
  public void robotInit() {
    m_robotContainer = new RobotContainer();

    camera.init();
    camera.start();
    
  }



  @Override
  public void robotPeriodic() {
    
    

    CommandScheduler.getInstance().run();
  }

//Matg
  
  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  
  @Override
  public void autonomousInit() {

    Shuffleboard.selectTab("Auto");


  }

  
  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
    CommandScheduler.getInstance().cancelAll();
    Shuffleboard.selectTab("TeleOp");

    //tankDrive = m_robotContainer.getTankDrive();
    //arcadeDrive = m_robotContainer.getArcadeDrive();
    //tankDrive.schedule();

   // m_robotContainer.testIntake();

   m_robotContainer.runDeployer();
  }

  
  @Override
  public void teleopPeriodic() {
    m_robotContainer.updater.update();
  }

  @Override
  public void testInit() {
    
    CommandScheduler.getInstance().cancelAll();

    // arcadeDrive = m_robotContainer.getArcadeDrive();
    // arcadeDrive.schedule();
  }

  
  @Override
  public void testPeriodic() {}
}
