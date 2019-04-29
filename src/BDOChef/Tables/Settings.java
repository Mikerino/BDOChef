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

    public String getSetting(String key) throws Exception {
        String value  = settings.get(key).toString();
        if (value == null) {
            throw new Exception("Key doesnt exists.");
        }
        return value;
    }

    private void loadSettings() {
        settings = new HashMap();
        List<Setting> lSettings =  BDOChef.jdbTemptale.findAll(Setting.class);
        for (Setting setting : lSettings) {
            settings.put(setting.getValue(), setting.getKey());
        }
    }

    public void setSetting(String key, String value) {
        if (settingExists(key)) {
            Setting setting = getSavedSetting(key);
            setting.setValue(value);
            BDOChef.jdbTemptale.save(setting, Setting.class);
        } else {
            Setting setting = new Setting();
            setting.setId(Integer.toString(getNextId()));
            setting.setKey(key);
            setting.setValue(value);
        }
        loadSettings();
    }

    private boolean settingExists(String key) {
        String jxQuery = String.format("/.[key='%s']", key);
        List<Setting> settings = BDOChef.jdbTemptale.find(jxQuery, Setting.class);
        if (settings.size() != 0) {
            return true;
        }
        return false;
    }

    private int getNextId() {
        List<Setting> lSetting = BDOChef.jdbTemptale.findAll(Setting.class);
        if (lSetting.size() == 0) {
            return 1;
        }
        Setting lastSetting = lSetting.get(lSetting.size()-1);
        return Integer.parseInt(lastSetting.getId())+1;
    }

    private Setting getSavedSetting(String key) {
        String jxQuery = String.format("/.[key='%s']", key);
        List<Setting> lSetting = BDOChef.jdbTemptale.find(jxQuery, Setting.class);
        if (lSetting.size() > 0)
            return lSetting.get(0);
        return null;
    }
}
