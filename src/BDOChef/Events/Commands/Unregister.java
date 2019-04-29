package BDOChef.Events.Commands;

import BDOChef.BDOChef;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

public class Unregister extends AbstractRegister {

    public static final String command = BDOChef.prefix + "unregister";

    public Unregister(GuildMessageReceivedEvent event, String[] args) throws Exception {
        if (!hasRole(event.getMember(), "Officer")) {
            sendErrorMessage(event, "Insufficient permissions");
        } else {
            Member member = event.getMessage().getMentionedMembers().get(0);
            registerMember(member, null, "inactive");
            event.getGuild().getController().removeRolesFromMember(member, event.getJDA().getRolesByName("Member", true)).complete();
            sendMessage(event, "Unregistration complete");
            log(event.getMember(), event.getMember().getUser(), event.getGuild().getTextChannelsByName("log", true).get(0), "User unregistered!");
        }
    }

}
