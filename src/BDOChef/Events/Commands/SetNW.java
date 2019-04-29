package BDOChef.Events.Commands;

import BDOChef.BDOChef;
import BDOChef.Models.NWEvent;
import BDOChef.Tables.NWEventTable;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.text.SimpleDateFormat;

public class SetNW extends AbstractCommand {

    public static final String command = BDOChef.prefix + "setnw";

    public SetNW(GuildMessageReceivedEvent event, String[] args) {
        if (!hasRole(event.getMember(), "Officer")) {
            sendErrorMessage(event, "Insufficient permissions");
        } else if (args.length == 1 || args[1].equalsIgnoreCase("help")) {
            showHelp(event, command + " {NodeName} {Date} {Time}");
        } else {
            if (validateInput(event, args)) {
                NWEventTable nwet = new NWEventTable();
                nwet.insertEvent(getNWEvent(args, nwet));
                sendMessage(event, "Set complete");
            }
        }
    }

    private boolean validateInput(GuildMessageReceivedEvent event, String[] args) {
        System.out.println(args);

        if (args.length != 4) {
            showHelp(event, "Invalid format " + command + " {NodeName} {Date} {Time}");
            return false;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        try {
            sdf.parse(args[2]);
        } catch (Exception e) {
            showHelp(event, "Invalid format for {Date} example: 31.12.2019, 01.01.2019");
            return false;
        }
        SimpleDateFormat stf = new SimpleDateFormat("HH:mm");
        try {
            stf.parse(args[3]);
        } catch (Exception e) {
            showHelp(event, "Invalid format for {Time} example: 01:00, 23:59");
            return false;
        }
        return true;
    }

    private NWEvent getNWEvent(String[] args, NWEventTable nwet) {
        NWEvent nwEvent = new NWEvent();
        nwEvent.setId(Integer.toString(nwet.getNextId()));
        nwEvent.setNode(args[1]);
        nwEvent.setDate(args[2]);
        nwEvent.setTime(args[3]);
        nwEvent.setState("active");
        return nwEvent;
    }

}
