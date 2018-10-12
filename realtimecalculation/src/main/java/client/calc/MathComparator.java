package client.calc;

import client.hibernate.model.CalculateModel;
import org.springframework.cglib.beans.BeanGenerator;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;
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

   public static BigDecimal calc2Skdo(int n, BigDecimal sqrSumm){

        return sqrt(sqrSumm.
                divide((new BigDecimal(2).
                        multiply(
                                new BigDecimal(n).subtract(new BigDecimal(1))
                        )
                ),20,RoundingMode.HALF_UP),MathContext.UNLIMITED);
   }

    public static BigDecimal calcSkdo(int n, BigDecimal sqrSumm){

        return bigSqrt(sqrSumm.
                divide((new BigDecimal(2).
                        multiply(
                                new BigDecimal(n).subtract(new BigDecimal(1))
                        )
                ),20,RoundingMode.HALF_UP));
    }


    private static final BigDecimal SQRT_DIG = new BigDecimal(20);
    private static final BigDecimal SQRT_PRE = new BigDecimal(10).pow(SQRT_DIG.intValue());



    private static BigDecimal sqrtNewtonRaphson  (BigDecimal c, BigDecimal xn, BigDecimal precision){
        BigDecimal fx = xn.pow(2).add(c.negate());
        BigDecimal fpx = xn.multiply(new BigDecimal(2));
        BigDecimal xn1 = fx.divide(fpx,2*SQRT_DIG.intValue(),RoundingMode.HALF_DOWN);
        xn1 = xn.add(xn1.negate());
        BigDecimal currentSquare = xn1.pow(2);
        BigDecimal currentPrecision = currentSquare.subtract(c);
        currentPrecision = currentPrecision.abs();
        if (currentPrecision.compareTo(precision) <= -1){
            return xn1;
        }
        return sqrtNewtonRaphson(c, xn1, precision);
    }

    public static BigDecimal bigSqrt(BigDecimal c){
        return sqrtNewtonRaphson(c,new BigDecimal(1),new BigDecimal(1).divide(SQRT_PRE));
    }







}
