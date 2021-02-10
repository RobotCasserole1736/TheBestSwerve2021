/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import frc.lib.Calibration.CalWrangler;
import frc.lib.DataServer.CasseroleDataServer;
import frc.lib.DataServer.Annotations.Signal;
import frc.lib.WebServer.CasseroleWebServer;

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

  @Signal(units = "count")
  int loopCounter = 0;

  @Override
  public void robotInit() {
    /* Init website utilties */
    webserver = new CasseroleWebServer();
    wrangler = new CalWrangler();
    dataServer = CasseroleDataServer.getInstance();
    loopTiming = LoopTiming.getInstance();


    dataServer.registerSignals(this);
    dataServer.startServer();
    webserver.startServer();

  }

  @Override
  public void robotPeriodic() {
  }

  @Override
  public void autonomousInit() {
  }

  @Override
  public void autonomousPeriodic() {
    loopTiming.markLoopStart();
    loopCounter--;
    System.out.println("Da swerve is da wervd");

    dataServer.sampleAllSignals();
    loopTiming.markLoopEnd();

  }

  @Override
  public void teleopInit() {
  }

  @Override
  public void teleopPeriodic() {
    loopTiming.markLoopStart();
    loopCounter++;
    System.out.println("Hello World");

    dataServer.sampleAllSignals();
    loopTiming.markLoopEnd();

  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    loopTiming.markLoopStart();

    dataServer.sampleAllSignals();
    loopTiming.markLoopEnd();
  }

  @Override
  public void testInit() {
  }

  @Override
  public void testPeriodic() {
  }

}
