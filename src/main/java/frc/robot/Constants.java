// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    
    public static final int Motor_Left_Back = 3;//2
    public static final int Motor_Left_Front = 4;//1
    public static final int Motor_Right_Back = 5;//13
    public static final int Motor_Right_Front = 6;//12

    public static final int Deployer = 12;
    public static final int Intake = 11;

    public static final int[] DeployerEncoder = {0, 1};
    public static final int DeployerLimitSwitch = 0;

    public static final int[] Indexer = {9, 10};

    public static final int[] Shooter = {11, 12};

    //public static final int testMotor = 14;

    
    public static final double ControllerDeadzone = 0.05;

    public static final double shooterGearing = 32.0/30.0;

    public static final double indexGearing = 1;
    public static final double indexWheel = 1;
    
    public static final double deployerGearing = 1.0/100.0 * 21.0/36.0;



    public static final double currentLimit = 180;

    



}
