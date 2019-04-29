package BDOChef.Tables;

import BDOChef.BDOChef;
import BDOChef.Models.GameCharacter;
import BDOChef.Models.NWEvent;

import java.util.List;

public class NWEventTable {

    public int getNextId() {
        List<NWEvent> nwEvents = getNWEvents();
        if (nwEvents.size() == 0) {
            return 1;
        }
        NWEvent lastNWEvent = nwEvents.get(nwEvents.size()-1);
        return Integer.parseInt(lastNWEvent.getId())+1;
    }

    public NWEvent getLastEvent() {
        List<NWEvent> nwEvents = getNWEvents();
        if (nwEvents.size() == 0) {
            return null;
        }
        return nwEvents.get(nwEvents.size()-1);
    }

    public List<NWEvent> getNWEvents() {
        return BDOChef.jdbTemptale.findAll(NWEvent.class);
    }

    public void insertEvent(NWEvent nwEvent) {
        BDOChef.jdbTemptale.insert(nwEvent);
    }


}
