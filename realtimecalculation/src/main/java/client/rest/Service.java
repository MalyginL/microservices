package client.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import javax.annotation.Resource;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

@org.springframework.stereotype.Service
public class Service {

    @Autowired
    Task task;

    @Bean
    public ConcurrentHashMap<String, ScheduledFuture> map() {
        return new ConcurrentHashMap<String, ScheduledFuture>();
    }

    @Resource
    ConcurrentHashMap<String, ScheduledFuture> map;

    public void init(String device, int channel, String name) {
        calculateStart(device, channel, name);
    }

    public void cancel(String name) {
        calculateStop(name);
    }

    @Bean
    ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        return new ThreadPoolTaskScheduler();
    }

    @Autowired
    ThreadPoolTaskScheduler ThreadPoolTaskScheduler;

    private void calculateStart(String device, int channel, String name) {
       task.run(device,channel);
     //   ScheduledFuture task = ThreadPoolTaskScheduler.scheduleAtFixedRate(new Task(device,channel),1000);
      //  map.put(name, task);
    }

    private void calculateStop(String name) {
        map.get(name).cancel(false);
    }


}
