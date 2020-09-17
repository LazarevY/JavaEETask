package logic.tokens.ariphmetic;

public class MoreToken extends LessEqualToken {
    @Override
    public boolean evaluate() {
        return !super.evaluate();
    }
}
