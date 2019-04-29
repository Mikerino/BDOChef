package BDOChef.Events;

import BDOChef.BDOChef;
import BDOChef.Events.Commands.*;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.awt.*;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandsEvent extends AbstractEvent {

    private Map<String, Class> commandList = null;

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        if (event.getAuthor().isBot())
            return;

        String[] args = event.getMessage().getContentRaw().split(" ");
        try {
            if (getCommandList().containsKey(args[0].toLowerCase())) {
                Class command = getCommandList().get(args[0].toLowerCase());
                Constructor constructor = command.getConstructor(GuildMessageReceivedEvent.class, String[].class);
                constructor.newInstance(new Object[] {event, args});
            } else if (args[0].substring(0, 1).equalsIgnoreCase(BDOChef.prefix)) {
                sendCommandHelp(event);
            }
        } catch (Exception e) {
            log(event.getMember(), event.getAuthor(), event.getGuild().getTextChannelsByName("log", true).get(0), e.getMessage());
        }

    }

    public void sendCommandHelp(GuildMessageReceivedEvent event) {
        Map<String, Class> list = getCommandList();
        List<String> commands = new ArrayList<String>();
        list.forEach((k,v) -> commands.add(k));

        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Command not found");
        builder.setColor(Color.decode("#FF0000"));
        builder.setDescription("Valid commands: " + String.join(", ", commands));
        event.getChannel().sendMessage(builder.build()).queue();
    }

    private Map<String, Class> getCommandList() {
        if (commandList == null) {
            commandList = new HashMap<String, Class>();
            commandList.put(SetGameCharacter.command, SetGameCharacter.class);
            commandList.put(RemoveGameCharacter.command, Members.class);
            commandList.put(Members.command, Members.class);
            commandList.put(Register.command, Register.class);
            commandList.put(Unregister.command, Unregister.class);
            commandList.put(SetNW.command, SetNW.class);
            commandList.put(RandomGag.command, RandomGag.class);
        }

        return commandList;
    }
}
