package BDOChef.Events.Commands;

import BDOChef.BDOChef;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

public class Register extends AbstractRegister {

    public static final String command = BDOChef.prefix + "register";

    public Register(GuildMessageReceivedEvent event, String[] args) throws Exception {
        if (!hasRole(event.getMember(), "Officer")) {
            sendErrorMessage(event, "Insufficient permissions");
        } else {
            if (args.length == 1 || args[1].equalsIgnoreCase("help") || args.length < 5) {
                showHelp(event, command + " @Mention as {FamilyName} {PreferredName}");
            } else {
                Member member = event.getMessage().getMentionedMembers().get(0);
                if (member == event.getMember()) {
                    sendErrorMessage(event, "Cannot change your own state");
                } else {
                    String nickName = "[" + args[3] + "] " + args[4];
                    registerMember(member, args[3], "active");
                    event.getGuild().getController().setNickname(member, nickName).complete();
                    event.getGuild().getController().addRolesToMember(member, event.getJDA().getRolesByName("Member", true)).complete();
                    sendMessage(event, "Registration complete");
                    log(event.getMember(), event.getMember().getUser(), event.getGuild().getTextChannelsByName("log", true).get(0), "User: " + nickName + " registered!");
                }
            }
        }
    }
}
