package linienfolger;

import lejos.nxt.SensorPort;
import lejos.nxt.SensorPortListener;
import linienfolger.KlaLiFo;

public class uslistener extends Thread implements SensorPortListener {

	@Override
	public void run() {
	int	 valuea,valueb;
		// TODO Auto-generated method stub
		super.run();
		valuea=0;
		while(true){
		valueb=KlaLiFo.us.getDistance();
		this.stateChanged(SensorPort.S3, valuea, valueb);
		valuea=valueb;
		}
	}

	@Override
	public void stateChanged(SensorPort aSource, int aOldValue, int aNewValue) {
		
		double s=(aNewValue-KlaLiFo.dist)/100;
		System.out.println(s);
		s=(s>=0)?s:0;
		//s=(s<=150)?s:150;
		if (s < 1 && s>=0) {
			System.out.println("Hey ausgabe");
			KlaLiFo.mLinks.setSpeed(KlaLiFo.mls*(float)s);
			KlaLiFo.mRechts.setSpeed(KlaLiFo.mrs*(float)s);}
		else {
			KlaLiFo.mLinks.setSpeed(KlaLiFo.mls);
			KlaLiFo.mRechts.setSpeed(KlaLiFo.mrs);
		}
		//KlaLiFo.speed=s;
	
	}

}
