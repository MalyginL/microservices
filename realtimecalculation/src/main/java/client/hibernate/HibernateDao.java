package client.hibernate;

import client.hibernate.model.CalcSettingsModel;
import client.hibernate.model.CalculateModel;
import client.hibernate.model.DataModel;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

@Repository
@Transactional
public class HibernateDao {

    @PersistenceContext
    private EntityManager entityManger;


    @Transactional(readOnly=true)
    public CalcSettingsModel getCalcSettingForDeviceAndChannel(String device, String channel) {
        return entityManger.createQuery("from CalcSettingsModel where calc_device= :device and calc_channel= :channel",CalcSettingsModel.class)
              .setParameter("device",device).setParameter("channel",channel).getSingleResult();
    }

    @Transactional(propagation = REQUIRES_NEW)
    public CalcSettingsModel saveSettings(CalcSettingsModel item){
        return entityManger.merge(item);
    }

    @Transactional(readOnly=true)
    public List<DataModel> getData() {
        entityManger.flush();
        entityManger.clear();
        return entityManger.createQuery("from DataModel",DataModel.class).getResultList();
    }

    @Transactional(readOnly=true)
    public List<DataModel> getDataFromTime(String time1,String time2,int channel, String device) {
        entityManger.flush();
        entityManger.clear();
        return entityManger.createQuery("from DataModel e where (e.time>=:start and e.time<:endo and e.device=:device)",DataModel.class)
                .setParameter("start",time1)
                .setParameter("endo",time2)
                .setParameter("device",Integer.valueOf(device)).getResultList();
    }

    @Transactional(propagation=REQUIRES_NEW)
    public void updateCalculaton(CalculateModel list){
        entityManger.merge(list);
    }

    @Transactional(propagation=REQUIRES_NEW)
    public void save(DataModel model){
        entityManger.merge(model);
    }



}
