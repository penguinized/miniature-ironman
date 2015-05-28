package linienfolger;

import lejos.nxt.comm.*;

public class VarChanger extends Thread {
	BTConnection conn = null;

	public void run() {
		Bluetooth.setName("Robert");
		
		conn = Bluetooth.waitForConnection(0, NXTConnection.PACKET);
		while (true) {
			try {
				byte[] b = new byte[1+Float.SIZE];
				int l= conn.read (b, b.length );
				switch (b[0]) {
				case (byte) 0 :
					KlaLiFo.kp =  Float.intBitsToFloat((b[1] & 0xFF) 
				            | ((b[2] & 0xFF) << 8) 
				            | ((b[3] & 0xFF) << 16) 
				            | ((b[4] & 0xFF) << 24));
					System.out.println("kp="+KlaLiFo.kp);
					break;
				case 1:
					KlaLiFo.speed = Float.intBitsToFloat(b[1]
				            | (b[2]  << 8) 
				            | (b[3]  << 16) 
				            | (b[4]  << 24));
							System.out.println("speed="+KlaLiFo.speed);
					break;
				case 2:
					KlaLiFo.bremsfactor =  Float.intBitsToFloat((b[1] & 0xFF) 
				            | ((b[2] & 0xFF) << 8) 
				            | ((b[3] & 0xFF) << 16) 
				            | ((b[4] & 0xFF) << 24));
							System.out.println("brems="+KlaLiFo.bremsfactor);
				default:
					break;
				}
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
