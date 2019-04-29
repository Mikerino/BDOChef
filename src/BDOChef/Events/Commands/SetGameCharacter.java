package BDOChef.Events.Commands;

import BDOChef.BDOChef;
import BDOChef.Models.GameCharacter;
import BDOChef.Tables.GameCharacterTable;
import BDOChef.Utils;
import BDOChef.Models.DiscordMember;
import BDOChef.Tables.DiscordMemberTable;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.util.Arrays;

public class SetGameCharacter extends AbstractCommand {

    public static final String command = BDOChef.prefix + "setchar";

    public SetGameCharacter(GuildMessageReceivedEvent event, String[] args) throws Exception {
        if (args.length == 1 || args[1].equalsIgnoreCase("help")) {
            showHelp(event, command + " {name} {AP} {AAP} {DP} {className} {lvl}");
        } else {
            DiscordMemberTable dmbt = new DiscordMemberTable();
            DiscordMember dm = dmbt.getMemberByDiscordId(event.getAuthor().getId());
            GameCharacterTable gcht = new GameCharacterTable();

            if (dm == null) {
                sendErrorMessage(event, "Insufficient permissions");
            } else if (validateInput(event, args)) {
                if (gcht.getCharCountForDiscordMemberId(dm.getId()) > 10) {
                    sendErrorMessage(event, "Max character count reached");
                } else {
                    gcht.setGameCharacter(getGameChar(args, dm));
                    sendMessage(event, "Set complete");
                }
            }
        }
    }

    private boolean validateInput(GuildMessageReceivedEvent event, String[] args) {
        if (args.length != 7) {
            showHelp(event, "Invalid format " + command + " {name} {AP} {AAP} {DP} {className} {lvl}");
            return false;
        }
        if (!Utils.isInt(args[2]) || !Utils.isInt(args[3]) || !Utils.isInt(args[4]) || !Utils.isInt(args[6])) {
            showHelp(event, "Invalid format " + command + " {name} {AP} {AAP} {DP} {className} {lvl}");
            return false;
        }
        String[] values = {"zerker", "ranger", "sorc", "tamer", "valk", "warr", "witch", "wiz", "musa", "mae", "ninja", "kuno", "dk", "striker", "mystic", "lahn", "archer"};
        if (!Arrays.asList(values).contains(args[5])) {
            showHelp(event, "Invalid format for {className}, please use one of the following: " + String.join(", ", values));
            return false;
        }
        return true;
    }

    private GameCharacter getGameChar(String[] args, DiscordMember discordMember) {
        GameCharacter gch = new GameCharacter();
        gch.setName(args[1]);
        gch.setApValue(args[2]);
        gch.setAapValue(args[3]);
        gch.setDpValue(args[4]);
        gch.setCharacterClass(args[5]);
        gch.setLevel(args[6]);
        gch.setDiscordMemberId(discordMember.getId());
        gch.setState("active");
        return gch;
    }
}
