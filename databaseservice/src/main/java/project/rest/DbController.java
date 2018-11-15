package project.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.hibernate.model.CalculateModel;
import project.hibernate.model.DeviceModel;
import project.hibernate.model.TasksModel;
import project.rest.model.NewTaskModel;
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

    @RequestMapping(value = "/getcurrenttasks")
    public ResponseEntity<Collection<TasksModel>> getTasks (){
        return  new ResponseEntity<>(service.getCompletedTasks(),HttpStatus.OK);
    }

    @RequestMapping(value = "/deletetask/{id}")
    public ResponseEntity<Collection<TasksModel>> deleteTask(@PathVariable long id){
        return  new ResponseEntity<>(service.getCompletedTasks(),HttpStatus.OK);
    }
    @RequestMapping(value = "/loaddevices", method = RequestMethod.GET)
    public ResponseEntity<Collection<String>> loadDevices(){
        return  new ResponseEntity<>(service.loadDevices(),HttpStatus.OK);
    }

    @RequestMapping(value = "/loadchannels/{device}", method = RequestMethod.GET)
    public ResponseEntity<Collection<Short>> loadDevices(@PathVariable String device){
        return  new ResponseEntity<>(service.loadChannels(device),HttpStatus.OK);
    }

    @RequestMapping(value = "/startTask", method = RequestMethod.POST)
    public ResponseEntity<Boolean> startTask(@RequestBody NewTaskModel model){
        return  new ResponseEntity<>(service.addTask(model),HttpStatus.OK);
    }


}
