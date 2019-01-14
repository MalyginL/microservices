package project.hibernate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.hibernate.model.ExternalComparsionModel;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional
@Service
public class Dao {

    @PersistenceContext
    EntityManager entityManager;

public List<ExternalComparsionModel> findByDate(int start, int end){
    return entityManager.createQuery("from ExtermalComparsionModel m where m.date between :start and :end").setParameter("start",start).setParameter("end",end).getResultList();
}

}
