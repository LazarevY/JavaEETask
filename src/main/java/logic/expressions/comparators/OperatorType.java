package logic.expressions.comparators;

public enum OperatorType {
    More(">"),
    MoreEqual(">="),
    Less("<"),
    LessEqual("<="),
    Equal("="),
    NotEqual("!=");

    private String representation;

    OperatorType(String repr){
        this.representation = repr;
    }

    public String getRepresentation() {
        return representation;
    }
}
