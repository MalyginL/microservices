package project.file;

import org.joda.time.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.HashMap;
import java.util.List;

@Component
@EnableScheduling
public class CronTimer {

    @Autowired
    ExtFile extFile;

    @Value("${ftp.rirtdirectory}")
    private String rirt;

    @Value("${ftp.localdirectory}")
    private String local;

    @Scheduled(cron = "0 * * * * *")
    public void calculate() {
        double jday = ((DateTimeUtils.toJulianDayNumber(System.currentTimeMillis())) - 2400006D) / 1000D;
//        System.out.println(rirt + "\\6RRT" + (int) (jday * 1000D) + ".dat");
//        System.out.println(new File(rirt + "\\6RRT" + (int) (jday * 1000D) + ".dat").exists());
//        System.out.println(new File(local + "\\GMSU19" + jday).exists());
//        System.out.println(local + "\\GMSU19" + jday);



        if ((new File(local + "\\GMSU19" + jday).exists()) && (new File(rirt + "\\6RRT" + (int) (jday * 1000D) + ".dat").exists())) {
            System.out.println(jday);
            HashMap<String, List<FileModel>> map = extFile.filter(extFile.sort(extFile.findCrossing(extFile.parse(new File(rirt + "\\6RRT" + (int) (jday * 1000D) + ".dat")), extFile.parse(new File(local + "\\GMSU19" + jday)))));
            map.entrySet().forEach(e->
                    System.out.println("for RFC:    " + e.getKey() + "| VALUE:  " + extFile.mnc(e.getValue())));
        }

    }

}
