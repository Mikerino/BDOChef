package BDOChef.Events.Commands;

import BDOChef.BDOChef;

import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class RandomGag extends AbstractCommand {

    public static final String command = BDOChef.prefix + "randomgag";

    public RandomGag(GuildMessageReceivedEvent event, String[] args) {
        try {
            Document doc = Jsoup.connect("https://9gag.com/random").get();
            String pictureUrl = doc.select("link[rel='image_src']").first().attr("href");
            event.getChannel().sendMessage(pictureUrl).queue();
        } catch (Exception e) {
            log(event.getMember(), event.getMember().getUser(), event.getGuild().getTextChannelsByName("log", true).get(0), e.getMessage());
        }

    }
}
