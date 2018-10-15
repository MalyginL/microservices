package client.hazelcast;

import com.hazelcast.core.IMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Service
public class Hazelclient {

    @Resource(name = "instance")
    IMap<String ,BigDecimal> map;

    public void put(String key, BigDecimal value){
        map.put(key, value);
    }

    public BigDecimal get(String key){
      return map.get(key);
    }
}
