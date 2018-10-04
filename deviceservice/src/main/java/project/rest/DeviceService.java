package project.rest;

import org.springframework.beans.factory.annotation.Autowired;
import project.jssc.JsscManagement;

@org.springframework.stereotype.Service
public class DeviceService {

    @Autowired
    JsscManagement jssc;

    public void addChannel(int channel){
        jssc.startChannel(String.valueOf(channel));
    }
    public void removeChannel(int channel){
        jssc.stopChannel(String.valueOf(channel));
    }

    public boolean init(String comport){
       return jssc.configure(comport);
    }


}
