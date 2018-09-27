package client.rest;

import client.calc.MathComparator;
import client.hibernate.HibernateDao;
import client.hibernate.model.CalcSettingsModel;
import client.hibernate.model.CalculateModel;
import client.hibernate.model.DataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


public class Task implements Runnable {

    private String device;
    private String channel;

    @Autowired
    HibernateDao hib;

   public Task(String device, String channel){
       this.device=device;
       this.channel=channel;
   }
    @Override
    public void run() {
        CalcSettingsModel settings = hib.getCalcSettingForDeviceAndChannel(device, channel);

        List<DataModel> list = hib.getDataFromTime(settings.getCalc_time(), "9999999999", Integer.valueOf(channel), device);

        List<CalculateModel> calculateModel = new ArrayList<>();
        for (DataModel item : list) {



            CalculateModel model = new CalculateModel();
            model.setChannel(2);
            model.setDevice(String.valueOf(item.getDevice()));
            model.setTime(item.getTime());
            model.setPhase_diff(MathComparator.calcPhase_diff(item.getRawData(), "1000000").toString());
            calculateModel.add(model);




        }

        settings.setCalc_time(list.get(list.size() - 2).getTime());
        CalculateModel bufferForPhaseDiff = calculateModel.get(0);

        for (CalculateModel item : calculateModel.subList(1, calculateModel.size())) {
            bufferForPhaseDiff.setRel_freq_diff(MathComparator.calcRelFreq_diff(
                    bufferForPhaseDiff.getPhase_diff(),
                    item.getPhase_diff(),
                    "1").toString());
            bufferForPhaseDiff = item;
        }
        CalculateModel bufferForRelFreqDiff = calculateModel.get(1);

        for (CalculateModel item : calculateModel.subList(2, calculateModel.size())) {
            System.out.println("---------------------"+item.getRel_freq_diff());
            try {
                bufferForRelFreqDiff.setCurr_var_rel_freq_diff(
                        MathComparator.calcCurrVarRelFreq_diff(
                                bufferForRelFreqDiff.getRel_freq_diff(),
                                item.getRel_freq_diff()
                        ).toString());
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
            bufferForRelFreqDiff = item;
        }

        calculateModel.forEach(e->hib.updateCalculaton(e));
        settings.setCalc_time(calculateModel.get(calculateModel.size() - 1).getTime());
        hib.saveSettings(settings);
        System.out.println("DONE!");

    }

}
