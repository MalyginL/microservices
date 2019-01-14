package project.file;

import org.joda.time.DateTimeUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.*;

@Service
public class ExtFile {

    public HashMap<Integer, Long[]> parsingResult(File local) {

        try (BufferedReader br = new BufferedReader(new FileReader(local))) {
            for (int i = 0; i < 19; i++) {
                br.readLine();
            }
            String s;
            while ((s = br.readLine()) != null) {
                String[] array = s.split(" ");

            }
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
        return null;
    }


    public List<FileModel> parse(File file) {
        List<FileModel> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            for (int i = 0; i < 19; i++) {
                br.readLine();
            }
            String s;
            while ((s = br.readLine()) != null) {
                String[] array = s.trim().split("\\W+");
                if ((Integer.valueOf(array[4]) == 780) && Integer.valueOf(array[5]) > 150) {
                    list.add(new FileModel(Integer.valueOf(array[0]),
                            array[3],
                            new BigDecimal(array[9]).divide(new BigDecimal("10000000000")),
                            array[22]));
                }
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

    public List<FileModel> findCrossing(List<FileModel> first, List<FileModel> second) {
        List<FileModel> result = new ArrayList<>();
        first.forEach(e -> {
            if (second.contains(e)) {
                e.setRefsv((second.get(second.indexOf(e)).getRefsv().subtract(e.getRefsv())));
                //         System.out.println((second.get(second.indexOf(e)).getRefsv() + "/" + e.getRefsv()));
                result.add(e);

            }

        });
        return result;
    }

    public HashMap<String, List<FileModel>> sort(List<FileModel> list) {
        HashMap<String, List<FileModel>> map = new HashMap<>();
        for (FileModel e : list) {
            if (map.containsKey(e.getFrc())) {
                map.get(e.getFrc()).add(e);

            } else {
                List<FileModel> model = new ArrayList<>();
                model.add(e);
                map.put(e.getFrc(), model);
            }
        }

        return map;
    }

    public HashMap<String, List<FileModel>> filter(HashMap<String, List<FileModel>> map) {
        HashMap<String, List<FileModel>> fixed = new HashMap<>();
        map.entrySet().forEach(e -> {
            BigDecimal summ = new BigDecimal("0");
            List<FileModel> model = e.getValue();
            for (FileModel f : model) {
                summ = summ.add(f.getRefsv());
            }
            BigDecimal sred = summ.divide(new BigDecimal(model.size()), 20, RoundingMode.CEILING);
            List<FileModel> re = new ArrayList<>();
            for (FileModel f : model) {
                System.out.println(f.getRefsv().subtract(sred).abs());
                System.out.println(new BigDecimal("3").multiply(skdo(model)).abs());
                if ((f.getRefsv().subtract(sred).abs()).compareTo(new BigDecimal("3").multiply(skdo(model)).abs()) == -1) {
                    re.add(f);
                }

            }
            fixed.put(e.getKey(), re);
        });
        return fixed;
    }

    public BigDecimal mnc(List<FileModel> list) {
        BigDecimal sumxy = new BigDecimal("0");
        BigDecimal sumx = new BigDecimal("0");
        BigDecimal sumy = new BigDecimal("0");
        BigDecimal sumx2 = new BigDecimal("0");
        BigDecimal num = new BigDecimal(String.valueOf(list.size()));

        for (FileModel i : list) {
            //  System.out.println("on" + i.getFrc() + "|| sat" + i.getSat() + "x"+i.getSttime() +"|||" + i.getRefsv());
            BigDecimal x = new BigDecimal(fixtime(i.getSttime()));
            BigDecimal y = i.getRefsv();
            System.out.println(y);
            sumxy = sumxy.add(x.multiply(y, MathContext.UNLIMITED));
            sumx = sumx.add(x);
            sumy = sumy.add(y);
            sumx2 = sumx2.add(x.pow(2, MathContext.UNLIMITED));
        }
        System.out.println("-----------------------РАСЧЕТ МНК ДЛЯ ИТОГОВОГО ПРОМЕЖУТКА-----------------------");
        System.out.println("sumXY   " + sumxy);
        System.out.println("sumX    " + sumx);
        System.out.println("sumY    " + sumy);
        System.out.println("sumX2   " + sumx2);
        System.out.println("N   " + num);
        System.out.println("----------");
        BigDecimal a = ((num.multiply(sumxy, MathContext.UNLIMITED)).subtract(sumx.multiply(sumy, MathContext.UNLIMITED)))
                .divide(num.multiply(sumx2, MathContext.UNLIMITED).subtract(sumx.pow(2)), 40, RoundingMode.CEILING);
        BigDecimal b = new BigDecimal((sumy.subtract(a.multiply(sumx, MathContext.UNLIMITED))).divide(num, 40, RoundingMode.CEILING).toString());
        System.out.println("a =" + a);
        System.out.println("b =" + b);


        return (a.multiply(new BigDecimal("86400")).add(b));
    }

    public static int fixtime(String time) {
        return Integer.valueOf(time.substring(0, 2)) * 3600 + Integer.valueOf(time.substring(2, 4)) * 60 + Integer.valueOf(time.substring(4, 6));
    }

    @Bean
    public static boolean formateDate() {
        double v = DateTimeUtils.toJulianDayNumber(System.currentTimeMillis()) - 1;
        System.out.println(v);
        return true;
    }

    public BigDecimal skdo(List<FileModel> list) throws NullPointerException {
      //  System.out.println("skdo called");
        BigDecimal summ = new BigDecimal("0");
        BigDecimal skdo;
        BigDecimal temp = new BigDecimal("0");

     //   System.out.println(list.size());
        for (FileModel e : list) {
            BigDecimal big = new BigDecimal(String.valueOf(e.getRefsv()));
            if (!temp.toString().equals("0")) {
                summ = summ.add(((big.subtract(temp))).pow(2, MathContext.DECIMAL32));

            }
            temp = big;
        }


     //   System.out.println("summ1" + summ);
        summ = summ.divide(new BigDecimal(String.valueOf(list.size() - 1)), 100, RoundingMode.CEILING);
    //    System.out.println("summ skdo" + summ);

        skdo = sqrtBabylon(summ, 70);
        return skdo;
    }

    public BigDecimal sqrtBabylon(BigDecimal in, int scale) {
        BigDecimal sqrt = new BigDecimal(1);
        sqrt.setScale(scale + 3, RoundingMode.FLOOR);
        BigDecimal store = new BigDecimal(in.toString());
        boolean first = true;
        do {
            if (!first) {
                store = new BigDecimal(sqrt.toString());
            } else first = false;
            store.setScale(scale + 3, RoundingMode.FLOOR);
            sqrt = in.divide(store, scale + 3, RoundingMode.FLOOR).add(store).divide(
                    BigDecimal.valueOf(2), scale + 3, RoundingMode.FLOOR);
        } while (!store.equals(sqrt));
        return sqrt.setScale(scale, RoundingMode.FLOOR);
    }

}
