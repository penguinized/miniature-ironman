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
		ByteBuffer buffer = ByteBuffer.allocate(1+Float.BYTES); 
		while (true) {
			try {
				System.out.print(" Geben Sie die Variable und den neuen Wert ein:");
				String cmd = sc.next();
				String[] parts = cmd.split(" ");
				if(parts.length != 2 || parts[0].length() > 1){
					System.out.println("Ungueltige Eingabe");
					continue;
				}
				else{
					switch (parts[0]) {
					case "k":
						buffer.put(0, (byte) 0);
						break;
					case "v":
						buffer.put(0,(byte)1);break;
					case "b":
						buffer.put(0, (byte)2);break;
					default:
						System.out.println("Ungueltige Eingabe, Variable muss k, v oder b sein.");
						continue;
					}
					
					buffer.putFloat(1,Float.parseFloat(parts[1]));
					comm.write(buffer.array());
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
