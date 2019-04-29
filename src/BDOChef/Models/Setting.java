package BDOChef.Models;

import io.jsondb.annotation.Document;
import io.jsondb.annotation.Id;

@Document(collection = "Settings", schemaVersion= "1.0")
public class Setting {
    @Id
    private String Id;
    private String key;
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }
}
