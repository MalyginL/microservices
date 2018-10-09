package project.db;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.db.model.SendModel;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class SendService {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public void send(SendModel model){
        System.out.println("SENDED");
        System.out.println(entityManager.merge(model));
    }
}
