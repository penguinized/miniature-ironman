package pcBT;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Scanner;

import lejos.pc.comm.*;

public class PCBTConn {
	private NXTInfo[] nxts;
	private NXTConnector conn = new NXTConnector();
	private Scanner sc = new Scanner(System.in);

	void start() {
		System.out.println(" Suche BT NXTs ... ");
		nxts = conn.search("", null, NXTCommFactory.BLUETOOTH);
		for (int i = 0; i < nxts.length; i++) {
			System.out.println(i + ": " + nxts[i].name + " "
					+ nxts[i].deviceAddress);
		}
		int index = sc.nextInt();
		if (!conn.connectTo(nxts[index], NXTComm.PACKET)) {
			System.out.println(" Konnte nicht verbinden ... ");
			System.exit(0);
		}
		// Get NXTComm Object for I/O
		NXTComm comm = conn.getNXTComm();
		sc.useDelimiter(System.getProperty("line.separator"));
		byte[] buffer = new byte [1+Float.BYTES];
		while (true) {
			try {
				System.out.print(" Geben Sie die Variable und den neuen Wert ein:");
				String cmd = sc.next();
				String[] parts = cmd.split(" ");
				if(parts.length != 2){
					System.out.println("Ungueltige Eingabe");
					continue;
				}
				else{
					switch (parts[0]) {
					case "k":
						buffer[0] =  (byte) 0;
						break;
					case "v":
						buffer[0] = (byte)1;
						break;
					case "b":
						buffer[0] = (byte)2;
						break;
					default:
						System.out.println("Ungueltige Eingabe, Variable muss k, v oder b sein.");
						continue;
					}
					int i = Float.floatToIntBits(Float.parseFloat(parts[1]));
					buffer[1] = (byte) (i & 0xFF);
					buffer[2] = (byte) (i & (0xFF << 8));
		            buffer[3] = (byte) (i & (0xFF << 16)); 
		            buffer[4] = (byte) (i & (0xFF << 24));
					comm.write(buffer);
					System.out.println("Variable erfoglrich gesetzt.");
					//byte[] reply = comm.read();
					//String s = new String(reply);
					//System.out.println(s);
				}
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public static void main(String[] args) {
		PCBTConn bt = new PCBTConn();
		bt.start();
	}
}
