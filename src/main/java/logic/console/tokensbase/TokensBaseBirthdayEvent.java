package logic.console.tokensbase;

import logic.events.BirthdayEvent;

import java.util.List;

public class TokensBaseBirthdayEvent extends TokensBaseForEvent<BirthdayEvent> {
    @Override
    public void acceptEvent(BirthdayEvent e) {
        super.acceptEvent(e);
        List<NodeTokenDescription> l = listNodesForParameter("person");
        for (NodeTokenDescription d: l)
            d.setOperandValue(e.getTargetPerson());
    }
}
