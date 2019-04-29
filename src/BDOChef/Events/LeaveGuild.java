package BDOChef.Events;

import net.dv8tion.jda.core.events.guild.member.GuildMemberLeaveEvent;

public class LeaveGuild extends AbstractEvent {

    public void onGuildMemberLeave(GuildMemberLeaveEvent event) {
        if (event.getUser().isBot())
            return;
        log(event.getMember(), event.getUser(), event.getGuild().getTextChannelsByName("log", true).get(0), "Member left");
    }


}
