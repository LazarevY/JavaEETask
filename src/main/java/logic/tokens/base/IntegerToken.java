package logic.tokens.base;

public abstract class IntegerToken  implements BooleanBinaryToken {

    public IntegerToken(){
        left = 0;
        right = 0;
    }

    @Override
    public void setLeftOperand(Object operand) {
        left = (Integer)operand;
    }

    @Override
    public void setRightOperand(Object operand) {
        right = (Integer) operand;
    }

    protected Integer left;
    protected   Integer right;

}
