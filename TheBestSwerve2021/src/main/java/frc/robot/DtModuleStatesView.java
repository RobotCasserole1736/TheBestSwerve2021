package frc.robot;

import edu.wpi.first.wpilibj.kinematics.SwerveModuleState;
import frc.UnitUtils;
import frc.lib.DataServer.Signal;

class DtModuleStatesView {

    Signal wheelSpdDesSigFL;
    Signal wheelSpdActSigFL;
    Signal azmthPosDesSigFL;
    Signal azmthPosActSigFL;
    Signal wheelSpdDesSigFR;
    Signal wheelSpdActSigFR;
    Signal azmthPosDesSigFR;
    Signal azmthPosActSigFR;
    Signal wheelSpdDesSigBL;
    Signal wheelSpdActSigBL;
    Signal azmthPosDesSigBL;
    Signal azmthPosActSigBL;
    Signal wheelSpdDesSigBR;
    Signal wheelSpdActSigBR;
    Signal azmthPosDesSigBR;
    Signal azmthPosActSigBR;

    SwerveModuleState FLAct;
    SwerveModuleState FRAct;
    SwerveModuleState BLAct;
    SwerveModuleState BRAct;
    SwerveModuleState FLDes;
    SwerveModuleState FRDes;
    SwerveModuleState BLDes;
    SwerveModuleState BRDes;

    public DtModuleStatesView(){
        FLAct = new SwerveModuleState();
        FRAct = new SwerveModuleState();
        BLAct = new SwerveModuleState();
        BRAct = new SwerveModuleState();
        FLDes = new SwerveModuleState();
        FRDes = new SwerveModuleState();
        BLDes = new SwerveModuleState();
        BRDes = new SwerveModuleState();

        wheelSpdDesSigFL = new Signal("DtModuleFLWheelSpdDes", "RPM");
        wheelSpdActSigFL = new Signal("DtModuleFLWheelSpdAct", "RPM");
        azmthPosDesSigFL = new Signal("DtModuleFLAzmthPosDes", "deg");
        azmthPosActSigFL = new Signal("DtModuleFLAzmthPosAct", "deg");
        wheelSpdDesSigFR = new Signal("DtModuleFRWheelSpdDes", "RPM");
        wheelSpdActSigFR = new Signal("DtModuleFRWheelSpdAct", "RPM");
        azmthPosDesSigFR = new Signal("DtModuleFRAzmthPosDes", "deg");
        azmthPosActSigFR = new Signal("DtModuleFRAzmthPosAct", "deg");
        wheelSpdDesSigBL = new Signal("DtModuleBLWheelSpdDes", "RPM");
        wheelSpdActSigBL = new Signal("DtModuleBLWheelSpdAct", "RPM");
        azmthPosDesSigBL = new Signal("DtModuleBLAzmthPosDes", "deg");
        azmthPosActSigBL = new Signal("DtModuleBLAzmthPosAct", "deg");
        wheelSpdDesSigBR = new Signal("DtModuleBRWheelSpdDes", "RPM");
        wheelSpdActSigBR = new Signal("DtModuleBRWheelSpdAct", "RPM");
        azmthPosDesSigBR = new Signal("DtModuleBRAzmthPosDes", "deg");
        azmthPosActSigBR = new Signal("DtModuleBRAzmthPosAct", "deg");
    }

    public void setActualStates(SwerveModuleState FLAct_in, SwerveModuleState FRAct_in, SwerveModuleState BLAct_in, SwerveModuleState BRAct_in){
        FLAct = FLAct_in;
        FRAct = FRAct_in;
        BLAct = BLAct_in;
        BRAct = BRAct_in;
    }

    public void setDesiredStates(SwerveModuleState FLDes_in, SwerveModuleState FRDes_in, SwerveModuleState BLDes_in, SwerveModuleState BRDes_in){
        FLDes = FLDes_in;
        FRDes = FRDes_in;
        BLDes = BLDes_in;
        BRDes = BRDes_in;
    }

    public void update(double sampleTimeMs){
        wheelSpdActSigFL.addSample(sampleTimeMs, UnitUtils.DtMPerSectoRPM(FLAct.speedMetersPerSecond));
        wheelSpdActSigFR.addSample(sampleTimeMs, UnitUtils.DtMPerSectoRPM(FRAct.speedMetersPerSecond));
        wheelSpdActSigBL.addSample(sampleTimeMs, UnitUtils.DtMPerSectoRPM(BLAct.speedMetersPerSecond));
        wheelSpdActSigBR.addSample(sampleTimeMs, UnitUtils.DtMPerSectoRPM(BRAct.speedMetersPerSecond));

        azmthPosActSigFL.addSample(sampleTimeMs, FLAct.angle.getDegrees());
        azmthPosActSigFR.addSample(sampleTimeMs, FRAct.angle.getDegrees());
        azmthPosActSigBL.addSample(sampleTimeMs, BLAct.angle.getDegrees());
        azmthPosActSigBR.addSample(sampleTimeMs, BRAct.angle.getDegrees());

        wheelSpdDesSigFL.addSample(sampleTimeMs, UnitUtils.DtMPerSectoRPM(FLDes.speedMetersPerSecond));
        wheelSpdDesSigFR.addSample(sampleTimeMs, UnitUtils.DtMPerSectoRPM(FRDes.speedMetersPerSecond));
        wheelSpdDesSigBL.addSample(sampleTimeMs, UnitUtils.DtMPerSectoRPM(BLDes.speedMetersPerSecond));
        wheelSpdDesSigBR.addSample(sampleTimeMs, UnitUtils.DtMPerSectoRPM(BRDes.speedMetersPerSecond));

        azmthPosDesSigFL.addSample(sampleTimeMs, FLDes.angle.getDegrees());
        azmthPosDesSigFR.addSample(sampleTimeMs, FRDes.angle.getDegrees());
        azmthPosDesSigBL.addSample(sampleTimeMs, BLDes.angle.getDegrees());
        azmthPosDesSigBR.addSample(sampleTimeMs, BRDes.angle.getDegrees());

    }


}