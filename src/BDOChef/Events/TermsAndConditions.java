package BDOChef.Events;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.core.events.message.react.MessageReactionRemoveEvent;

import java.awt.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TermsAndConditions extends AbstractEvent {

    public void onMessageReactionAdd(MessageReactionAddEvent event) {
        try {
            if (event.getUser().isBot())
                return;
            if (event.getReactionEmote().getName().equals("\uD83D\uDC4D") && event.getChannel().getName().equalsIgnoreCase("terms_and_conditions")) {
                String nickname;
                if (event.getMember().getNickname() != null) {
                    nickname = event.getMember().getNickname();
                } else {
                    nickname = event.getUser().getName();
                }
                /*Pattern p = Pattern.compile("\\((.*?)\\)");
                String nickname;
                if (event.getMember().getNickname() != null) {
                    nickname = event.getMember().getNickname();
                } else {
                    nickname = event.getUser().getName();
                }
                Matcher m = p.matcher(nickname);

                if (!m.find()) {
                    event.getReaction().removeReaction(event.getUser()).queue();
                    EmbedBuilder builder = new EmbedBuilder();
                    builder.setTitle("Wrong name format");
                    builder.setColor(Color.red);
                    builder.setAuthor(event.getMember().getNickname(), event.getUser().getAvatarUrl(), event.getUser().getAvatarUrl());
                    builder.setDescription("Please change your server nickname accordingly, format: (FamilyName) PreferedName");
                    builder.addField("For example", "(Mikerino) Mike", false);
                    event.getChannel().sendMessage(builder.build()).complete().delete().queueAfter(15, TimeUnit.SECONDS);
                    log(event.getMember(), event.getUser(), event.getGuild().getTextChannelsByName("log", true).get(0), "Wrong name format.");
                } else {
                    registerMember(event.getMember(),m.group(1), "active");*/
                EmbedBuilder builder = new EmbedBuilder();
                builder.setTitle("Guest access granted.");
                builder.setColor(Color.green);
                builder.setAuthor(nickname, event.getUser().getAvatarUrl(), event.getUser().getAvatarUrl());
                event.getChannel().sendMessage(builder.build()).complete().delete().queueAfter(15, TimeUnit.SECONDS);
                event.getGuild().getController().addRolesToMember(event.getMember(), event.getJDA().getRolesByName("Guest", true)).complete();
                log(event.getMember(), event.getUser(), event.getGuild().getTextChannelsByName("log", true).get(0), "Guest access granted.");
                /* }*/


            }
        } catch (Exception e) {
            log(event.getMember(), event.getUser(), event.getGuild().getTextChannelsByName("log", true).get(0), e.getMessage());
        }
    }

    public void onMessageReactionRemove(MessageReactionRemoveEvent event) {
        try {
            if (event.getUser().isBot())
                return;
            if (event.getReactionEmote().getName().equals("\uD83D\uDC4D") && event.getChannel().getName().equalsIgnoreCase("terms_and_conditions")) {
                /*registerMember(event.getMember(), null, "inactive");*/
                EmbedBuilder builder = new EmbedBuilder();
                builder.setTitle("Guest access removed.");
                builder.setColor(Color.green);
                builder.setAuthor(event.getMember().getNickname(), event.getUser().getAvatarUrl(), event.getUser().getAvatarUrl());
                event.getChannel().sendMessage(builder.build()).complete().delete().queueAfter(15, TimeUnit.SECONDS);
                event.getGuild().getController().removeRolesFromMember(event.getMember(), event.getJDA().getRolesByName("Guest", true)).complete();
                log(event.getMember(), event.getUser(), event.getGuild().getTextChannelsByName("log", true).get(0), "Guest access removed.");
            }
        } catch (Exception e) {
            log(event.getMember(), event.getUser(), event.getGuild().getTextChannelsByName("log", true).get(0), e.getMessage());
        }

    }
}
