package linienfolger;
import javax.microedition.sensor.MindsensorsAccelerationSensorInfo;

import lejos.nxt.Button;
import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.util.Delay;


public class KlaLiFo {
	//static DifferentialPilot p=new DifferentialPilot(5.6, 15, Motor.B, Motor.C);
	static NXTRegulatedMotor mLinks = Motor.B;
	static NXTRegulatedMotor mRechts = Motor.C;
	public static UltrasonicSensor us =new UltrasonicSensor(SensorPort.S3);

	public static LightSensor ls=new LightSensor(SensorPort.S1, true);
	static int dark,light;
	static double kp=7.5;
	static int dist=10;
	static double speed=50, bremsfactor=0.75;
	static float mls,mrs;
	static LightListener ll;
	//static Btcom comm;
	public static void main(String[] args) {
				int kalib[] = kalibrieren();
		Button.ENTER.waitForPressAndRelease();
//		ll=new LightListener();
//		SensorPort.S1.addSensorPortListener(ll);
		SensorPort.S1.addSensorPortListener(new LightListener());
		Thread varch = new VarChanger();
		varch.setDaemon(true);
		varch.start();
		/*Button.ESCAPE.waitForPress();
		*/
		int lightValue, prevLightValue;
		//p.setTravelSpeed(5);
		mLinks.setSpeed(5);
		mRechts.setSpeed(5);
		
		ls.setFloodlight(true);
		prevLightValue = ls.readValue();
		uslistener usl=new uslistener();
		SensorPort.S3.addSensorPortListener(usl);
		usl.setDaemon(true);
//		usl.start();
		
		//p.steer(100);
		mLinks.forward();
		mRechts.forward();
//		while(Button.ESCAPE.isUp()){
//			lightValue = ls.readValue();
//			if(Math.abs(lightValue-kalib[0]) < 5 ){
//				//heller geworden
//				System.out.println("heller " + lightValue);
//				p.steer(120);
//				
//			} else if (Math.abs(lightValue-kalib[1]) < 5 ){
//				//dunkler geworden
//
//				System.out.println("dunkler " + lightValue);
//				p.steer(-120);
//			}
//
			//Delay.msDelay(50);
			
//		}
		Button.ESCAPE.waitForPress();
		
		
	}
	
	private static int[] kalibrieren(){
		ls.setFloodlight(true);
		int kalib[] = new int[3];
		System.out.println("Setze auf weiss.");
		Button.ENTER.waitForPressAndRelease();
		ls.calibrateHigh();
		kalib[0] = ls.getLightValue();

		System.out.println("Setze auf schwarz.");
		Button.ENTER.waitForPressAndRelease();
		ls.calibrateLow();
		kalib[1] = ls.getLightValue();
/*
		System.out.println("Setze auf die Linie.");
		Button.ENTER.waitForPressAndRelease();
		kalib[2] = ls.getLightValue();
	*/	
		System.out.println(kalib[0] + " "+kalib[1]+" "+kalib[2]);
		return kalib;
	}
}