package BDOChef.Events.Commands;

import BDOChef.BDOChef;
import BDOChef.Models.DiscordMember;
import BDOChef.Models.GameCharacter;
import BDOChef.Tables.DiscordMemberTable;
import BDOChef.Tables.GameCharacterTable;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

public class RemoveGameCharacter extends AbstractCommand {

    public static final String command = BDOChef.prefix + "removechar";

    public RemoveGameCharacter(GuildMessageReceivedEvent event, String[] args) throws Exception {
        if (args[1].equalsIgnoreCase("help")) {
            showHelp(event, command + " {name}");
        } else {
            DiscordMemberTable dmbt = new DiscordMemberTable();
            DiscordMember dm = dmbt.getMemberByDiscordId(event.getAuthor().getId());

            if (dm == null) {
                sendErrorMessage(event, "Insufficient permissions");
            } else {
                GameCharacterTable gcht = new GameCharacterTable();
                GameCharacter savedChar = gcht.getGameCharByName(args[1]);
                if (savedChar != null) {
                    savedChar.setState("inactive");
                    gcht.setGameCharacter(savedChar);
                    sendMessage(event, "Remove complete");
                }

            }
        }
    }

}
