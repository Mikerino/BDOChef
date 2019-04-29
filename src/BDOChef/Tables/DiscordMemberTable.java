package BDOChef.Tables;

import BDOChef.BDOChef;
import BDOChef.Models.DiscordMember;
import net.dv8tion.jda.core.entities.Member;

import java.util.List;

public class DiscordMemberTable {

    public boolean isRegistered(Member member) {
        String jxQuery = String.format("/.[discordId='%s']", member.getUser().getId());
        List<DiscordMember> discordMembers = BDOChef.jdbTemptale.find(jxQuery, DiscordMember.class);
        if (discordMembers.size() > 0)
            return true;
        return false;
    }

    public void saveMember(DiscordMember discordMember) {
        DiscordMember savedMember = getMemberByDiscordId(discordMember.getId());
        if ( savedMember == null) {
            BDOChef.jdbTemptale.insert(discordMember);
        } else {
            if (discordMember.getFamName() == null) {
                discordMember.setFamName(savedMember.getFamName());
            }
            BDOChef.jdbTemptale.save(discordMember, DiscordMember.class);
        }
    }

    public DiscordMember getMemberByDiscordId(String discordId) {
        return BDOChef.jdbTemptale.findById(discordId, DiscordMember.class);
    }

    public List<DiscordMember> getActiveMembers() {
        String jxQuery = String.format("/.[state='%s']", "active");
        return BDOChef.jdbTemptale.find(jxQuery, DiscordMember.class);
    }
}
