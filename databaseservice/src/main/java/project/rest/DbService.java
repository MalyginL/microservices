package project.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import project.hibernate.CalcDao;
import project.hibernate.model.CalculateModel;
import project.hibernate.model.TasksModel;
import project.rest.model.RequestModel;

import java.util.Collection;
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
}
