package server.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.listeners.EurekaRenewedListener;

import java.util.Collection;

@RestController
@RequestMapping("/getservices")
public class Controller {

    @Autowired
    EurekaRenewedListener listener;

    @RequestMapping("/device")
    public ResponseEntity<Collection<String>> getDevices(){
        return new ResponseEntity<>(listener.getDevice(),HttpStatus.OK);
    }
}
