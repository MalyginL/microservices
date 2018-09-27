package client.calc;

import client.hibernate.model.CalculateModel;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;

@Component
public class MathComparator {

    static public BigDecimal scalePrec(final BigDecimal x, int d) {
        return x.setScale(d + x.scale());
    }

    static public BigDecimal sqrt(final BigDecimal x, final MathContext mc) {
        if (x.compareTo(BigDecimal.ZERO) < 0)
            throw new ArithmeticException("negative argument " + x.toString() + " of square root");
        if (x.abs().subtract(new BigDecimal(Math.pow(10., -mc.getPrecision()))).compareTo(BigDecimal.ZERO) < 0)
            return scalePrec(BigDecimal.ZERO, mc.getPrecision());
        BigDecimal s = new BigDecimal(Math.sqrt(x.doubleValue()), mc);
        final BigDecimal half = new BigDecimal("2");
        MathContext locmc = new MathContext(mc.getPrecision() + 2, mc.getRoundingMode());
        final double eps = Math.pow(10.0, -mc.getPrecision());
        for (; ; ) {
            if (Math.abs(BigDecimal.ONE.subtract(x.divide(s.pow(2, locmc), locmc)).doubleValue()) < eps)
                break;
            s = s.add(x.divide(s, locmc)).divide(half, locmc);
        }
        return s;
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

    public static BigDecimal calcPhase_diff(String phase, String k) {
        BigDecimal result = (new BigDecimal(phase).divide(new BigDecimal(k))).setScale(40, RoundingMode.CEILING);
        return result;
    }

    public static BigDecimal calcRelFreq_diff(String phaseCurr_diff, String phaseNext_diff, String tau_time) {
        BigDecimal result = (new BigDecimal(phaseNext_diff)
                .subtract
                        (new BigDecimal(phaseCurr_diff)))
                .divide
                        (new BigDecimal(tau_time), BigDecimal.ROUND_HALF_UP)
                .setScale(40, RoundingMode.CEILING);
        return result;
    }

    public static BigDecimal calcCurrVarRelFreq_diff(String curr, String next) {
        try {
            BigDecimal result = (new BigDecimal(next).subtract(new BigDecimal(curr)).setScale(40, RoundingMode.CEILING));
            return result;
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    public static BigDecimal skdo(List<CalculateModel> list, int period, boolean usage) throws NullPointerException {
        BigDecimal k;
        if (usage) {
            k = sqrtBabylon(new BigDecimal("2"), 70);
        } else {
            k = new BigDecimal("1");
        }

        BigDecimal summ = new BigDecimal("0");
        BigDecimal skdo;
        BigDecimal temp = new BigDecimal("0");
        for (CalculateModel item : list) {
            if (!temp.toString().equals("0")) {
                summ = summ.add(((new BigDecimal(item.getCurr_var_rel_freq_diff()).subtract(temp))).pow(2, MathContext.UNLIMITED));
            }
            temp = new BigDecimal(item.getCurr_var_rel_freq_diff());
        }
        System.out.println("summ1" + summ);
        summ = summ.divide(new BigDecimal(String.valueOf(list.size() - 1)).multiply(new BigDecimal("2")).multiply(k), 100, RoundingMode.CEILING);
        System.out.println("summ skdo" + summ);

        skdo = sqrtBabylon(summ, 70);
        return skdo;
    }





}
