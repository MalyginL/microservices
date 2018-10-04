package project.jssc.model;

import org.springframework.stereotype.Repository;

import javax.xml.bind.DatatypeConverter;

@Repository
public class CommandList {

    public static final byte[] CLEAN_CPU = {0x01, 0x3F, 0x1A};
    public static final byte[] GET_VERSION = {0x01, 0x37, 0x1A};
    public static final byte[] TEST = {0x01, 0x36, 0x1A};
    public static final byte[] GETDATA = {0x01, 0x3A, 0x1A};
    public static final byte[] SYNCHR = {0x01, 0x33, 0x1A};
    private static final byte[] STARTTIME = {0x01, 0x40};
    private static final byte[] ENDTIME = {0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x1A};

    public static byte[] getTime() {
        System.out.println("setTime"  + System.currentTimeMillis() / 1000);
        int date = (int) (System.currentTimeMillis() / 1000);
        byte[] time = new byte[]{
                (byte) (date >> 24),
                (byte) (date >> 16),
                (byte) (date >> 8),
                (byte) date
        };


        byte[] result = {0x01, 0x40, time[3], time[2], time[1], time[0], 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x1A};
//        byte[] time = ByteBuffer.allocate(4)
//                .order(ByteOrder.LITTLE_ENDIAN)
//                .putInt((int) System.currentTimeMillis() / 1000)
//                .array();
//        System.out.println(time.length);
//        byte[] result = {0x01, 0x40, time[3], time[2], time[1], time[0], 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x1A};
//         byte[] time2={ time[0], time[1], time[2], time[3]};
      //  System.out.println( "REZ" +ByteBuffer.wrap(time2).getInt());
        return result;
    }

    public static byte[] destroyBuffer(int channel) {
        byte[] result = {0x01, 0x3D, 0x30, (byte) Integer.parseInt("3"+String.valueOf(channel), 16), 0x1A};
        return result;
    }

    private static byte[] toByteArray(String s) {
        return DatatypeConverter.parseHexBinary("3" + s);
    }
}
