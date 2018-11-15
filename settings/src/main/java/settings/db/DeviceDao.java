package settings.db;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import settings.db.model.DeviceModel;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Transactional
@Repository
public class DeviceDao {

    @PersistenceContext
    EntityManager manager;

    public void register (DeviceModel model,int channels) {
        for (int i = 1; i <= channels; i++) {
            manager.createQuery("INSERT INTO DeviceModel (device, channel, status, servicename)  VALUES  ( :device, :channel, :status, :servicename) ON DUPLICATE KEY UPDATE status= VALUES(status)")
                    .setParameter("device", model.getDevice())
                    .setParameter("channel", i)
                    .setParameter("status", model.getStatus())
                    .setParameter("servicename", model.getServicename());
        }
    }


}
