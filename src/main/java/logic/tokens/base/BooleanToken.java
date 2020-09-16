package logic.tokens.base;

public interface BooleanToken {

    boolean evaluate();
    void setOperand(String name, Object value);

}
