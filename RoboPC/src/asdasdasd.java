import java.util.Arrays;


public class asdasdasd {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		byte[] buffer  = new byte[5];
		String[] parts = new String[2];
		parts[1] = "-100.123123123123123123123123";
		
		int i = Float.floatToIntBits(Float.parseFloat(parts[1]));
		System.out.println(Float.parseFloat(parts[1]));
		System.out.println(i);
//		Inte
		//buffer= i;
		buffer[1] = (byte)(i & 0xff);
		buffer[2] = (byte)((i >>> 8) & 0xff);
	    buffer[3] = (byte)((i >>> 16) & 0xff);
	    buffer[4] = (byte)((i >>> 24) & 0xff);
	    System.out.println((byte)((1120403456 & (255 << 16))>>16));
	    System.out.println(Arrays.toString(buffer));
		System.out.println(Float.intBitsToFloat((buffer[1] & 0xff)  
	            | ((buffer[2]  & 0xff) << 8) 
	            | ((buffer[3] & 0xff) << 16) 
	            | ((buffer[4]  & 0xff) << 24)));

	}

}
