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
import project.rest.model.TimeModel;

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

    @RequestMapping(value = "/getTask/{device}/{channel}", method = RequestMethod.GET)
    public ResponseEntity<Collection<Integer>> startTask(@PathVariable String device,@PathVariable String channel){
        return  new ResponseEntity<>(service.getTasks(device,channel),HttpStatus.OK);
    }

    @RequestMapping(value = "/getstat/{deviceid}/{starttime}/{endtime}", method = RequestMethod.GET)
    public ResponseEntity<Collection<TimeModel>> startTask(@PathVariable int deviceid, @PathVariable int starttime, @PathVariable int endtime){
        return  new ResponseEntity<>(service.getResult(deviceid,starttime,endtime),HttpStatus.OK);
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ResponseEntity<String> startTask(){
        return  new ResponseEntity<>("test",HttpStatus.OK);
    }

}
