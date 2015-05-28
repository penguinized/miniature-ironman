package testsi;

public class ConvTest {
	public static void main(){
		
	byte[] buffer  = new byte[5];
	String[] parts = new String[2];
	parts[1] = "100";
	int i = Float.floatToIntBits(Float.parseFloat(parts[1]));
	buffer[1] = (byte) (i & 0xFF);
	buffer[2] = (byte) (i & (0xFF << 8));
    buffer[3] = (byte) (i & (0xFF << 16)); 
    buffer[4] = (byte) (i & (0xFF << 24));
	System.out.println(Float.intBitsToFloat((buffer[1] & 0xFF) 
            | ((buffer[2] & 0xFF) << 8) 
            | ((buffer[3] & 0xFF) << 16) 
            | ((buffer[4] & 0xFF) << 24)));

	}}
