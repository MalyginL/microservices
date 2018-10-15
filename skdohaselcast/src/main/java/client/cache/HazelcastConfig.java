package client.cache;

import com.hazelcast.config.*;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
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
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Map;

@Repository
@Configuration
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

    @Bean(name = "instance")
    @Order(-10)
    public IMap hazelcastInstance(){
        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance();
        IMap<String,BigDecimal> map = hazelcastInstance.getMap("data");
        return map;
    }



}
