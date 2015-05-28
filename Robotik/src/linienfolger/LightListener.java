package linienfolger;

import lejos.nxt.SensorPort;
import lejos.nxt.SensorPortListener;

public class LightListener implements SensorPortListener {

	@Override
	public void stateChanged(SensorPort aSource, int aOldValue, int aNewValue) {
		int e,val;
		val=KlaLiFo.ls.getLightValue();
//		System.out.println("hier");
		e=50-val;
		double y= (KlaLiFo.kp*e);
//		System.out.println(aNewValue);
//		System.out.println(KlaLiFo.ls.getLightValue());
		System.out.println("Stellgröße"+ y);System.out.println("Fehler"+e);
		double sl=KlaLiFo.speed-(KlaLiFo.bremsfactor* (+y));
		double sr=KlaLiFo.speed-(KlaLiFo.bremsfactor* (-y));
		sl=(sl>0)?sl:0.1;
		sl=(sl>KlaLiFo.speed)?KlaLiFo.speed:sl;
		sr=(sr>0)?sr:0.1;
		sr=(sr>KlaLiFo.speed)?KlaLiFo.speed:sr;
		System.out.println("Speed links"+sl);
		System.out.println("Speed rechts"+sr);
		KlaLiFo.mls=(float)sl;
		KlaLiFo.mrs=(float)sr;
		KlaLiFo.mLinks.setSpeed((float)sl );
		KlaLiFo.mRechts.setSpeed((float)sr);

		//KlaLiFo.p.setTravelSpeed(s);
		//KlaLiFo.p.setRotateSpeed(s);
//		System.out.println(KlaLiFo.p.getTravelSpeed());
	}

}
