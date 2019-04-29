package BDOChef.Events.Commands;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class AbstractCommand {

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

    protected void sendErrorMessage(GuildMessageReceivedEvent event, String title, String message) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle(title);
        builder.setColor(Color.decode("#FF0000"));
        builder.setDescription(message);
        event.getChannel().sendMessage(builder.build()).queue();
    }

    protected void sendErrorMessage(GuildMessageReceivedEvent event, String title) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle(title);
        builder.setColor(Color.decode("#FF0000"));
        event.getChannel().sendMessage(builder.build()).queue();
    }

    protected void sendMessage(GuildMessageReceivedEvent event, String title, String message) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle(title);
        builder.setColor(Color.decode("#00FF00"));
        builder.setDescription(message);
        event.getChannel().sendMessage(builder.build()).queue();
    }

    protected void sendMessage(GuildMessageReceivedEvent event, String title) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle(title);
        builder.setColor(Color.decode("#00FF00"));
        event.getChannel().sendMessage(builder.build()).queue();
    }

    protected void showHelp(GuildMessageReceivedEvent event, String message) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Help");
        builder.setColor(Color.decode("#FFFF00"));
        builder.setDescription(message);
        event.getChannel().sendMessage(builder.build()).queue();
    }

    protected boolean hasRole(Member member, String role) {
        for (Role rRole : member.getRoles()) {
            if (rRole.getName().equalsIgnoreCase(role)) {
                return true;
            }
        }
        return false;
    }

    protected boolean hasRole(Member member, String[] roles) {
        for (Role rRole : member.getRoles()) {
            for (String role : roles) {
                if (rRole.getName().equalsIgnoreCase(role)) {
                    return true;
                }
            }
        }
        return false;
    }


}
