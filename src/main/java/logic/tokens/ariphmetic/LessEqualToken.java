package logic.tokens.ariphmetic;

import logic.tokens.base.IntegerToken;

public class LessEqualToken extends IntegerToken {
    @Override
    public boolean evaluate() {
        return this.left <= this.right;
    }
}
