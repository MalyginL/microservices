package correctionservice.calculation;

import correctionservice.calculation.model.LinealModel;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.HashMap;

@Component
public class CorrectionMath {

    public static LinealModel mnc(HashMap<BigDecimal, BigDecimal> map) {
        BigDecimal sumxy = new BigDecimal("0");
        BigDecimal sumx = new BigDecimal("0");
        BigDecimal sumy = new BigDecimal("0");
        BigDecimal sumx2 = new BigDecimal("0");
        BigDecimal num = new BigDecimal(String.valueOf(map.size()));
        try {
            for (HashMap.Entry<BigDecimal, BigDecimal> item : map.entrySet()) {
                sumxy = sumxy.add(item.getKey().multiply(item.getValue(), MathContext.UNLIMITED));
                sumx = sumx.add(item.getKey());
                sumy = sumy.add(item.getValue());
                sumx2 = sumx2.add(item.getKey().pow(2, MathContext.UNLIMITED));
            }
            System.out.println("-----------------------РАСЧЕТ МНК ДЛЯ ИТОГОВОГО ПРОМЕЖУТКА-----------------------");
            System.out.println("sumXY   " + sumxy);
            System.out.println("sumX    " + sumx);
            System.out.println("sumY    " + sumy);
            System.out.println("sumX2   " + sumx2);
            System.out.println("N   " + num);
            System.out.println("----------");
            BigDecimal a = new BigDecimal(((num.multiply(sumxy, MathContext.UNLIMITED)).subtract(sumx.multiply(sumy, MathContext.UNLIMITED)))
                    .divide(num.multiply(sumx2, MathContext.UNLIMITED).subtract(sumx.pow(2)), 40, RoundingMode.CEILING).toString());
            BigDecimal b = new BigDecimal((sumy.subtract(a.multiply(sumx, MathContext.UNLIMITED))).divide(num, 40, RoundingMode.CEILING).toString());
            LinealModel model = new LinealModel();
            model.setA(a.setScale(40, RoundingMode.CEILING));
            model.setB(b.setScale(40, RoundingMode.CEILING));
            return model;
        } catch (NullPointerException important) {
            System.out.println("massiv dannih bitiy" + important);
            return null;
        }
    }


    public static BigDecimal skdo(HashMap<BigDecimal, BigDecimal> map) throws NullPointerException {
        BigDecimal summ = new BigDecimal("0");
        BigDecimal skdo;
        BigDecimal temp = new BigDecimal("0");
        for (HashMap.Entry<BigDecimal, BigDecimal> item : map.entrySet()) {
            if (!temp.toString().equals("0")) {
                summ = summ.add(((item.getValue().subtract(temp))).pow(2, MathContext.UNLIMITED));
            }
            temp = item.getValue();
        }
        System.out.println("summ1" + summ);
        summ = summ.divide(new BigDecimal(String.valueOf(map.size() - 1)).multiply(new BigDecimal("2")), 100, RoundingMode.CEILING);
        System.out.println("summ skdo" + summ);

        skdo = sqrtBabylon(summ, 70);
        return skdo;
    }

    public static BigDecimal sqrtBabylon(BigDecimal in, int scale) {
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
