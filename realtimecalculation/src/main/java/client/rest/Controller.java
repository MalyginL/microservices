package client.rest;

import client.hazelcast.Hazelclient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    Service service;

    @Autowired
    Hazelclient hazelclient;

    @RequestMapping("/start/{device}/{channel}")
    public void init(@PathVariable String device, @PathVariable String channel) {
        service.init(device,channel,device+channel);
    }

    @RequestMapping("/stop/{device}/{channel}")
    public void stop(@PathVariable String device,@PathVariable  String channel) {
        service.cancel(device+channel);
    }


}
