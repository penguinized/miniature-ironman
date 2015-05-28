package stau;

import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.navigation.DifferentialPilot;

public class stau {
	//static DifferentialPilot p = new DifferentialPilot(5.6, 15, Motor.B,
	//		Motor.C);
	static UltrasonicSensor us = new UltrasonicSensor(SensorPort.S2);
	static NXTRegulatedMotor motoLinks = Motor.B;
	static NXTRegulatedMotor motoRechts = Motor.C;
	
	static float startSpeed= 100f;
	
	public static void main(String[] args) {
		uslistener usl = new uslistener();
		usl.start();
		
		System.out.println("hello");
		System.out.println("world");
		
		//p.steer(0);
		motoLinks.setSpeed(startSpeed);
		motoRechts.setSpeed(startSpeed);
		
		// int i=us.setMode(UltrasonicSensor.MODE_CONTINUOUS);
		System.out.println("hi");

		Button.ESCAPE.waitForPress();
	}

}
