package project.db;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import project.db.model.SendModel;

import javax.annotation.PostConstruct;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
@EnableAsync
@Transactional
public class Upload {

    @PersistenceContext
    EntityManager entityManager;

    public static List<SendModel> list = Collections.synchronizedList(new ArrayList<SendModel>());

    @Async
   // @PostConstruct
    public void upload() {
        while (true) {
            if (list.size() > 0) {
                list.forEach(this::send);
            } else {
                try {
                    Thread.currentThread().sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void send(SendModel model){
        entityManager.merge(model);
        entityManager.detach(model);
    }

}
