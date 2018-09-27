package client.cache;

import com.hazelcast.config.*;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.Arrays;
import java.util.Map;

@Configuration
@EnableCaching
public class HazelcastConfig {


    @Bean
    @Order(10)
    public Config hazelConfig() {
        Config config = new Config();
        NetworkConfig networkConfig = config.getNetworkConfig();
        networkConfig.setPortAutoIncrement(true);
        networkConfig.setPortCount(20);
        networkConfig.setPort(5701);
        JoinConfig join = networkConfig.getJoin();
        join.getMulticastConfig().setEnabled(false);
        join.getTcpIpConfig().setEnabled(true).addMember("leonid").addMember("localhost");
        return config;
    }

    @Bean
    @Order(-10)
    public HazelcastInstance hazelcastInstance(){
        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance();
        Map<String, String> map = hazelcastInstance.getMap("data");
        IdGenerator idGenerator = hazelcastInstance.getIdGenerator("newid");
        for (int i = 0; i < 10; i++) {
            map.put(String.valueOf(i+"asdasdasda"), "message" + 2);
        }

        return hazelcastInstance;
    }



}
