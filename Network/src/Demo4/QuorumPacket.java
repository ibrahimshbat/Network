package Demo4;



public class QuorumPacket implements Record {
  private int type;
  private long zxid;
  private byte[] data;
  public QuorumPacket() {
  }
  public QuorumPacket(
        int type,
        long zxid,
        byte[] data) {
    this.type=type;
    this.zxid=zxid;
    this.data=data;
  }
  public int getType() {
    return type;
  }
  public void setType(int m_) {
    type=m_;
  }
  public long getZxid() {
    return zxid;
  }
  public void setZxid(long m_) {
    zxid=m_;
  }
  public byte[] getData() {
    return data;
  }
  public void setData(byte[] m_) {
    data=m_;
  }
  
  public void serialize(OutputArchive a_, String tag) throws java.io.IOException {
    //a_.startRecord(this,tag);
    a_.writeInt(type,"type");
    a_.writeLong(zxid,"zxid");
    a_.writeBuffer(data,"data");
    
   // a_.endRecord(this,tag);
  }
  public void deserialize(InputArchive a_, String tag) throws java.io.IOException {
    //a_.startRecord(tag);
  	System.out.println(tag);

    type=a_.readInt("type");
    zxid=a_.readLong("zxid");
    data=a_.readBuffer("data");   
   // a_.endRecord(tag);
}
  public String toString() {
    try {
      java.io.ByteArrayOutputStream s =
        new java.io.ByteArrayOutputStream();
      CsvOutputArchive a_ = 
        new CsvOutputArchive(s);
      a_.startRecord(this,"");
    a_.writeInt(type,"type");
    a_.writeLong(zxid,"zxid");
	System.out.println("sdqwddds");

    a_.writeBuffer(data,"data");
     a_.endRecord(this,"");
      return new String(s.toByteArray(), "UTF-8");
    } catch (Throwable ex) {
      ex.printStackTrace();
    }
    return "ERROR";
  }
  public void write(java.io.DataOutput out) throws java.io.IOException {
    BinaryOutputArchive archive = new BinaryOutputArchive(out);
    serialize(archive, "");
  }
  public void readFields(java.io.DataInput in) throws java.io.IOException {
    BinaryInputArchive archive = new BinaryInputArchive(in);
    deserialize(archive, "");
  }
  public int compareTo (Object peer_) throws ClassCastException {
    throw new UnsupportedOperationException("comparing QuorumPacket is unimplemented");
  }
  public boolean equals(Object peer_) {
    if (!(peer_ instanceof QuorumPacket)) {
      return false;
    }
    if (peer_ == this) {
      return true;
    }
    QuorumPacket peer = (QuorumPacket) peer_;
    boolean ret = false;
    ret = (type==peer.type);
    if (!ret) return ret;
    ret = (zxid==peer.zxid);
    if (!ret) return ret;
    
     return ret;
  }
  public int hashCode() {
    int result = 17;
    int ret;
    ret = (int)type;
    result = 37*result + ret;
    ret = (int) (zxid^(zxid>>>32));
    result = 37*result + ret;
    ret = java.util.Arrays.toString(data).hashCode();
    result = 37*result + ret;
    result = 37*result + ret;
    return result;
  }
  public static String signature() {
    return "LQuorumPacket(ilB[LId(ss)])";
  }
}
