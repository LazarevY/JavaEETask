package logic.console.tokensbase;

import logic.events.Birthday;

import java.util.List;

public class TokensBaseBirthdayEvent extends TokensBaseForEvent<Birthday> {
    @Override
    public void acceptEvent(Birthday e) {
        super.acceptEvent(e);
        List<NodeTokenDescription> l = listNodesForParameter("person");
        for (NodeTokenDescription d: l)
            d.setOperandValue(e.getTargetPerson());
    }
}
