package BDOChef.Events.Commands;

import BDOChef.BDOChef;
import BDOChef.Models.DiscordMember;
import BDOChef.Tables.DiscordMemberTable;
import BDOChef.Utils;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Members {

    public static final String command = BDOChef.prefix + "members";

    public Members(GuildMessageReceivedEvent event, String[] args) {
        DiscordMemberTable dmbt = new DiscordMemberTable();
        List<DiscordMember> members = dmbt.getActiveMembers();

        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Members");
        builder.setColor(Color.decode("#FFFF00"));
        builder.setDescription(getMembersTable(members));
        event.getChannel().sendMessage(builder.build()).queue();
    }

    private String getMembersTable(List<DiscordMember> members) {
        try {
            SimpleDateFormat myFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
            Date date1 = new Date();
            List<List<String>> rows = new ArrayList<>();
            List<String> headers = Arrays.asList("Family name", "Member since", "No. of days");
            rows.add(headers);
            for (int i = 0; i < members.size(); i++) {
                DiscordMember member = members.get(i);
                Date date2 = myFormat.parse(member.getCreated());
                long diff = date1.getTime() - date2.getTime();
                String days = Long.toString(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
                rows.add(Arrays.asList(member.getFamName(), member.getCreated(), days));
            }
            return "```" + Utils.formatAsTable(rows) + "```";
        } catch (Exception e) {
            return "```" + e.getMessage() + "```";
        }

    }
}
