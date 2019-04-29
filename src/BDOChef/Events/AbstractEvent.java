package BDOChef.Events;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class AbstractEvent extends ListenerAdapter {

    protected void log(Member member, User user, TextChannel logChannel, String message) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        SimpleDateFormat stf = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Log");
        builder.setColor(Color.green);

        if (member.getNickname() != null) {
            builder.setAuthor(member.getNickname(), user.getAvatarUrl(), user.getAvatarUrl());
        } else {
            builder.setAuthor(user.getName(), user.getAvatarUrl(), user.getAvatarUrl());
        }

        builder.setDescription(message);
        builder.addField("Date", sdf.format(date), true);
        builder.addField("Time", stf.format(date), true);
        logChannel.sendMessage(builder.build()).queue();
    }


}
