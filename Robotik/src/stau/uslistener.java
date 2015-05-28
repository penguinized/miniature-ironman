package stau;

import lejos.nxt.SensorPort;
import lejos.nxt.SensorPortListener;

public class uslistener extends Thread {

	

	@Override
	public void run() {
		float s;
		while (true) {
			s=stau.us.getDistance()-10;
			System.out.println(s);
			s=(s>0)?s:1;
			
			stau.motoLinks.setSpeed(5*s);

			stau.motoRechts.setSpeed(5*s);
			//stau.p.setTravelSpeed(5*s);
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
			}
		
		}
		
	}
}
