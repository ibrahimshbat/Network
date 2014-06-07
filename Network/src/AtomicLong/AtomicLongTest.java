package AtomicLong;
import java.util.concurrent.atomic.AtomicLong;

public class AtomicLongTest {

	  public static void main(String[] argv) {
	    AtomicLong nextId = new AtomicLong(6);

	    System.out.println(nextId.getAndDecrement());
	    System.out.println(nextId.getAndDecrement());
	    System.out.println(nextId.getAndDecrement());
	    System.out.println(nextId.getAndDecrement());
	    System.out.println(nextId.getAndDecrement());
	    System.out.println(nextId.getAndDecrement());
	    System.out.println(nextId.getAndDecrement());
	    System.out.println(nextId.getAndDecrement());
	    System.out.println(nextId.getAndDecrement());
	    System.out.println(nextId.getAndDecrement());
	    System.out.println(nextId.getAndDecrement());
	    long zxid = -1;
	    System.out.println((zxid & 0xffffffffL));

	  }
	}


