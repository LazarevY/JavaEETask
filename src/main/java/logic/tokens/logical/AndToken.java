package logic.tokens.logical;

import logic.tokens.base.LogicalBinaryToken;

public class AndToken extends LogicalBinaryToken {
    @Override
    public boolean evaluate() {
        return this.left.evaluate() && this.right.evaluate();
    }
}
