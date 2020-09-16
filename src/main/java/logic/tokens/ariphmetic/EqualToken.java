package logic.tokens.ariphmetic;

import logic.tokens.base.BooleanBinaryToken;

public class EqualToken implements BooleanBinaryToken {

    public EqualToken(){
        left = new Object();
        right = new Object();
    }

    @Override
    public void setLeftOperand(Object operand) {
        left = operand;
    }

    @Override
    public void setRightOperand(Object operand) {
        right = operand;
    }

    @Override
    public boolean evaluate() {
        return left.equals(right);
    }



    private Object left;
    private Object right;
}
