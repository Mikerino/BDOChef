package BDOChef.Models;

import io.jsondb.annotation.Document;
import io.jsondb.annotation.Id;

@Document(collection = "GameCharacters", schemaVersion= "1.0")
public class GameCharacter {
    @Id
    private String id;
    private String name;
    private String discordMemberId;
    private String apValue;
    private String aapValue;
    private String dpValue;
    private String characterClass;
    private String level;
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

    public String getDiscordMemberId() {
        return discordMemberId;
    }

    public void setDiscordMemberId(String discordMemberId) {
        this.discordMemberId = discordMemberId;
    }

    public String getApValue() {
        return apValue;
    }

    public void setApValue(String apValue) {
        this.apValue = apValue;
    }

    public String getAapValue() {
        return aapValue;
    }

    public void setAapValue(String aapValue) {
        this.aapValue = aapValue;
    }

    public String getDpValue() {
        return dpValue;
    }

    public void setDpValue(String dpValue) {
        this.dpValue = dpValue;
    }

    public String getCharacterClass() {
        return characterClass;
    }

    public void setCharacterClass(String characterClass) {
        this.characterClass = characterClass;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
