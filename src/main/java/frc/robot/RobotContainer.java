// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import org.ejml.equation.IntegerSequence.Range;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.CloseIntake;
import frc.robot.commands.TankDrive;
import frc.robot.commands.TestMotorBackward;
import frc.robot.commands.OpenIntake;
import frc.robot.commands.TestMotorForward;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.ShooterTest;
import frc.robot.subsystems.SparkMaxMotor;

public class RobotContainer {

  DriveTrain m_DriveTrain;
  ShooterTest shooter;

  SparkMaxMotor m_motor;

  Intake m_intake;


  Command m_TankDrive;
  Command m_ArcadeDrive;

  Command testShooter;

  Command testMotorForward;
  Command testMotorBackward;


  Command openIntake;
  Command closeIntake;

 
  Joystick m_leftJoystick;
  Joystick m_rightJoystick;
  Joystick buttonBox;

  Joystick buttonbox;

  JoystickButton Ltrigger;
  JoystickButton Rtrigger;

  JoystickButton toggle;
  JoystickButton green;
  JoystickButton red;
  JoystickButton black;
  JoystickButton yellow;
  JoystickButton blue;


  
  public RobotContainer() {
    
    m_leftJoystick = new Joystick(0);
    m_rightJoystick = new Joystick(1);
    buttonBox = new Joystick(2);
    

    m_DriveTrain = new DriveTrain();

    m_motor = new SparkMaxMotor();

    shooter = new ShooterTest();

    m_intake = new Intake();
    

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

  public void testMotor(){
    testMotorForward = new TestMotorForward(m_motor);
    testMotorBackward = new TestMotorBackward(m_motor);

    toggle.and(green).whileActiveContinuous(testMotorForward);
    toggle.negate().and(green).whileActiveContinuous(testMotorBackward);

  }

  public void testIntake(){
    openIntake = new OpenIntake(m_intake);
    closeIntake = new CloseIntake(m_intake);

    
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
