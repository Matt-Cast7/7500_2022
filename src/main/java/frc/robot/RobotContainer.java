// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.Joystick.ButtonType;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.*;

// import frc.robot.commands.ArcadeDrive;
// import frc.robot.commands.AutoShootBall;
// import frc.robot.commands.RetractIntake;
// import frc.robot.commands.RunDeployer;
// import frc.robot.commands.RunIndex;
// import frc.robot.commands.AutoRunIndex;
// import frc.robot.commands.RunIndexBackwards;
// import frc.robot.commands.RunIntake;
// import frc.robot.commands.TankDrive;
// import frc.robot.commands.ToggleDeployer;
// import frc.robot.commands.DeployIntake;
// import frc.robot.commands.FireBall;
// import frc.robot.commands.HighGoal;
// import frc.robot.commands.InitIndexing;
// import frc.robot.commands.LowGoal;
// import frc.robot.commands.MoveFromTarmac;
// import frc.robot.commands.PrimeShooter;
// import frc.robot.commands.RunShooter;
import frc.robot.subsystems.Deployer;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Index;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;


public class RobotContainer {

  private DriveTrain m_DriveTrain;

  private Intake m_intake;
  private Index m_index;
  private Shooter m_shooter;
  private Deployer m_deployer;


  
  SystemsUpdater updater;

  private Command m_TankDrive;
  private Command m_ArcadeDrive;

  private Command deployIntake;
  private Command retractIntake;


  private Command runIndex;

  private Command autoRunIndex;
  private Command runIntake;
  private Command runShooter;

  private Command runDeployer;

  private Command primeShooter;

  private Command fireBall;

  private Command initIndexing;

  public Command autoShootBall;
  public Command moveFromTarmac;

  public Command runIndexBackwards;

  public Command highGoal;
  public Command lowGoal;

  public Command toggleDeployer;

  
  
  

  private PowerDistribution pd;

 
  private Joystick m_leftJoystick;
  private Joystick m_rightJoystick;
  private Joystick buttonBox;

  private Joystick buttonbox;

  private JoystickButton Ltrigger;
  private JoystickButton Rtrigger;

  private JoystickButton leftHat;
  private JoystickButton rightHat;
  private JoystickButton topHat;
  private JoystickButton bottomHat;

  private JoystickButton toggle;
  private JoystickButton green;
  private JoystickButton red;
  private JoystickButton black;
  private JoystickButton yellow;
  private JoystickButton blue;

  
  public RobotContainer() {

    TrackDistance t = new TrackDistance();
    pd = new PowerDistribution();
    
    
    m_leftJoystick = new Joystick(0);
    
    m_rightJoystick = new Joystick(1);
    buttonBox = new Joystick(2);
    

    m_DriveTrain = new DriveTrain();

    m_intake = new Intake();
    m_index = new Index();
    m_shooter = new Shooter();
    m_deployer = new Deployer();
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
    runDeployer = new RunDeployer(m_deployer);
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
    () -> getRightXAdjusted());
    return m_ArcadeDrive;


  }

  public SequentialCommandGroup autoCommand(){
    moveFromTarmac = new MoveFromTarmac(m_DriveTrain);
    deployIntake = new DeployIntake(m_deployer);
    autoShootBall = new AutoShootBall(m_shooter, m_index);

    return new SequentialCommandGroup(moveFromTarmac, deployIntake, autoShootBall);

  }


  public void testTeleOp(){

    Trigger switchManualMode = new Trigger();

    
    //getArcadeDrive().schedule();

    // runDeployer = new RunDeployer(m_deployer);

    // deployIntake = new DeployIntake(m_deployer);
    // retractIntake = new RetractIntake(m_deployer);
    
    
  
    // fireBall = new FireBall(m_index);

    // runIndexBackwards = new RunIndexBackwards(m_index, m_shooter);
 // runShooter = new RunShooter(m_shooter);
 
    runIntake = new RunIntake(m_intake);
    autoRunIndex = new AutoRunIndex(m_index);
    Rtrigger.whenHeld(runIntake).whenHeld(autoRunIndex);

    

    toggleDeployer = new ToggleDeployer(m_deployer);
    blue.whenPressed(toggleDeployer);



    primeShooter = new PrimeShooter(m_shooter, m_index);
    initIndexing = new InitIndexing(m_index);
    SequentialCommandGroup shooterInitin = new SequentialCommandGroup(initIndexing, primeShooter);
    green.toggleWhenPressed(shooterInitin);

    fireBall = new FireBall(m_index);
    red.whenPressed(fireBall);


    lowGoal = new LowGoal(m_shooter);
    highGoal = new HighGoal(m_shooter);
    toggle.whenHeld(highGoal).whenReleased(lowGoal);

    runIndex = new RunIndex(m_index);
    black.whenHeld(runIndex);

    //SequentialCommandGroup shooterIniting = new SequentialCommandGroup(initIndexing, primeShooter);
    // green.whenPressed(initIndexing);
    // red.whenPressed(primeShooter);
    // black.whenPressed(fireBall);

    //toggle.cancelWhenPressed(runIndex).whenHeld(primeShooter);

    //green.cancelWhenPressed(runIndex).toggleWhenPressed(primeShooter);

    // green.toggleWhenPressed(runShooter);

    // black.whileHeld(fireBall);

    
    // blue.whenPressed(deployIntake);
    // yellow.whenPressed(retractIntake);

    //red.whenHeld(runIndexBackwards);
    // red.cancelWhenPressed(fireBall);
    

    

  }







  public double getLeftYAdjusted(){
    return -getLeftY()*getSensitvity();
  }

  public double getRightYAdjusted(){
    return -getRightY()*getSensitvity();
  }


  public double getRightXAdjusted(){
    return getRightX()*getSensitvity();
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
