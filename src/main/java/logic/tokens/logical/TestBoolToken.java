package logic.tokens.logical;

import logic.tokens.base.BooleanToken;

public class TestBoolToken implements BooleanToken {

    public TestBoolToken(boolean value){
        this.value = value;
    }

    @Override
    public boolean evaluate() {
        return value;
    }

    @Override
    public void setOperand(String name, Object value) {
        if (name.equals("value"))
            this.value = (Boolean)value;

    }

    private boolean value;
}
