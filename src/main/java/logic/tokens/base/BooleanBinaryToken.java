package logic.tokens.base;

public interface BooleanBinaryToken extends BooleanToken{
    void setLeftOperand(Object operand);
    void setRightOperand(Object operand);

    @Override
    default void setOperand(String name, Object value){
        if (name.equals("left"))
            setLeftOperand(value);
        else if (name.equals("right"))
            setRightOperand(value);
    }
}
