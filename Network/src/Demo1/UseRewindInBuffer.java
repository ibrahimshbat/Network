package Demo1;


import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;


public class UseRewindInBuffer {
  public static void main(String[] args) throws IOException {
    FileInputStream aFile = new FileInputStream("d://rewind.txt");
    FileChannel inChannel = aFile.getChannel();
    ByteBuffer buf = ByteBuffer.allocate(100);
    int bytesRead = inChannel.read(buf);
    buf.flip();
System.out.print("Without rewind the content of buffer :\n");
    while (buf.hasRemaining()) {
      System.out.print((char) buf.get());
    }
    buf.rewind();
System.out.print("\nAfter using rewind content of buffer : \n");
    while (buf.hasRemaining()) {
      System.out.print((char) buf.get());
    }
    aFile.close();
  }
}