package project.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.db.model.DeviceModel;
import project.db.model.RegisterModel;

@RestController
public class DeviceController {

    @Autowired
    DeviceService service;

    @RequestMapping(value = "/start", method = RequestMethod.GET)
    public void start() {
        service.init();
    }

    @RequestMapping(value = "/connect", method = RequestMethod.POST)
    public boolean connect(@RequestBody RegisterModel model) {
        System.out.println("reg");
        if (service.connect(model.getComport())) {
            System.out.println("channels" + model.getMaxchannels());
            for (short j = 0; j < Short.valueOf(model.getMaxchannels()); j++) {
                service.register(
                        new DeviceModel(model.getDevicename(), model.getComport(), j));
            }
            service.setName(Integer.valueOf(model.getDevicename()));
            service.init();

            return true;
        } else return false;
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


    @RequestMapping(value = "/setname", method = RequestMethod.POST)
    public void setName(@RequestBody String name) {
        service.setName(Integer.valueOf(name));
    }

    @RequestMapping(value = "/disconnect", method = RequestMethod.POST)
    public boolean disconnect() {
        return service.disconnect();
    }


}
