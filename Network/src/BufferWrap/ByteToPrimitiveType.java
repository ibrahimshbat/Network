package BufferWrap;

import java.nio.ByteBuffer;

public class ByteToPrimitiveType {

	public static void main(String[] args) {
		int port = 2111;
		byte [] b = " sd".getBytes();
		ByteBuffer bb = ByteBuffer.wrap(new byte[] {0,0 ,1 , 4});
		long l = bb.getInt();

		System.out.println(l);
	}

}
