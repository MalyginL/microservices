package client.hibernate;

import client.hibernate.model.CalculateModel;
import client.hibernate.model.TaskModel;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@Transactional
public class HibernateDao {

    @PersistenceContext
    private EntityManager entityManger;



//    @Transactional(readOnly=true)
//    public TaskModel getStartTime(String device, int channel) {
//        return entityManger.createQuery("from TaskModel where device= :device and channel= :channel and status=true",TaskModel.class)
//                .setParameter("device",device).setParameter("channel",channel).getSingleResult();
//    }

    @Transactional(readOnly=true)
    public TaskModel getStartTime(String device, int channel) {
        System.out.println("here");
        return entityManger.createQuery("from TaskModel e where e.id=2",TaskModel.class)
                .getSingleResult();
    }

    @Transactional(readOnly=true)
    public List<CalculateModel> getDataFromTime(int time1,short channel, String device) {
        return entityManger.createQuery("from CalculateModel e where (e.calc_time>:start  and e.device=:device and channel=:channel and e.curr_var_rel_freq_diff IS NOT NULL)",CalculateModel.class)
                .setParameter("start",time1)
                .setParameter("device",device)
                .setParameter("channel",channel)
                .getResultList();
    }




}
