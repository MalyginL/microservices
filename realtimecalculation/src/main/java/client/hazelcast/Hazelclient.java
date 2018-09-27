package client.hazelcast;

import com.hazelcast.core.IMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Map;
@Service
public class Hazelclient {

    @Autowired
    HazelcastConfig hazelcastConfig;

    @Resource(name = "instance")
    IMap<String ,BigDecimal> map;

    public void put(String key, BigDecimal value){
        map.put(key, value);
    }

    public BigDecimal get(String key){
      return map.get(key);
    }
}
