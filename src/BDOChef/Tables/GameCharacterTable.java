package BDOChef.Tables;

import BDOChef.BDOChef;
import BDOChef.Models.DiscordMember;
import BDOChef.Models.GameCharacter;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.Member;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class GameCharacterTable {

    public void setGameCharacter(GameCharacter gameChar) {
        gameChar.setId(Integer.toString(getNextId()));
        GameCharacter savedChar = getGameCharByName(gameChar.getName());
        if ( savedChar != null) {
            gameChar.setId(savedChar.getId());
            BDOChef.jdbTemptale.save(gameChar, GameCharacter.class);
        } else {
            BDOChef.jdbTemptale.insert(gameChar);
        }
    }

    public List<GameCharacter> getGameCharacters() {
        return BDOChef.jdbTemptale.findAll(GameCharacter.class);
    }

    public GameCharacter getGameCharByName(String name) {

        String jxQuery = String.format("/.[name='%s']", name);
        List<GameCharacter> chars = BDOChef.jdbTemptale.find(jxQuery, GameCharacter.class);
        if (chars.size() == 0) {
            return null;
        }
        return chars.get(0);
    }

    public int getNextId() {
        List<GameCharacter> gameCharacter = getGameCharacters();
        if (gameCharacter.size() == 0) {
            return 1;
        }
        GameCharacter lastGameCharacter = gameCharacter.get(gameCharacter.size()-1);
        return Integer.parseInt(lastGameCharacter.getId())+1;
    }

    public int getCharCountForDiscordMemberId(String discordMemberId) {
        String jxQuery = String.format("/.[discordMemberId='%s']", discordMemberId);
        List<GameCharacter> chars = BDOChef.jdbTemptale.find(jxQuery, GameCharacter.class);
        return chars.size();
    }

}
