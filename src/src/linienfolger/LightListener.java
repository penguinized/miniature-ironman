package linienfolger;

import lejos.nxt.SensorPort;
import lejos.nxt.SensorPortListener;

public class LightListener implements SensorPortListener {

	@Override
	public void stateChanged(SensorPort aSource, int aOldValue, int aNewValue) {
		// TODO Auto-generated method stub
		int e,val;
		val=KlaLiFo.ls.getLightValue();
		System.out.println("hier");
		e=50-val;
		double y= (0-KlaLiFo.kp*e);
		KlaLiFo.p.steer(y);
//		System.out.println(aNewValue);
//		System.out.println(KlaLiFo.ls.getLightValue());
		System.out.println(y);System.out.println(e);
		double s=KlaLiFo.speed-KlaLiFo.bremsfactor* (Math.abs(e));
		s=(s>0)?s:5;
		KlaLiFo.p.setTravelSpeed(s);
		KlaLiFo.p.setRotateSpeed(s);
//		System.out.println(KlaLiFo.p.getTravelSpeed());
	}

}
