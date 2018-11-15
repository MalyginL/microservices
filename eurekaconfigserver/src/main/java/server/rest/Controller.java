package server.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import server.listeners.EurekaRenewedListener;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/getservices")
public class Controller {

    @Autowired
    EurekaRenewedListener listener;

    @RequestMapping(value = "/device",method = RequestMethod.GET,produces = "application/json")
    public List<String> getDevices(){
        return listener.getDevice();
    }
}
