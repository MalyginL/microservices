package project.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.hibernate.model.CalculateModel;
import project.rest.model.RequestModel;

import java.util.Collection;


@RestController
public class DbController {

    @Autowired
    DbService service;

    @RequestMapping("/getdata")
    public ResponseEntity<Collection<CalculateModel>> getData(@RequestBody RequestModel model) {
        return new ResponseEntity<>(service.getData(model), HttpStatus.OK);
    }
}
