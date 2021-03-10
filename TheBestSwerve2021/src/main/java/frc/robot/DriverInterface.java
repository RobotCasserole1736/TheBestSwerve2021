package frc.robot;

public class DriverInterface {

    // TODO: Instantiate new xbox controller

    public DriverInterface() {
        // Constructure - fill out here as needed
    }

    /** Inputs */
    // No one here but us chickens

    /** Calcualtion */
    public void update() {
        // TODO: read joystick and buttons values
        // TODO: multipy by proper ratio to transform joystick +1/-1 range into
        // speed/rotation commands
        // use constants for the robot's maximum rotational/translational speed
    }

    /** Outputs */
    public double getFwdRevSpdCmd_mps() {
        return 0; // TODO
    }

    public double getRotationSpdCmd_radPerSec() {
        return 0; // TODO
    }

    public double getStrafeSpdCmd_mps() {
        return 0; // TODO
    }

}
