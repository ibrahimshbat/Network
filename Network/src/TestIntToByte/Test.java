package TestIntToByte;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.BitSet;
import java.util.concurrent.atomic.AtomicLong;

public class Test {
	//DatagramSocket socketUDPa = new DatagramSocket(2111);


	public static void main(String[] args) throws UnknownHostException, SocketException {
		
		DatagramSocket socketUDP = new DatagramSocket(2111);
//		int i =19;
//		byte[] b = new byte[4];
//        b[0] = (byte) (i);
//        System.out.println(b[0]);
//        i >>= 8;
//        b[1] = (byte) (i);
//        System.out.println(b[1]);
//        i >>= 8;
//        b[2] = (byte) (i);
//        System.out.println(b[2]);
//        i >>= 8;
//        b[3] = (byte) (i);;
//        System.out.println(b[3]);
//        System.out.println(b.length);
//        
//        int bits2 = 0b1000001;
//        int bits1 = 0b1111111;
//        bits2 &= bits1;
//        System.out.println(Integer.toBinaryString(bits2));
        
        String names[] = { "Java", "Source", "and",
        "Support"};
    BitSet bits = new BitSet();
    for (int i1 = 0, n = names.length; i1 < n; i1++) {
      if ((names[i1].length() % 2) == 0) {
        bits.set(i1);
      }
    }
    System.out.println(bits);
    System.out.println("Size : " + bits.size());
    System.out.println("Length: " + bits.length());
    for (int i1 = 0, n = names.length; i1 < n; i1++) {
      if (!bits.get(i1)) {
        System.out.println(names[i1] + " is odd");
      }
    }
    BitSet bites = new BitSet();
    bites.set(0);
    bites.set(1);
    bites.set(2);
    bites.set(3);
    bites.andNot(bits);
    System.out.println(bites);
    int start = 10;
    AtomicLong current = new AtomicLong(start);
    System.out.println(InetAddress.getLocalHost());
    long z = (374182406);
    System.out.println(Long.toHexString(z));
    


	}

}
