package wettKampf;

import javax.microedition.sensor.SensorConnection;

import lejos.nxt.Button;
import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.navigation.DifferentialPilot;

public class Main {
	static NXTRegulatedMotor motorRechts = Motor.B;
	static NXTRegulatedMotor motorLinks = Motor.C;
	static NXTRegulatedMotor motorArm = Motor.A;
	
	static UltrasonicSensor sensUltra = new UltrasonicSensor(SensorPort.S1);
	static LightSensor sensLicht = new LightSensor(SensorPort.S2);
	
	static int colorHome;
	static int colorEnemy;
	static DifferentialPilot p=new DifferentialPilot(5.6, 15, Motor.B, Motor.C);
	static final int armAngle = 120;
	public static void main(String args[]){
		
		//Licht kalibrieren
		sensLicht.setFloodlight(true);
		System.out.println("Auf eigene Zone setzen");
		Button.ENTER.waitForPress();
		colorHome = sensLicht.getLightValue();
		System.out.println("Auf Gegner-Zone setzen");
		Button.ENTER.waitForPress();
		colorEnemy = sensLicht.getLightValue();
		
		//
		System.out.println("Stell mich in mein Feld und drücke HOME");
		
		
		while (true){
		//drehen und scannen
		int[] map = new int[91];
		for (int i = 0; i < 90; i++){
			map[i] = sensUltra.getDistance();
			p.arc(0, 1, false);
		}
		int deg = 0;
		int entf = 255;
		for (int i  = 0; i < map.length; i++){
			if(map[i] < entf){
				deg = 90-i;
			}
		}
		p.arc(0, -deg, false);
		p.setTravelSpeed(100);
		p.travel(entf);
		motorArm.rotate(armAngle, false);
		p.arc(0, 180);
		p.forward();
		while(sensLicht.getLightValue() != colorHome){
			
		}
		p.stop();
		motorArm.rotate(-armAngle, false);
		p.travel(2);
		p.arc(0, 180);
		}
	} 
	
}
