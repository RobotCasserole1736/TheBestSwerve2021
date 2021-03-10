package frc.robot;

import edu.wpi.first.wpilibj.kinematics.ChassisSpeeds;

public class Drivetrain {

    // Telemetry Utilities
    // keep track of the robot's actual, estimated, and desired position on the
    // field
    DtPoseView dtPoseView;
    DtModuleStatesView dtModStatesView;

    ChassisSpdsToModuleStates ch2mod;

    ModuleControl FLMod;
    ModuleControl FRMod;
    ModuleControl BLMod;
    ModuleControl BRMod;

    public Drivetrain() {
        dtPoseView = new DtPoseView();
        dtModStatesView = new DtModuleStatesView();

        ch2mod = new ChassisSpdsToModuleStates();

        FLMod = new ModuleControl(); // TODO - pass in additional configuration info to each module controller
        FRMod = new ModuleControl();
        BLMod = new ModuleControl();
        BRMod = new ModuleControl();
    }

    /** Inputs */

    public void setDesiredState(ChassisSpeeds desState_in) {
        ch2mod.setSpeedCmds(desState_in);
    }

    /** Calcualtion */

    public void update() {

        ch2mod.update();

        FLMod.setModuleStateCmd(ch2mod.getFLModuleStateCmd());
        FRMod.setModuleStateCmd(ch2mod.getFRModuleStateCmd());
        BLMod.setModuleStateCmd(ch2mod.getBLModuleStateCmd());
        BRMod.setModuleStateCmd(ch2mod.getBRModuleStateCmd());

        FLMod.update();
        FRMod.update();
        BLMod.update();
        BRMod.update();

        // TODO - uncomment these and pass meaningful information
        // to pass telemetry input to the dashboards
        // dtPoseView.setEstimatedPose(put something here);
        // dtPoseView.setDesiredPose(put something here);
        // dtModStatesView.setActualStates(put four things here);
        // dtModStatesView.setDesiredStates(put four things here);

    }

    /** Outputs */
    // no one here but us chickens.... so far
}
