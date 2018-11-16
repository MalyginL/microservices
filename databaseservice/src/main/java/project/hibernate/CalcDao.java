package project.hibernate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.hibernate.model.CalculateModel;
import project.hibernate.model.DeviceModel;
import project.hibernate.model.TasksModel;

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

    public List<TasksModel> getCurrentTasks(){
        return entityManger.createQuery("from TasksModel where status = true",TasksModel.class).getResultList();
    }

    public void deleteById(long id) {
        entityManger.createQuery("DELETE FROM TasksModel WHERE id=:id").setParameter("id",id);
    }

    public List<String> getDevices() {
       return entityManger.createQuery("Select DISTINCT e.device  from DeviceModel e").getResultList();
    }

    public List<Short> getChannels(String device) {
        return entityManger.createQuery("Select e.channel from DeviceModel e where e.device= :device and e.status=1")
                .setParameter("device",device).getResultList();
    }

    public String getServiceName(String device, int channel) {
        return  entityManger.createQuery("select e.servicename from DeviceModel e where e.device= :device and e.channel= :channel")
                .setParameter("device",device).setParameter("channel",(short    )channel).getSingleResult().toString();
    }

    public void addTask(String device, short channel, int period){
        entityManger.merge(new TasksModel(device,channel,period,true,(int)(System.currentTimeMillis()/1000)));
//        entityManger.createQuery("INSERT INTO TaskModel (device, channel, period, status,starttime) VALUES (:device,:channel,:period,true,:time)")
//                .setParameter("device",device)
//                .setParameter("channel",channel)
//                .setParameter("period",period)
//                .setParameter("time", (int)(System.currentTimeMillis()/1000));

    }

    public void updateChannel(String device, short channel,short status){
        entityManger.createQuery("update Devicemodel m set m.status = :status where m.device = :device and m.channel =:channel")
                .setParameter("device",device)
                .setParameter("channel",channel)
                .setParameter("status",status);
    }

    public List<Integer> getTaskByDeviceAndChannel(String device, String channel) {
        return entityManger.createQuery("select s.starttime from TasksModel s where s.device=:device and channel=:channel")
                .setParameter("device",device)
                .setParameter("channel",Short.valueOf(channel))
                .getResultList();
    }
}
