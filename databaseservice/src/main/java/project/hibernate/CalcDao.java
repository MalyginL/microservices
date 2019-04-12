package project.hibernate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.hibernate.model.CalculateModel;
import project.hibernate.model.TasksModel;
import project.rest.model.TimeModel;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class CalcDao {

    public static final int[] timeperiod = {1, 10, 100, 1000, 1800, 3600, 10800, 36000, 86400};

    @PersistenceContext
    private EntityManager entityManger;

    @Deprecated
    public List<CalculateModel> findAllWithPeriod(int startTime, int endTime, short channel, String device) {
        return entityManger.createQuery("Select e from CalculateModel e where e.calc_time Between :startTime and :endTime AND e.device= :device AND e.channel=: channel ORDER BY e.calc_time  ASC", CalculateModel.class)
                .setParameter("startTime", startTime).setParameter("endTime", endTime).setParameter("device", device).setParameter("channel", channel).getResultList();

    }

    public List<TasksModel> getCurrentTasks() {
        return entityManger.createQuery("from TasksModel where status = true", TasksModel.class).getResultList();
    }

    public void deleteById(long id) {
        entityManger.createQuery("DELETE FROM TasksModel WHERE id=:id").setParameter("id", id);
    }

    public List<String> getDevices() {
        return entityManger.createQuery("Select DISTINCT e.device from DeviceModel e").getResultList();
    }

    public List<Short> getChannels(String device) {
        return entityManger.createQuery("Select e.channel from DeviceModel e where e.device= :device and e.status=1")
                .setParameter("device", device).getResultList();
    }

    public String getServiceName(String device, int channel) {
        return entityManger.createQuery("select e.servicename from DeviceModel e where e.device= :device and e.channel= :channel")
                .setParameter("device", device).setParameter("channel", (short) channel).getSingleResult().toString();
    }

    public void addTask(String device, short channel, int period) {
        entityManger.merge(new TasksModel(device, channel, period, true, (int) (System.currentTimeMillis() / 1000)));
//        entityManger.createQuery("INSERT INTO TaskModel (device, channel, period, status,starttime) VALUES (:device,:channel,:period,true,:time)")
//                .setParameter("device",device)
//                .setParameter("channel",channel)
//                .setParameter("period",period)
//                .setParameter("time", (int)(System.currentTimeMillis()/1000));

    }

    public void updateChannel(String device, short channel, short status) {
        entityManger.createQuery("update Devicemodel m set m.status = :status where m.device = :device and m.channel =:channel ")
                .setParameter("device", device)
                .setParameter("channel", channel)
                .setParameter("status", status);
    }


    public List<CalculateModel> getData(int deviceid,int starttime, int endtime ){
        return entityManger.createQuery("Select e from CalculateModel e where e.calc_time Between :starttime and :endtime ORDER BY e.calc_time  ASC")
                .setParameter("starttime",starttime)
                .setParameter("endtime",endtime)
                .getResultList();
    }

    public List<Integer> getTaskByDeviceAndChannel(String device, String channel) {
        return entityManger.createQuery("select s.starttime from TasksModel s where s.device=:device and channel=:channel")
                .setParameter("device", device)
                .setParameter("channel", Short.valueOf(channel))
                .getResultList();
    }

    //------------------------------//

    public List<TimeModel> getParams(int deviceid, int starttime, int endtime) {
        List<TimeModel> params = new ArrayList<>();
        for (int z : timeperiod) {
            TimeModel item = new TimeModel();
            item.setType(z);
            try {
                System.out.println(deviceid);
                System.out.println(starttime);
                System.out.println(endtime);
            item.setDzch((Double) (entityManger.createQuery("select avg(s.rel_freq_diff) FROM  CalculateModel s WHERE s.calc_time>:starttime AND s.calc_time<:endtime AND s.time_type=:type AND s.deviceid=:id  AND s.cur_var_rel_freq_diff!=null ")
                    .setParameter("starttime", starttime)
                    .setParameter("endtime", endtime)
                    .setParameter("id", deviceid)
                    .setParameter("type",z)
                    .getSingleResult()));

            item.setSko((BigDecimal) (entityManger.createNativeQuery("select STDDEV_SAMP(rel_freq_diff) from Production.calculate s " +
                    " WHERE s.calc_time>" +
                    starttime +
                    " AND s.calc_time<" +
                    endtime +
                    " AND s.time_type=" +
                    z +
                    " AND s.deviceid=" +
                    deviceid)
                     .getSingleResult()));
            item.setData(entityManger.createQuery("from CalculateModel s WHERE s.calc_time>:starttime AND s.calc_time<:endtime AND s.time_type=:type AND s.deviceid=:id AND s.rel_freq_diff!=null ")
                    .setParameter("starttime", starttime)
                    .setParameter("endtime", endtime)
                    .setParameter("id", deviceid)
                    .setParameter("type",z)
            .getResultList());
            System.out.println(item.getData().size());
            System.out.println(starttime + "|"+endtime+ "|" + deviceid+ "|" + z);
                System.out.println(item.getData().get(item.getData().size() - 1).getRel_freq_diff());
                System.out.println(item.getData().get(0).getRel_freq_diff());
                item.getData().forEach(e-> System.out.println(e.getRel_freq_diff()));
int k=endtime-starttime;
                System.out.println(k);
                item.setDrift((item.getData().get(item.getData().size() - 1).getRel_freq_diff().subtract(item.getData().get(0).getRel_freq_diff())).divide(new BigDecimal(String.valueOf(k)),MathContext.DECIMAL64));
            }
            catch (NullPointerException ex){

            }
            params.add(item);
        }
        return params;
    }

    public int getDeviceid(String device, short channel) {
        return 1;
      //  entityManger.createQuery()
    }
}
