package BDOChef;

import BDOChef.Events.Commands.Members;
import BDOChef.Events.CommandsEvent;
import BDOChef.Events.LeaveGuild;
import BDOChef.Events.TermsAndConditions;
import BDOChef.Tables.Settings;
import io.jsondb.JsonDBTemplate;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;


import java.lang.reflect.Constructor;
import java.util.Scanner;

public class BDOChef {
    public static JDA jda;
    public static final String prefix = ".";
    public static JsonDBTemplate jdbTemptale;
    public static Settings settings;

    public static void main(String[] args) throws Exception {
        jdbTemptale = DB.initDB();
        settings = new Settings();
        String token = settings.getSetting("token");
        if (token == null) {
            System.out.println("Insert token");
            Scanner inputToken = new Scanner(System.in);
            token = inputToken.nextLine();
            settings.setSetting("token", token);
        }
        jda = new JDABuilder(AccountType.BOT).setToken(token).buildBlocking();
        jda.getPresence().setStatus(OnlineStatus.IDLE);
        jda.getPresence().setGame(Game.playing("BDOChef"));

        jda.addEventListener(new CommandsEvent());
        jda.addEventListener(new TermsAndConditions());
        jda.addEventListener(new LeaveGuild());
    }
}
