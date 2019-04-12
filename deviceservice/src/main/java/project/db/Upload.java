package project.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.UnexpectedRollbackException;
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
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
@EnableAsync
public class
Upload {

  @Autowired
  SendService service;

    public static ConcurrentLinkedQueue<SendModel> queue = new ConcurrentLinkedQueue<>();
  //  public static List<SendModel> list = Collections.synchronizedList(new ArrayList<SendModel>());

    @Async
   // @PostConstruct
    public void upload() {
        Thread.currentThread().setName("upload");
        while (true) {
            try {
                while (!queue.isEmpty()) {
                    service.send(queue.poll());
                }

                Thread.currentThread().sleep(900);
            }
            catch (UnexpectedRollbackException  ex){
                System.out.println("non-uniq");
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
//            if (list.size() > 0) {
//                list.forEach(e->service.send(e));
//                list.clear();

//            } else {
//                try {
//                    Thread.currentThread().sleep(500);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
        }
    }



}
