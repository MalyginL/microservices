package client.rest;

import client.calc.MathComparator;
import client.hazelcast.Hazelclient;
import client.hibernate.HibernateDao;
import client.hibernate.model.CalculateModel;
import client.hibernate.model.TaskModel;
import client.rest.model.SkdoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class Task {

    TaskModel taskModel;
    static final int range = 10000;
    ConcurrentHashMap<String, SkdoModel> temp = new ConcurrentHashMap<>();

    @Autowired
    HibernateDao hib;

    @Autowired
    Hazelclient hazelclient;


    public void run(String device, int channel) {

            taskModel = hib.getStartTime(device, channel);
            int time = taskModel.getStarttime();
            int period = taskModel.getPeriod();
            BigDecimal summ;
            int n = 1;

            init(period);

            int multy = period * 10;

            while (true) {

                List<CalculateModel> list = hib.getDataFromTime(time, Integer.valueOf(channel).shortValue(), device);
                for (CalculateModel model : list) {

                    SkdoModel rawmodel = temp.get(taskModel.getDevice() + "_" + taskModel.getChannel() + "_" + period);

                    summ = rawmodel.getSumm()
                            .add((model.getCurr_var_rel_freq_diff()).pow(2));
                    rawmodel.setSumm(summ);
                    try {


                    hazelclient.put(taskModel.getDevice() + "_" + taskModel.getChannel() + "_" + period, MathComparator.calcSkdo(n, summ));
                        }
                    catch (ArithmeticException ex){ex.printStackTrace();}
                    while (multy < range) {
                        SkdoModel skdomodel = temp.get(taskModel.getDevice() + "_" + taskModel.getChannel() + "_" + multy);

                        if (n % multy == 0) {
                            try {
                                summ = skdomodel.getSumm().add((skdomodel.getTemp().divide(new BigDecimal(multy))).pow(2));
                                skdomodel.setSumm(summ);
                                System.out.println(hazelclient.get(taskModel.getDevice() + "_" + taskModel.getChannel() + "_" + multy));
                            } catch (NullPointerException ex){
                                ex.printStackTrace();
                            }


                            hazelclient.put(taskModel.getDevice() + "_" + taskModel.getChannel() + "_" + multy, MathComparator.calcSkdo(n, summ));

                        } else {
                          BigDecimal temp = skdomodel.getTemp();
                            skdomodel.setTemp(temp.
                                    add(model.getCurr_var_rel_freq_diff()));
                        }
                        multy = multy * 10;
                    }
                    n++;
                    multy = period * 10;
                    time = model.getCalc_time();
                }
            }
    }


    public void init(int period) {
        while (period < range) {
            System.out.println("added "+taskModel.getDevice() + "_" + taskModel.getChannel() + "_" + period);
            temp.put(taskModel.getDevice() + "_" + taskModel.getChannel() + "_" + period, new SkdoModel(new BigDecimal(0), new BigDecimal(0)));
            period=period*10;
        }
    }

}
