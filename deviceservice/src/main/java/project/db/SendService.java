package project.db;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.db.model.DeviceModel;
import project.db.model.DeviceStatus;
import project.db.model.SendModel;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.SQLException;

@Service
@Transactional
public class SendService {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public void send(SendModel model){
        try {
            entityManager.merge(model);
        }
        catch (Exception e){
            System.out.println("non-uniq row");
        }
    }
    @Transactional
    public void saveDevice(DeviceModel model){
        DeviceModel managedEntity  = entityManager.merge(model);
        System.out.println("SAVED");
    }
}
