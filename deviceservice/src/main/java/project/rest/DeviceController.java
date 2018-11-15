package project.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.db.model.DeviceModel;
import project.db.model.RegisterModel;

@RestController
public class DeviceController {

    @Autowired
    DeviceService service;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        return "success";
    }

    @RequestMapping(value = "/start", method = RequestMethod.GET)
    public void start() {
        service.init();
    }

    @RequestMapping(value="/connect", method = RequestMethod.POST)
    public boolean connect(@RequestBody RegisterModel model){
        System.out.println("reg");
        if(service.connect(model.getComport())) {
            System.out.println("channels" + model.getMaxchannels());

            for(int j=0;j<Integer.valueOf(model.getMaxchannels());j++){
            service.register(new DeviceModel(
                    model.getDevicename(),
                    (short) j,
                    (short) 1,
                    model.getServicename(),
                    model.getComport()
            ));}
            service.setName(model.getDevicename());
            service.init();
            System.out.println("HZHZHHDASDASKLJDASDLKASJLKDA");

            return true;
        }
        else return false;
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
        service.setName(name);
    }

    @RequestMapping(value = "/disconnect", method = RequestMethod.POST)
        public boolean disconnect(){
           return service.disconnect();
        }


}
