package project.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class DeviceController {

    @Autowired
    DeviceService service;

    @RequestMapping(value = "/start", method = RequestMethod.POST)
    public boolean start(@RequestBody String comport) {
        System.out.println(comport);
        return service.init(comport);
    }

    @RequestMapping(value = "/addchannel", method = RequestMethod.POST)
    public void addChannel(@RequestBody int channel) {
        System.out.println(channel);
        service.addChannel(channel);
    }

    @RequestMapping(value = "/removechannel", method = RequestMethod.POST)
    public void removeChannel(@RequestBody int channel) {
        service.removeChannel(channel);
    }


}
