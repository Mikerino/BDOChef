package BDOChef;

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
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.net.URL;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BDOChef {
    public static JDA jda;
    public static final String prefix = ".";
    public static JsonDBTemplate jdbTemptale;
    public static Settings settings;

    public static void main(String[] args) throws Exception {
        jdbTemptale = DB.initDB();
        settings = new Settings();
        jda = new JDABuilder(AccountType.BOT).setToken("NTY0NTA0OTQyOTM3ODk5MDE4.XKskjQ.evfp3YELrDzbz2PfjW_yDoUQd8o").buildBlocking();
        jda.getPresence().setStatus(OnlineStatus.IDLE);
        jda.getPresence().setGame(Game.playing("BDOChef"));

        jda.addEventListener(new CommandsEvent());
        jda.addEventListener(new TermsAndConditions());
        jda.addEventListener(new LeaveGuild());
    }
}
