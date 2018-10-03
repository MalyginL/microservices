package settings.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import settings.db.SettingsDaoImpl;
import settings.db.model.SettingsModel;

import java.util.List;

@Service
public class SettingsService {

    @Autowired
    SettingsDaoImpl dao;

    public String getComPort(String device){
       return dao.get(device).getComport();
    }

    public List<SettingsModel> getAll(){
        return dao.getAll();
    }

    public void register(String com, String device){
        dao.register(com, device);
    }

}
