package logic.console.tokensbase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public abstract class TokensBaseForEvent<Event extends logic.events.Event> {

    private HashMap<String, List<NodeTokenDescription>> nodesMap;

    public TokensBaseForEvent() {
        this.nodesMap = new HashMap<>();
    }

    public void insertNode(String parameter, NodeTokenDescription nodeTokenDescription){
        nodeTokenDescription.setNodeParamName(parameter);
        if (!nodesMap.containsKey(parameter))
            nodesMap.put(parameter, new ArrayList<>());
        nodesMap.get(parameter).add(nodeTokenDescription);
    }

    public void insertNode(NodeTokenDescription node){
        String parameter = node.getNodeParamName();
        if (!nodesMap.containsKey(parameter))
            nodesMap.put(parameter, new ArrayList<>());
        nodesMap.get(parameter).add(node);
    }

    protected List<NodeTokenDescription> listNodesForParameter(String param){
        return nodesMap.getOrDefault(param, new ArrayList<>());
    }

    public void acceptEvent(Event e){
        List<NodeTokenDescription> l = listNodesForParameter("year");

        for (NodeTokenDescription d: l)
            d.setOperandValue(e.getEventDate().getYear());

        l = listNodesForParameter("month");

        for (NodeTokenDescription d: l)
            // Plus one because GregorianCalendar class has first month as 0
            d.setOperandValue(e.getEventDate().getMonth().getValue());

        l = listNodesForParameter("day");

        for (NodeTokenDescription d: l)
            d.setOperandValue(e.getEventDate().getDayOfMonth());

    }

}
