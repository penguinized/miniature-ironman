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
				int l = conn.read (b, b.length );
				if(l != b.length){//there has been an error... on either side.
					continue;
				}
				switch (b[0]) {
				case (byte) 0 :
					KlaLiFo.kp =  myByteArrayToFloat(b);
					System.out.println("kp="+KlaLiFo.kp);
					break;
				case 1:
					KlaLiFo.speed = myByteArrayToFloat(b);
							System.out.println("speed="+KlaLiFo.speed);
					break;
				case 2:
					KlaLiFo.bremsfactor =  myByteArrayToFloat(b);
							System.out.println("brems="+KlaLiFo.bremsfactor);
				default:
					break;
				}
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	public float myByteArrayToFloat(byte[] b){
		return Float.intBitsToFloat((b[1] & 0xff)  
	            | ((b[2]  & 0xff) << 8) 
	            | ((b[3] & 0xff) << 16) 
	            | ((b[4]  & 0xff) << 24));
	}
}
