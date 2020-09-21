package logic.console.parsing;

import database.emulation.EventFilterPredicate;
import logic.console.tokensbase.NodeTokenDescription;
import logic.console.tokensbase.TokensBaseForEvent;
import logic.events.BaseEvent;
import logic.tokens.ariphmetic.*;
import logic.tokens.base.BooleanBinaryToken;
import logic.tokens.base.BooleanToken;
import logic.tokens.logical.AndToken;
import logic.tokens.logical.OrToken;

import logic.tokens.logical.TestBoolToken;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FilterStringParser {

    private static HashSet<String> keywords = new HashSet<>(Arrays.asList("day", "month", "year", "person"));

    public static <EventT extends BaseEvent> Predicate<BaseEvent> parsePredicate(String filter, TokensBaseForEvent<EventT> base) {

        EventFilterPredicate<EventT> p = new EventFilterPredicate<>(base);
        AndToken root = new AndToken();
        root.setLeftOperand(new TestBoolToken(true));
        BooleanToken parsed = parseExpression(filter, base);
        if (parsed == null)
            return null;
        root.setRightOperand(root);
        return (Predicate<BaseEvent>) p;
    }

    public static <EventT extends BaseEvent> BooleanToken parseExpression(String filter, TokensBaseForEvent<EventT> base) {
        BooleanToken token = null;

        int orInd = filter.indexOf("|");

        if (orInd != -1) {
            BooleanBinaryToken parsedToken = new OrToken();
            return parseLogicalExpression(filter, base, orInd, parsedToken);
        }

        int andInd = filter.indexOf('&');

        if (andInd != -1) {
            BooleanBinaryToken parsedToken = new AndToken();
            return parseLogicalExpression(filter, base, andInd, parsedToken);
        } else {
            List<NodeTokenDescription> l = parseBinaryNonLogicalExpression(filter.trim());
            if (l.isEmpty())
                return null;
            else {
                token = l.get(0).getToken();
                for (NodeTokenDescription nd : l)
                    base.insertNode(nd);
            }

        }

        return token;
    }

    private static <EventT extends BaseEvent> BooleanToken parseLogicalExpression(
            String filter,
            TokensBaseForEvent<EventT> base,
            int separateIndex,
            BooleanBinaryToken parsedToken) {
        String leftOperandStr = filter.substring(0, separateIndex).trim();
        BooleanToken left = parseExpression(leftOperandStr, base);
        if (left == null)
            return null;
        parsedToken.setLeftOperand(left);

        String rightOperandStr = filter.substring(separateIndex + 1);
        BooleanToken right = parseExpression(rightOperandStr, base);

        if (right == null)
            return null;
        parsedToken.setRightOperand(right);
        return parsedToken;
    }

    public static List<NodeTokenDescription> parseBinaryNonLogicalExpression(String str) {
        Pattern simpleOperator = Pattern.compile("(.+?)\\s*([><=!]{1,2})\\s*(.+)");
        Matcher m = simpleOperator.matcher(str);


        if (!(m.matches() && m.groupCount() == 3))
            return Collections.emptyList();

        NodeTokenDescription leftDesc = new NodeTokenDescription(null, "left");
        NodeTokenDescription rightDesc = new NodeTokenDescription(null, "right");

        String operator = m.group(2);

        switch (operator) {
            case "<":
                leftDesc.setToken(new LessToken());
                break;
            case "<=":
                leftDesc.setToken(new LessEqualToken());
                break;
            case ">":
                leftDesc.setToken(new MoreToken());
                break;
            case ">=":
                leftDesc.setToken(new MoreEqualToken());
                break;
            case "=":
                leftDesc.setToken(new EqualToken());
                break;
            case "!=":
                leftDesc.setToken(new NotEqualToken());
                break;
            default:
                return Collections.emptyList();
        }

        rightDesc.setToken(leftDesc.getToken());

        String left = m.group(1);
        String right = m.group(3);

        List<NodeTokenDescription> out = new ArrayList<>();

        if (!isInteger(left) && !isInteger(right)) {
            if (keywords.contains(left)) {
                leftDesc.setNodeParamName(left);
                out.add(leftDesc);
            } else {
                leftDesc.setOperandValue(left);
            }
            if (keywords.contains(right)) {
                rightDesc.setNodeParamName(right);
                out.add(rightDesc);
            } else {
                rightDesc.setOperandValue(right);
            }
            return out;
        }


        if (isInteger(left)) {
            boolean wrongOtherToken = !isInteger(right) && !keywords.contains(right);

            if (wrongOtherToken)
                return Collections.emptyList();

            rightDesc.setNodeParamName(right);
            rightDesc.getToken().setOperand("left", Integer.parseInt(left));
            out.add(rightDesc);
        }
        if (isInteger(right)) {
            boolean wrongOtherToken = !isInteger(left) && !keywords.contains(left);
            if (wrongOtherToken)
                return Collections.emptyList();
            leftDesc.setNodeParamName(left);
            leftDesc.getToken().setOperand("right", Integer.parseInt(right));
            out.add(leftDesc);
        }

        return out;

    }

    public static boolean isInteger(String string) {
        return StringUtils.isNumeric(string);
    }

}
