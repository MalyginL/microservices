package correctionservice.rest;


import correctionservice.rest.model.AnswerModel;
import correctionservice.rest.model.RequestModel;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class CorrectionController {

    @Autowired
    CorrectionService service;

    @GetMapping("/test")
    public void test(){
        service.test(1542699857);
    }

    @PostMapping("/start")
    public List<AnswerModel> start(@RequestBody RequestModel model){
       return service.start(model);

    }


}
