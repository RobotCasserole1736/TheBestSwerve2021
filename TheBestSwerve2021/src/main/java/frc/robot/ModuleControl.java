package frc.robot;

import edu.wpi.first.wpilibj.kinematics.SwerveModuleState;

public class ModuleControl {

    // TODO - declare motor controllers for _one_ module
    // TODO - declare encoders for _one_ module

    public ModuleControl() {
        // TODO - what configuration needs to be passed as input to this single module
        // control object?
    }

    /** Inputs */

    public void setModuleStateCmd(SwerveModuleState desiredState) {

    }

    /** Calcualtion */

    public void update() {

        // TODO - PID controllers and stuff

        // TODO - send motor commands to the actual motors

    }

    /** Outputs */

    public SwerveModuleState getActualState() {
        return new SwerveModuleState(); // TODO - return something more meaningful from sensor values
    }

}
