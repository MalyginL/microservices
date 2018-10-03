package settings.db;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import settings.db.SettingsDao;
import settings.db.model.SettingsModel;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional
@Repository
public class SettingsDaoImpl implements SettingsDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional(readOnly=true)
    public SettingsModel get(String device) {
        return entityManager.createQuery("from SettingsModel where device= :item",SettingsModel.class)
                .setParameter("item",device).getSingleResult();
    }

    @Override
    @Transactional(readOnly=true)
    public List<SettingsModel> getAll() {
        return  entityManager.createQuery("from SettingsModel", SettingsModel.class).getResultList();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void register(String com, String device) {
        entityManager.merge(new SettingsModel(device,com));
    }



}
