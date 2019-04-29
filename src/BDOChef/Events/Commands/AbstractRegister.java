package BDOChef.Events.Commands;

import BDOChef.Models.DiscordMember;
import BDOChef.Tables.DiscordMemberTable;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class AbstractRegister extends AbstractCommand {

    protected void registerMember(Member member, String famName, String state) {
        DiscordMemberTable dmbt = new DiscordMemberTable();
        DiscordMember dm = new DiscordMember();
        dm.setId(member.getUser().getId());
        dm.setName(member.getNickname());
        dm.setDiscordTag(member.getAsMention());
        dm.setState(state);
        if (famName != null) {
            dm.setFamName(famName);
        }
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        Date date = new Date();
        dm.setCreated(dateFormat.format(date));
        dmbt.saveMember(dm);
    }
}
