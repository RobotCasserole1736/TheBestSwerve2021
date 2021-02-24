package frc.sim;

import java.util.NoSuchElementException;

import edu.wpi.first.wpilibj.simulation.EncoderSim;
import frc.Constants;

public class SimQuadratureEncoder{

    EncoderSim baseEnc;

    double cyclesPrev = 0;

    final int CYCLES_PER_REV;
    final double DIST_PER_CYCLE;

    public SimQuadratureEncoder(int pinA, int pinB, int cyclesPerRev_in, double dist_per_pulse){
        try{
            baseEnc = EncoderSim.createForChannel(pinA);
        } catch(NoSuchElementException e){
            baseEnc = null; //User code didn't instantiate this quadrature encoder, so just don't bother updating it.
        }
        DIST_PER_CYCLE = dist_per_pulse;
        CYCLES_PER_REV = cyclesPerRev_in;

    }


    public void setShaftPositionRev(double shaft_pos_in){
        
        //Fairly simple model of encoder internals & FPGA interfacing? Maybe?
        if(baseEnc != null){
            double cycles = CYCLES_PER_REV * shaft_pos_in;
            double distance = cycles * DIST_PER_CYCLE * (baseEnc.getReverseDirection() ? -1.0 : 1.0);
            double rate = (cycles - cyclesPrev) / Constants.SIM_SAMPLE_RATE_SEC * DIST_PER_CYCLE ;
            boolean direction = (rate < 0);
    
    
            baseEnc.setCount((int) Math.floor(cycles));
            baseEnc.setDistance(distance);
            baseEnc.setRate(rate);
            baseEnc.setDirection(direction);
    
            cyclesPrev = cycles;
        }

    }
}