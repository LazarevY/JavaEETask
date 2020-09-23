package logic.console.tokensbase;

import logic.events.Appointment;
import logic.tokens.mapping.TokensBaseForEvent;

import java.util.List;

public class TokensBaseAppointmentEvent extends TokensBaseForEvent<Appointment> {
    @Override
    public void acceptEvent(Appointment e) {
        super.acceptEvent(e);
        List<NodeTokenDescription> l = listNodesForParameter("person");
        for (NodeTokenDescription d: l)
            d.setOperandValue(e.getTargetPerson());

        l = listNodesForParameter("hour");
        for (NodeTokenDescription d: l)
            d.setOperandValue(e.getTime().getHour());

        l = listNodesForParameter("minute");
        for (NodeTokenDescription d: l)
            d.setOperandValue(e.getTime().getMinute());
    }
}

