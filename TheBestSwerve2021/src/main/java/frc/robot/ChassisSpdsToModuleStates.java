package frc.robot;

import edu.wpi.first.wpilibj.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.kinematics.SwerveModuleState;

public class ChassisSpdsToModuleStates {

    public ChassisSpdsToModuleStates() {
        // TODO - set up and requried WPILib classes for doing translation from chassis
        // to module states
    }

    /** Inputs */

    public void setSpeedCmds(ChassisSpeeds spdCmd) {
        // TODO - store most recent speed command
    }

    /** Calcualtion */

    public void update() {
        // TODO - perform periodic calculations
    }

    /** Outputs */

    public SwerveModuleState getFLModuleStateCmd() {
        return new SwerveModuleState(); // TODO - replace with something more meaningful
    }

    public SwerveModuleState getFRModuleStateCmd() {
        return new SwerveModuleState(); // TODO - replace with something more meaningful
    }

    public SwerveModuleState getBLModuleStateCmd() {
        return new SwerveModuleState(); // TODO - replace with something more meaningful
    }

    public SwerveModuleState getBRModuleStateCmd() {
        return new SwerveModuleState(); // TODO - replace with something more meaningful
    }
}
