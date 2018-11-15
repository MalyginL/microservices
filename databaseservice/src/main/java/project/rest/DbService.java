package project.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import project.hibernate.CalcDao;
import project.hibernate.model.CalculateModel;
import project.hibernate.model.DeviceModel;
import project.hibernate.model.TasksModel;
import project.rest.model.NewTaskModel;
import project.rest.model.RequestModel;

import java.util.Collection;
import java.util.Collections;
import java.util.List;


@Service
public class DbService {

    @Autowired
    CalcDao dao;

    public List<CalculateModel> getData(RequestModel model) {
        return dao.findAllWithPeriod(model.getStartTime(), model.getEndTime(), model.getChannel(), model.getDevice());
    }

    public List<TasksModel> getCompletedTasks(){
        return dao.getCurrentTasks();
    }


    public void deleteTask(long id){
       dao.deleteById(id);
    }

    public List<String> loadDevices() {
        return dao.getDevices();
    }

    public List<Short> loadChannels(String device) {
        return dao.getChannels(device);
    }

    public boolean addTask(NewTaskModel model) {
        String serviceName =  dao.getServiceName(model.getDevice(),model.getChannel());

        return sendTask(model,serviceName);
    }

    public boolean sendTask(NewTaskModel model,String URL){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<Integer> entity = new HttpEntity<>(model.getChannel(), headers);
        HttpEntity<Boolean> respons = restTemplate.exchange("http://localhost:8762/"+URL+"/addchannel", HttpMethod.POST, entity, Boolean.class);

        //if(respons.getBody()){
        //FIX THIS!!!!!!
            dao.addTask(model.getDevice(),(short)model.getChannel(),model.getPeriod());
    //    }
        return true;
    }

}
