package correctionservice.rest;

import correctionservice.calculation.Calculate;
import correctionservice.calculation.model.FilterModel;
import correctionservice.calculation.model.LinealModel;
import correctionservice.database.Dao;
import correctionservice.rest.model.AnswerModel;
import correctionservice.rest.model.RequestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class CorrectionService {

    @Autowired
    Dao dao;

    @Autowired
    Calculate calculate;

    public void test(int time) {
        HashMap<BigDecimal, BigDecimal> example = dao.call(time, 86400, 3600, 4);
        LinealModel linealModel = calculate.calcMNC(example);
        FilterModel filter = calculate.filtersigma(linealModel, example);

        LinealModel norm = calculate.calcMNC(filter.getNormal());
        System.out.println(calculate.orch(norm.getA(), norm.getB(), time));


    }

    public List<AnswerModel> start(RequestModel model) {
        List<AnswerModel> result = new ArrayList<>();
        int time = (int) System.currentTimeMillis() / 100;
        time = 1542699857;
        Date d = new Date((long)time*100);
        SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dt1.format(time);
        d.toString();


        for (int channel : model.getChannels()) {
            AnswerModel rez = new AnswerModel();
            rez.setChannel(channel);
            rez.setCommand_time(dt1.format(time));
            rez.setMeasurment_period(model.getPeriod());
            rez.setMeasurment_time(model.getTime());
            System.out.println(channel);
            System.out.println(Integer.valueOf(model.getTime()) * 60);
            System.out.println(Integer.valueOf(model.getPeriod()) * 3600);
            System.out.println(time);
            HashMap<BigDecimal, BigDecimal> example = dao.call(time, Integer.valueOf(model.getPeriod()) * 3600, Integer.valueOf(model.getTime()) * 60, channel);
            LinealModel linealModel = calculate.calcMNC(example);
            rez.setFormula("a=" + linealModel.getA() + "; b=" + linealModel.getB());
            FilterModel filter = calculate.filtersigma(linealModel, example);
            LinealModel norm = calculate.calcMNC(filter.getNormal());
            rez.setRfd(calculate.orch(norm.getA(), norm.getB(), time));
            List<AnswerModel.AngTable> table = new ArrayList<>();
            Iterator it = example.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                System.out.println(((BigDecimal)pair.getKey()).multiply(new BigDecimal("100")).toBigInteger());
                table.add(new AnswerModel.AngTable(dt1.format(((BigDecimal)pair.getKey()).multiply(new BigDecimal("100")).toBigInteger()),(BigDecimal)pair.getValue(),linealModel.getA().multiply((BigDecimal) pair.getKey()).add(linealModel.getB())));
             //   System.out.println(pair.getKey() + " = " + pair.getValue());
            }
            rez.setGraphData(table);
            result.add(rez);
        }
        return result;

    }
}
