package client.cache;

import client.rest.model.RequestAnswer;
import client.rest.model.RequestModel;
import com.hazelcast.core.IMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class HazelcastService {

    @Resource(name = "instance")
    IMap<String, BigDecimal> map;

    public void put(String key, BigDecimal value) {
        map.put(key, value);
    }

    public BigDecimal get(String key) {
        return map.get(key);
    }

    public List<RequestAnswer> getskdo(RequestModel model) {
        List<RequestAnswer> requestAnswers = new ArrayList<>();
        BigDecimal result = new BigDecimal("0");
        int i = 1;
        while (true) {
            result = map.get(model.getDevice() + "_" + model.getChannel() + '_' + model.getPeriod() * i);
            if (result != null) {
                requestAnswers.add(new RequestAnswer(+model.getPeriod() * i, result));
                i *= 10;
            } else {
                break;
            }
        }
        return requestAnswers;
    }
}
