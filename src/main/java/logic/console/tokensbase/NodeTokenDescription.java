package logic.console.tokensbase;

import logic.tokens.base.BooleanToken;

public class NodeTokenDescription {

    public void setOperandValue(Object operand){
        token.setOperand(operandName, operand);
    }

    public NodeTokenDescription(BooleanToken token, String operandName) {
        this.token = token;
        this.operandName = operandName;
    }

    public BooleanToken getToken() {
        return token;
    }

    public void setToken(BooleanToken token) {
        this.token = token;
    }

    public String getOperandName() {
        return operandName;
    }

    public void setOperandName(String operandName) {
        this.operandName = operandName;
    }

    private BooleanToken token;
    private String operandName;
}
