package logic.console.tokensbase;

import logic.events.AppointmentEvent;

import java.util.Calendar;
import java.util.List;

public class TokensBaseAppointmentEvent extends TokensBaseForEvent<AppointmentEvent> {
    @Override
    public void acceptEvent(AppointmentEvent e) {
        super.acceptEvent(e);
        List<NodeTokenDescription> l = listNodesForParameter("person");
        for (NodeTokenDescription d: l)
            d.setOperandValue(e.getTargetPerson());

        l = listNodesForParameter("hour");
        for (NodeTokenDescription d: l)
            d.setOperandValue(e.getAppointmentTime().get(Calendar.HOUR_OF_DAY));

        l = listNodesForParameter("minute");
        for (NodeTokenDescription d: l)
            d.setOperandValue(e.getAppointmentTime().get(Calendar.MINUTE));
    }
}

