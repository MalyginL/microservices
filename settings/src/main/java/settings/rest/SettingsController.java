package settings.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import settings.db.model.SettingsModel;

import java.util.List;

@RestController
public class SettingsController {

    @Autowired
    SettingsService  service;

    @RequestMapping(value = "/getcom/{device}", method = RequestMethod.GET)
    public String getCom(@PathVariable String device) {
       return service.getComPort(device);
    }

    @RequestMapping(value = "/getall", method = RequestMethod.GET)
    public List<SettingsModel> getAll() {
        return service.getAll();
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        return "success";
    }

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public void register(@RequestBody SettingsModel model){
        service.register(model.getComport(), model.getDevice());
    }


}
