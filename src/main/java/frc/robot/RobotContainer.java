// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.RetractIntake;
import frc.robot.commands.RunDeployer;
import frc.robot.commands.RunIntake;
import frc.robot.commands.TankDrive;

import frc.robot.commands.DeployIntake;
import frc.robot.commands.RunShooter;

import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Index;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;


public class RobotContainer {

  private DriveTrain m_DriveTrain;

  private Intake m_intake;
  private Index m_index;
  private Shooter m_shooter;


  
  SystemsUpdater updater;

  private Command m_TankDrive;
  private Command m_ArcadeDrive;

  private Command deployIntake;
  private Command retractIntake;
  private Command runIntake;
  private Command runShooter;

  private Command runDeployer;
  private Command runintake;
  

  private PowerDistribution pd;

 
  private Joystick m_leftJoystick;
  private Joystick m_rightJoystick;
  private Joystick buttonBox;

  private Joystick buttonbox;

  private JoystickButton Ltrigger;
  private JoystickButton Rtrigger;

  private JoystickButton toggle;
  private JoystickButton green;
  private JoystickButton red;
  private JoystickButton black;
  private JoystickButton yellow;
  private JoystickButton blue;

  
  public RobotContainer() {

    pd = new PowerDistribution();

    Shuffleboard.getTab("TeleOp").addNumber("Total Current", () -> pd.getTotalCurrent());
    Shuffleboard.getTab("TeleOp").addNumber("PDH Temp", () -> pd.getTemperature());  

    
    
    m_leftJoystick = new Joystick(0);
    m_rightJoystick = new Joystick(1);
    buttonBox = new Joystick(2);
    

    //m_DriveTrain = new DriveTrain();

    m_intake = new Intake();
    //m_index = new Index();
    //m_shooter = new Shooter();

    //m_motor = new SparkMaxMotor();

    updater = new SystemsUpdater(m_index, m_intake, m_shooter, m_DriveTrain);
    configureButtons();
  }

  public void configureButtons(){
    toggle = new JoystickButton(buttonBox, 1);
    green = new JoystickButton(buttonBox, 2);
    red = new JoystickButton(buttonBox, 3);
    black = new JoystickButton(buttonBox, 4);
    yellow = new JoystickButton(buttonBox, 5);
    blue = new JoystickButton(buttonBox, 6);

    Ltrigger = new JoystickButton(m_leftJoystick, 1);
    Rtrigger = new JoystickButton(m_rightJoystick, 1);


  }

  public void runShooter(){
    runShooter = new RunShooter(m_shooter);
    toggle.whileHeld(runShooter);
  }

  public void runDeployer(){
    runDeployer = new RunDeployer(m_intake);
    toggle.whileHeld(runDeployer);
  }

  public void runIntake(){
    runIntake = new RunIntake(m_intake);
    toggle.whileHeld(runIntake);
  }


  public Command getTankDrive(){
    m_TankDrive = new TankDrive(m_DriveTrain,
    () -> getLeftYAdjusted(), 
    () -> getRightYAdjusted());

    return m_TankDrive;
  }

  public Command getArcadeDrive(){
    m_ArcadeDrive = new ArcadeDrive(m_DriveTrain, 
    () -> getLeftYAdjusted(), 
    () -> getRightX());
    return m_ArcadeDrive;


  }









  public double getLeftYAdjusted(){
    return -getLeftY()*getSensitvity();
  }

  public double getRightYAdjusted(){
    return -getRightY()*getSensitvity();
  }


  public double getLeftY(){
    double val = m_leftJoystick.getY();
    if(Math.abs(val) <= Constants.ControllerDeadzone){
      return 0;
    }else{
      return val;
    }
  }

  public double getRightY(){
      double val = m_rightJoystick.getY();
      if(Math.abs(val) <= Constants.ControllerDeadzone){
        return 0;
      }else{
        return val;
      }
  }

  public double getRightX(){
    double val = m_rightJoystick.getX();
    if(Math.abs(val) <= Constants.ControllerDeadzone){
      return 0;
    }else{
      return val;
    }
}

  public double getSensitvity(){
    return MathUtil.clamp((((-m_leftJoystick.getThrottle())+1)/2) + 0.1, 0.1, 1);
  }


}
