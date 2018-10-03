package settings.db;

import settings.db.model.SettingsModel;

import java.util.List;


public interface SettingsDao {

    SettingsModel get(String device);

   List<SettingsModel> getAll();

    void register (String com, String device);



}
