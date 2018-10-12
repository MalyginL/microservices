package project.database;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.database.model.SendModel;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
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
}
