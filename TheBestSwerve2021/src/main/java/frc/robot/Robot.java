/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.kinematics.ChassisSpeeds;
import frc.lib.Calibration.CalWrangler;
import frc.lib.DataServer.CasseroleDataServer;
import frc.lib.DataServer.Annotations.Signal;
import frc.lib.WebServer.CasseroleWebServer;
import frc.sim.RobotModel;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  // Website utilities
  CasseroleWebServer webserver;
  CalWrangler wrangler;
  CasseroleDataServer dataServer;
  LoopTiming loopTiming;



  //Drivetrain Control
  Drivetrain dt;
  
  //Human/machine interfaces
  DriverInterface di;

  @Signal(units = "count")
  int loopCounter = 0;


  //////////////////////////////////////////////////////////////////////
  // Robot Initilization
  //////////////////////////////////////////////////////////////////////

  @Override
  public void robotInit() {
    /* Init website utilties */
    webserver = new CasseroleWebServer();
    wrangler = new CalWrangler();
    dataServer = CasseroleDataServer.getInstance();
    loopTiming = LoopTiming.getInstance();

    dt = new Drivetrain();
    di = new DriverInterface();

    if(isSimulation()){
      simModel = new RobotModel();
    }

    dataServer.registerSignals(this);
    dataServer.startServer();
    webserver.startServer();

  }

  //////////////////////////////////////////////////////////////////////
  // Autonomous Operation
  //////////////////////////////////////////////////////////////////////

  @Override
  public void autonomousInit() {
  }

  @Override
  public void autonomousPeriodic() {
    loopTiming.markLoopStart();

    //TODO: how might we calcualte desired chassis speeds in autonomous?
    ChassisSpeeds desChSpd = new ChassisSpeeds(0,0,0); //Worlds best auto routine - stay still.
    dt.setDesiredState(desChSpd); 
    dt.update();

    updateTelemetry();
    loopTiming.markLoopEnd();

  }

  //////////////////////////////////////////////////////////////////////
  // Teleop Operation
  //////////////////////////////////////////////////////////////////////

  @Override
  public void teleopInit() {
  }

  @Override
  public void teleopPeriodic() {
    loopTiming.markLoopStart();

    di.update();
    ChassisSpeeds desChSpd = new ChassisSpeeds(di.getFwdRevSpdCmd_mps(), di.getStrafeSpdCmd_mps(), di.getRotationSpdCmd_radPerSec());
    dt.setDesiredState(desChSpd);
    dt.update();

    updateTelemetry();
    loopTiming.markLoopEnd();

  }


  //////////////////////////////////////////////////////////////////////
  // Disabled Operation 
  //////////////////////////////////////////////////////////////////////

  @Override
  public void disabledInit() {

    if(isSimulation()){
      simModel.reset();
    }

  }

  @Override
  public void disabledPeriodic() {
    loopTiming.markLoopStart();

    updateTelemetry();
    loopTiming.markLoopEnd();
  }

  //////////////////////////////////////////////////////////////////////
  // Common Utility Functions for Robot
  //////////////////////////////////////////////////////////////////////

  private void updateTelemetry(){
    
    if(isSimulation()){
      dt.dtPoseView.setActualPose(simModel.getCurActPose());
    }

    double sampleTimeMs = LoopTiming.getInstance().getLoopStartTimeSec() * 1000;
    dt.dtPoseView.update(sampleTimeMs);
    dt.dtModStatesView.update(sampleTimeMs);
    dataServer.sampleAllSignals();
    loopCounter++;

  }





  /*=========================================================================*/
  /*=========================================================================*/

  /*
   * This set of functions is for simulation support only, and is not called on the real
   * robot. Put plant-model related functionality here. For training purposes,
   * students should not have to modify this functionality.
   */

  // Simple robot plant model for simulation purposes
  RobotModel simModel;

  @Override
  public void simulationInit() {
  }

  @Override
  public void simulationPeriodic() {

    simModel.update(isDisabled());
  }

  /*=========================================================================*/
  /*=========================================================================*/

}
