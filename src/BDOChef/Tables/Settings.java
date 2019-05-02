package BDOChef.Tables;

import BDOChef.BDOChef;
import BDOChef.Models.Setting;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Settings {

    private Map settings;

    public Settings() {
        loadSettings();
    }

    public String getSetting(String key) {
        if(!settings.containsKey(key)) {
            return null;
        }
        return (String) settings.get(key);
    }

    private void loadSettings() {
        settings = new HashMap();
        List<Setting> lSettings =  BDOChef.jdbTemptale.findAll(Setting.class);
        for (Setting setting : lSettings) {
            settings.put(setting.getKey(), setting.getValue());
        }
    }

    public void setSetting(String key, String value) {

        Setting setting = BDOChef.jdbTemptale.findById(key, Setting.class);
        if (setting == null) {
            setting = new Setting();
            setting.setKey(key);
            setting.setValue(value);
            BDOChef.jdbTemptale.insert(setting);
        } else {
            setting.setValue(value);
            BDOChef.jdbTemptale.save(setting, Setting.class);
        }
        loadSettings();
    }
}
