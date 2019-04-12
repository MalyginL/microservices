package project.jssc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import project.db.Upload;
import project.db.model.SendModel;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
@EnableAsync
@EnableScheduling
public class ByteParse {

    private static int deviceName;

    public synchronized void setDeviceName(int deviceName) {
        System.out.println("Current name" + this.deviceName);
        this.deviceName = deviceName;
    }

    @Autowired
    Upload upload;

    // public static List<StringBuffer> list = Collections.synchronizedList(new ArrayList<StringBuffer>());
    public static ConcurrentLinkedQueue<StringBuffer> queue = new ConcurrentLinkedQueue<>();

    public ByteParse() {
    }

    @Async
    public void run() {
        Thread.currentThread().setName("rawDataParse");
        while (true) {
            while (!queue.isEmpty()) {
                ByteParse.parse(queue.poll());
            }
            try {
                Thread.currentThread().sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void parse(StringBuffer buffer) {
        Short channel;
        StringBuilder sb = new StringBuilder();
        for (int i = 21; i < buffer.length() - 2; i = i + 2) {
            sb.append(buffer.charAt(i));
        }
        sb.reverse();
        //  System.out.println(sb);
        try {
            for (int i = 0; i < sb.length(); i = i + 21) {
                //    System.out.println("---------------");
                //  System.out.println("phase   " + sb.substring(i, i + 8) + "||" + new BigDecimal(Long.valueOf(sb.substring(i, i + 8), 16)).divide(new BigDecimal("99900000"), 15, RoundingMode.HALF_UP).toString());
                System.out.println("date    " + sb.substring(i + 9, i + 17) + "||" + Long.valueOf(sb.substring(i + 9, i + 17), 16));
                //    System.out.println("channel " + sb.substring(i + 17, i + 19) + "||" + Integer.valueOf(sb.substring(i + 17, i + 19), 16));
                channel = Short.valueOf(sb.substring(i + 17, i + 19), 16);
                if (JsscManagement.activeChannels.contains(String.valueOf(channel)))
                    Upload.queue.add(
                            new SendModel(deviceName,
                                    // Short.valueOf(sb.substring(i + 17, i + 19), 16),
                                    new BigDecimal(Long.valueOf(sb.substring(i, i + 8), 16)).
                                            divide(new BigDecimal("99900000"), 20, RoundingMode.HALF_UP),
                                    Integer.valueOf(sb.substring(i + 9, i + 17), 16)));
            }
        } catch (IndexOutOfBoundsException ex) {

        }

    }
}
