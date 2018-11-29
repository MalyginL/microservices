package correctionservice.rest;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class CorrectionController {

    @Autowired
    CorrectionService service;

    @GetMapping("/test")
    public void test(){
        service.test(1542699652);
    }




}
