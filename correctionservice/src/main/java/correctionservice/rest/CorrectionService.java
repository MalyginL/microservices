package correctionservice.rest;

import correctionservice.calculation.Calculate;
import correctionservice.calculation.model.FilterModel;
import correctionservice.calculation.model.LinealModel;
import correctionservice.database.Dao;
import correctionservice.rest.model.RequestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;


@Service
public class CorrectionService {

    @Autowired
    Dao dao;

    @Autowired
    Calculate calculate;

    public void test(int time) {
       HashMap<BigDecimal,BigDecimal> example =  dao.call(time,8000,500,1);
      LinealModel linealModel =  calculate.calcMNC(example);
      FilterModel filter = calculate.filtersigma(linealModel,example);

      LinealModel norm = calculate.calcMNC(filter.getNormal());
        System.out.println( calculate.orch(norm.getA(),norm.getB(),time));


    }
}
