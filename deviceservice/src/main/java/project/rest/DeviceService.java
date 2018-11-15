package project.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import project.db.SendService;
import project.db.Upload;
import project.db.model.DeviceModel;
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

    @Autowired
    SendService send;

    public void addChannel(int channel){
        jssc.startChannel(String.valueOf(channel));
    }
    public void removeChannel(int channel){
        jssc.stopChannel(String.valueOf(channel));
    }

    public void init(){
        byteParse.run();
        upload.upload();
        jssc.configure();
    }
    public void setName(String name){
        byteParse.setDeviceName(name);
    }


    public boolean connect(String comport) {
       return jssc.connect(comport);
    }

    public void register(DeviceModel model){
        System.out.println(model.getChannel());
        System.out.println(model.getComport());
        System.out.println(model.getDevice());
        System.out.println(model.getServicename());
        System.out.println(model.getStatus());
        send.saveDevice(model);
    }

    public boolean disconnect() {
       return jssc.disconnect();
    }
}
