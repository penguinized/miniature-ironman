package stau;

import lejos.nxt.SensorPort;
import lejos.nxt.SensorPortListener;

public class uslistener implements SensorPortListener {

	@Override
	public void stateChanged(SensorPort aSource, int aOldValue, int aNewValue) {
		double s=stau.us.getDistance()-10;
		System.out.println(s);
		s=(s>0)?s:1;
		stau.p.setTravelSpeed(5*s);
	
	}

}
