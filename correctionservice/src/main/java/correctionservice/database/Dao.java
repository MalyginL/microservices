package correctionservice.database;

import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@Repository
public class Dao {

    @PersistenceContext
    EntityManager em;

    public HashMap<BigDecimal, BigDecimal> call(int starttime, int timelength, int timeperiod, int channel) {
        HashMap<BigDecimal, BigDecimal> resultMap = new HashMap<>();
        StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("getdata");
        storedProcedure.registerStoredProcedureParameter(0, Integer.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter(3, Integer.class, ParameterMode.IN);
        storedProcedure.setParameter(0, starttime);
        storedProcedure.setParameter(1, timelength);
        storedProcedure.setParameter(2, timeperiod);
        storedProcedure.setParameter(3, channel);
        storedProcedure.execute();
        List<Object> list = storedProcedure.getResultList();

                list.forEach(e -> {try {
            resultMap.put((BigDecimal) Array.get(e, 1), new BigDecimal(Array.get(e, 0).toString()));
        }catch (NullPointerException ex) {
                }

        });
        for (HashMap.Entry entry : resultMap.entrySet()) {
            System.out.println(entry.getKey() + ", " + entry.getValue());
        }
        return resultMap;
    }



}
