package logic.console.tokensbase;

import logic.events.BaseEvent;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public abstract class TokensBaseForEvent<Event extends BaseEvent> {

    public TokensBaseForEvent() {
        this.nodesMap = new HashMap<>();
    }

    public void insertNode(String parameter, NodeTokenDescription nodeTokenDescription){
        if (!nodesMap.containsKey(parameter))
            nodesMap.put(parameter, new ArrayList<>());
        nodesMap.get(parameter).add(nodeTokenDescription);
    }

    protected List<NodeTokenDescription> listNodesForParameter(String param){
        return nodesMap.getOrDefault(param, new ArrayList<>());
    }

    public void acceptEvent(Event e){
        List<NodeTokenDescription> l = listNodesForParameter("year");

        for (NodeTokenDescription d: l)
            d.setOperandValue(e.getEventDate().get(Calendar.YEAR));

        l = listNodesForParameter("month");

        for (NodeTokenDescription d: l)
            d.setOperandValue(e.getEventDate().get(Calendar.MONTH));

        l = listNodesForParameter("day");

        for (NodeTokenDescription d: l)
            d.setOperandValue(e.getEventDate().get(Calendar.DAY_OF_MONTH));

        l = listNodesForParameter("person");
    }

    private HashMap<String, List<NodeTokenDescription>> nodesMap;
}
