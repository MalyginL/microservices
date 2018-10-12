package project.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import project.database.model.SendModel;

import java.math.BigDecimal;


@Service
@EnableScheduling
public class Upload {

  @Autowired
  SendService service;

  int time=	(int )System.currentTimeMillis()/1000;
    @Async
   @Scheduled(fixedDelay=1000)
    public void upload() {

     service.send(new SendModel("test",Short.valueOf("1"),new BigDecimal(Math.random()),time++));
    }
}
