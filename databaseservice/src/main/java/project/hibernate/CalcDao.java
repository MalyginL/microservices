package project.hibernate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.hibernate.model.CalculateModel;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional
@Service
public class CalcDao {

    @PersistenceContext
    private EntityManager entityManger;


    public List<CalculateModel> findAllWithPeriod(int startTime, int endTime, short channel, String device) {
        return entityManger.createQuery("Select e from CalculateModel e where e.calc_time Between :startTime and :endTime AND e.device= :device AND e.channel=: channel ORDER BY e.calc_time  ASC", CalculateModel.class)
                .setParameter("startTime", startTime).setParameter("endTime", endTime).setParameter("device", device).setParameter("channel", channel).getResultList();

    }

}
