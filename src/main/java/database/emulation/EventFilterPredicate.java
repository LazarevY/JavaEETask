package database.emulation;

import logic.tokens.mapping.TokensBaseForEvent;
import logic.events.Event;
import logic.tokens.base.BooleanToken;
import logic.tokens.logical.AndToken;
import logic.tokens.logical.TestBoolToken;

import java.util.function.Predicate;

public class EventFilterPredicate<EventT extends Event> implements Predicate<EventT> {

    public EventFilterPredicate(TokensBaseForEvent<EventT> tokensBaseForEvent){
        this.tokensBaseForEvent = tokensBaseForEvent;
        rootToken = new AndToken();
        rootToken.setOperand("left", new TestBoolToken(true));
    }

    public TokensBaseForEvent<EventT> getTokensBaseForEvent() {
        return tokensBaseForEvent;
    }

    public BooleanToken getRootToken() {
        return rootToken;
    }

    private TokensBaseForEvent<EventT> tokensBaseForEvent;

    public void setRootToken(BooleanToken rootToken) {
        this.rootToken = rootToken;
    }

    private BooleanToken rootToken;

    @Override
    public boolean test(EventT baseEvent) {
        tokensBaseForEvent.acceptEvent(baseEvent);
        return rootToken.evaluate();
    }
}
