package logic.tokens.ariphmetic;

public class MoreEqualToken extends LessToken{
    @Override
    public boolean evaluate() {
        return !super.evaluate();
    }
}
