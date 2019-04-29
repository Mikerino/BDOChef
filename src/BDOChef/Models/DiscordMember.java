package BDOChef.Models;

import io.jsondb.annotation.Document;
import io.jsondb.annotation.Id;

@Document(collection = "DiscordMembers", schemaVersion = "1.0")
public class DiscordMember {
    @Id
    private String id;
    private String name;
    private String discordTag;
    private String famName;
    private String created;
    private String state;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamName() { return famName; }

    public void setFamName(String famName) { this.famName = famName; }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getDiscordTag() {
        return discordTag;
    }

    public void setDiscordTag(String discordTag) {
        this.discordTag = discordTag;
    }

    public String getState() { return state; }

    public void setState(String state) { this.state = state; }
}
