package client.rest;

import client.cache.HazelcastService;
import client.rest.model.RequestAnswer;
import client.rest.model.RequestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Collection;

@RestController
public class Controller {

    @Autowired
    HazelcastService service;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        return "success";
    }

    @RequestMapping(value = "/get/{key}", method = RequestMethod.GET)
    public BigDecimal get(@PathVariable String key) {
    return service.get(key);
    }

    @RequestMapping(value = "/geskdo", method = RequestMethod.POST)
    public ResponseEntity<Collection<RequestAnswer>> getskdo (@RequestBody RequestModel model) {
        return new ResponseEntity<>(service.getskdo(model), HttpStatus.OK);
    }
}
