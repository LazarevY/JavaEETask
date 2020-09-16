package logic.tokens.base;

import logic.tokens.base.BooleanBinaryToken;

public abstract class LogicalBinaryToken implements BooleanBinaryToken {

    @Override
    public void setLeftOperand(Object operand) {
        left = (BooleanToken)operand;
    }

    @Override
    public void setRightOperand(Object operand) {
        right = (BooleanToken)operand;
    }

    protected BooleanToken left;
    protected BooleanToken right;
}
