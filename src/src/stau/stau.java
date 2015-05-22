package stau;

import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.navigation.DifferentialPilot;

public class stau {
	static DifferentialPilot p=new DifferentialPilot(5.6, 15, Motor.B, Motor.C);
	static UltrasonicSensor us =new UltrasonicSensor(SensorPort.S2);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		uslistener usl=new uslistener();
		
SensorPort.S2.addSensorPortListener(usl);
System.out.println("hello");
System.out.println("world");
p.steer(0);
//int i=us.setMode(UltrasonicSensor.MODE_CONTINUOUS);
System.out.println("hi");

Button.ESCAPE.waitForPress();
	}

}
