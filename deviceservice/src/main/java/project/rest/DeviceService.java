package project.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import project.db.Upload;
import project.jssc.ByteParse;
import project.jssc.JsscManagement;

@org.springframework.stereotype.Service
public class DeviceService {

    @Autowired
    JsscManagement jssc;

    @Autowired
    ByteParse byteParse;

    @Autowired
    Upload upload;

    public void addChannel(int channel){
        jssc.startChannel(String.valueOf(channel));
    }
    public void removeChannel(int channel){
        jssc.stopChannel(String.valueOf(channel));
    }

    public boolean init(String comport){
        byteParse.run(comport);
        upload.upload();
       return jssc.configure(comport);
    }


}
