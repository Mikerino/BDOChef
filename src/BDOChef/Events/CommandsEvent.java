package BDOChef.Events;

import BDOChef.BDOChef;
import BDOChef.Events.Commands.*;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.awt.*;

public class CommandsEvent extends AbstractEvent {


    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        if (event.getAuthor().isBot())
            return;

        String[] args = event.getMessage().getContentRaw().split(" ");
        try {
            switch (args[0].toLowerCase()) {
                case SetGameCharacter.command:
                    new SetGameCharacter(event, args);
                    break;
                case RemoveGameCharacter.command:
                    new RemoveGameCharacter(event, args);
                    break;
                case Members.command:
                    new Members(event, args);
                    break;
                case Register.command:
                    new Register(event, args);
                    break;
                case Unregister.command:
                    new Unregister(event, args);
                    break;
                case SetNW.command:
                    new SetNW(event, args);
                    break;
                case RandomGag.command:
                    new RandomGag(event, args);
                    break;
                default:
                    if (args[0].substring(0, 1).equalsIgnoreCase(BDOChef.prefix)) {
                        sendCommandHelp(event);
                    }
                    break;
            }
        } catch (Exception e) {
            log(event.getMember(), event.getAuthor(), event.getGuild().getTextChannelsByName("log", true).get(0), e.getMessage());
        }

    }

    public void sendCommandHelp(GuildMessageReceivedEvent event) {

        String[] commands = {
                SetGameCharacter.command,
                RemoveGameCharacter.command,
                Members.command,
                Register.command,
                Unregister.command,
                SetNW.command,
                RandomGag.command,
        };

        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Command not found");
        builder.setColor(Color.decode("#FF0000"));
        builder.setDescription("Valid commands: " + String.join(", ", commands));
        event.getChannel().sendMessage(builder.build()).queue();
    }
}
