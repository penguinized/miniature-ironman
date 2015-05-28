import java.util.Arrays;


public class asdasdasd {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		byte[] buffer  = new byte[5];
		String[] parts = new String[2];
		parts[1] = "1024";
		
		int i = Float.floatToIntBits(Float.parseFloat(parts[1]));
		System.out.println(Float.parseFloat(parts[1]));
		System.out.println(i);
//		Inte
		buffer= i;
		buffer[1] = (byte) (i & 255);
		buffer[2] = (byte) ((i & (255 << 8)) >> 8);
	    buffer[3] = (byte) ((i & (255 << 16)) >> 16); 
	    buffer[4] = (byte) ((i & (255 << 24)) >> 24);
	    System.out.println((byte)((1120403456 & (255 << 16))>>16));
	    System.out.println(Arrays.toString(buffer));
		System.out.println(Float.intBitsToFloat(0| (int) buffer[1]  
	            | (((int)buffer[2] ) << 8) 
	            | (((int)buffer[3]) << 16) 
	            | (((int)buffer[4] ) << 24)));

	}

}
