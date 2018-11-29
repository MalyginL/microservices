package correctionservice.calculation;

import correctionservice.calculation.model.FilterModel;
import correctionservice.calculation.model.LinealModel;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

@Component
public class Calculate {


    public LinealModel calcMNC(HashMap<BigDecimal, BigDecimal> e) {
        return CorrectionMath.mnc(e);
    }

    public FilterModel filtersigma(LinealModel model, HashMap<BigDecimal, BigDecimal> e) {
        BigDecimal sigma = CorrectionMath.skdo(e);

        FilterModel filter = new FilterModel();
        for (HashMap.Entry<BigDecimal, BigDecimal> entry : e.entrySet()) {
            if (
                    (((entry.getValue()).
                            subtract(
                                    ((model.getA()).multiply(entry.getKey(), MathContext.UNLIMITED))
                                            .add(model.getB())
                            )).abs())
                            .compareTo(new BigDecimal("3").multiply(sigma)) == -1
                    ) {
                filter.getNormal().put(entry.getKey(), entry.getValue());
//                        System.out.println("Время " + item.getKey() + "подошло под 3 ско");
//                        System.out.println("Потому что разность = " +
//
//                                (((new BigDecimal(item.getValue())).
//                                        subtract(
//                                                (new BigDecimal(mnc.get("a")).multiply(new BigDecimal(item.getKey()), MathContext.UNLIMITED))
//                                                        .add(new BigDecimal(mnc.get("b")))
//                                        )).abs()).subtract((new BigDecimal("3").multiply(new BigDecimal(sko))))
//
//                        );
            } else {
                filter.getFake().put(entry.getKey(), entry.getValue());
            }
        }
        return filter;
    }
    public  BigDecimal correction(BigDecimal one, BigDecimal two, BigDecimal three){
        BigDecimal th = new BigDecimal("3");
        return (one.divide(th,20,RoundingMode.FLOOR))
                .add((two.divide(th,20,RoundingMode.FLOOR)))
                .add((two.divide(th,20,RoundingMode.FLOOR)))
                .abs().multiply(new BigDecimal("-1"));
    }


    public   BigDecimal orch(BigDecimal a, BigDecimal b, int time){
        return a.multiply(new BigDecimal(String.valueOf(time))).add(b);
    }


}
