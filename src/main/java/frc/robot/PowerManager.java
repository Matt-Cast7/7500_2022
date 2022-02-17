package frc.robot;

public class PowerManager {
    
    protected static double driveMulti;

    protected static double intakeMulti;

    protected static double indexMulti;

    protected static double shooterMulti;

    public enum Mode{
        kSpeed,
        kUtilities,
        kBalanced,
        kBrownBot,
    }

    protected static Mode currentMode;
    

    public PowerManager(){
        driveMulti = 1;

        intakeMulti = 1;

        indexMulti = 1;

        shooterMulti = 1;

        currentMode = Mode.kBrownBot;
    }

    public void setMode(Mode mode){
        switch(mode){
            case kSpeed:
                speedMode();
            case kUtilities:
                utilitiesMode();
            case kBalanced:
                balancedMode();
            case kBrownBot:
                brownMode();
        }
    
    }


    private void speedMode(){

    }

    private void utilitiesMode(){

    }

    private void balancedMode(){

    }

    private void brownMode(){

    }


}
