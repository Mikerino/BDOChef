package BDOChef.Models;

import io.jsondb.annotation.Document;
import io.jsondb.annotation.Id;

@Document(collection = "NWEvent", schemaVersion = "1.0")
public class NWEvent {
    @Id
    private String id;
    private String date;
    private String time;
    private String node;
    private String state;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
