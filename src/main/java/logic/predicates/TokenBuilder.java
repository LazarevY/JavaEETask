package logic.predicates;

import logic.tokens.ariphmetic.*;
import logic.tokens.base.BooleanBinaryToken;

import java.util.HashMap;

public class TokenBuilder {

    private static final HashMap<String, TokenType> stringTokensMap = new HashMap<String, TokenType>(){
        {
            put("<", TokenType.LESS);
            put("<=", TokenType.LESS_EQUAL);
            put(">", TokenType.MORE);
            put(">=", TokenType.MORE_EQUAL);
            put("=", TokenType.EQUAL);
            put("!=", TokenType.NOT_EQUAL);
        }
    };

    public static BooleanBinaryToken create(TokenType tokenType){
        switch (tokenType){
            case LESS:return new LessToken();
            case LESS_EQUAL: return new LessEqualToken();
            case MORE:return new MoreToken();
            case MORE_EQUAL:return new MoreEqualToken();
            case EQUAL:return new EqualToken();
            case NOT_EQUAL:return new NotEqualToken();
        }
        return null;
    }

    public static BooleanBinaryToken create(String tokenStringRepresentation){
        if (!stringTokensMap.containsKey(tokenStringRepresentation))
            throw new IllegalArgumentException("Illegal token value");
        return create(stringTokensMap.get(tokenStringRepresentation));
    }
}
