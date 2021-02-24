/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.TimedRobot;
import frc.Constants;
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

  // keep track of the robot's actual, estimated, and desired position on the field
  DtPoseView dtPoseView;
  DtModuleStatesView dtModStatesView;

  @Signal(units = "count")
  int loopCounter = 0;

  Spark testAzmthMotor;
  Spark testWheelMotor;


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
    dtPoseView = new DtPoseView();
    dtModStatesView = new DtModuleStatesView();

    //Very silly temporary controller logic
    // Yes, this should be deleted and moved and changed to be reasonable.
    testAzmthMotor = new Spark(Constants.FL_AZMTH_MOTOR_IDX);
    testWheelMotor = new Spark(Constants.FL_WHEEL_MOTOR_IDX);

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

    //Very silly temporary controller logic
    // Yes, this should be deleted and moved and changed to be reasonable.
    testAzmthMotor.set(1.0);
    testWheelMotor.set(1.0);


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
      dtPoseView.setActualPose(simModel.getCurActPose());
    }

    //TODO - fill these out to populate the web gui with useful debug info
    //dtPoseView.setEstimatedPose(put something here); 
    //dtPoseView.setDesiredPose(put something here); 
    //dtModStatesView.setActualStates(FLAct_in, FRAct_in, BLAct_in, BRAct_in); 
    //dtModStatesView.setDesiredStates(FLDes_in, FRDes_in, BLDes_in, BRDes_in);

    double sampleTimeMs = LoopTiming.getInstance().getLoopStartTimeSec() * 1000;
    dtPoseView.update(sampleTimeMs);
    dtModStatesView.update(sampleTimeMs);
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
