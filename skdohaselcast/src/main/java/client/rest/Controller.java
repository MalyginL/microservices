package client.rest;


import client.cache.skdoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {




    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        return "success";
    }

    @RequestMapping(value = "/clear", method = RequestMethod.GET)
    public String clean() {

        return "cache clean";
    }

    @RequestMapping(value = "/put/{device}/{value}", method = RequestMethod.GET)
    public String put(@PathVariable String device, @PathVariable String value) {
       return null;
    }

    @RequestMapping(value = "/get/{key}", method = RequestMethod.GET)
    public String get(@PathVariable String key) {
    return null;
    }


}
