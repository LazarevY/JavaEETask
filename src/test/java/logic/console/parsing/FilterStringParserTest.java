package logic.console.parsing;

import logic.console.tokensbase.NodeTokenDescription;
import logic.console.tokensbase.TokensBaseBirthdayEvent;
import logic.events.Birthday;
import logic.tokens.ariphmetic.EqualToken;
import logic.tokens.ariphmetic.LessToken;
import logic.tokens.ariphmetic.NotEqualToken;
import logic.tokens.base.BooleanBinaryToken;
import logic.tokens.base.BooleanToken;
import logic.tokens.base.IntegerToken;
import org.junit.Test;
import utils.calendar.CalendarUtils;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

public class FilterStringParserTest
{
    @Test
    public void test000(){
        List<NodeTokenDescription> l = FilterStringParser.parseBinaryNonLogicalExpression("day<20");
        assertEquals(1, l.size());
        assertEquals("day", l.get(0).getNodeParamName());
        assertEquals("left", l.get(0).getOperandName());
        IntegerToken t = (IntegerToken) l.get(0).getToken();
        t.setOperand("left", 19);
        assertTrue(t.evaluate());

    }

    @Test
    public void test001(){
        List<NodeTokenDescription> l = FilterStringParser.parseBinaryNonLogicalExpression("day<month");
        assertEquals(2, l.size());
        assertEquals("day", l.get(0).getNodeParamName());
        assertEquals("left", l.get(0).getOperandName());
        assertEquals("month", l.get(1).getNodeParamName());
        assertEquals("right", l.get(1).getOperandName());
        assertTrue(l.get(0).getToken() instanceof LessToken);

    }

    @Test
    public void test002(){
        List<NodeTokenDescription> l = FilterStringParser.parseBinaryNonLogicalExpression("day==month");
        assertEquals(0, l.size());
    }

    @Test
    public void test003(){
        List<NodeTokenDescription> l = FilterStringParser.parseBinaryNonLogicalExpression("dayaamonth");
        assertEquals(0, l.size());
    }

    @Test
    public void test004(){
        List<NodeTokenDescription> l = FilterStringParser.parseBinaryNonLogicalExpression("day==month");
        assertEquals(0, l.size());
    }

    @Test
    public void test005(){
        List<NodeTokenDescription> l = FilterStringParser.parseBinaryNonLogicalExpression("day!=20");
        assertEquals(1, l.size());
        assertEquals("day", l.get(0).getNodeParamName());
        assertEquals("left", l.get(0).getOperandName());
        BooleanBinaryToken t = (BooleanBinaryToken) l.get(0).getToken();
        assertTrue(t instanceof NotEqualToken);
        t.setOperand("left", 19);
        assertTrue(t.evaluate());

    }

    @Test
    public void test006(){
        List<NodeTokenDescription> l = FilterStringParser.parseBinaryNonLogicalExpression("20=day");
        assertEquals(1, l.size());
        assertEquals("day", l.get(0).getNodeParamName());
        assertEquals("right", l.get(0).getOperandName());
        BooleanBinaryToken t = (BooleanBinaryToken) l.get(0).getToken();
        assertTrue(t instanceof EqualToken);
        t.setOperand("right", 20);
        assertTrue(t.evaluate());

    }

    @Test
    public void test007(){
        assertFalse(FilterStringParser.isInteger("12.35"));
    }

    @Test
    public void test008(){
        assertFalse(FilterStringParser.isInteger("12,35"));
    }

    @Test
    public void test009(){
        assertFalse(FilterStringParser.isInteger("-1235"));
    }

    @Test
    public void test010(){
        TokensBaseBirthdayEvent base = new TokensBaseBirthdayEvent();
        BooleanToken parsed = FilterStringParser.parseExpression("10<20 & 40=40", base);
        assertNotNull(parsed);
        assertTrue(parsed.evaluate());
    }

    @Test
    public void test011(){
        TokensBaseBirthdayEvent base = new TokensBaseBirthdayEvent();
        BooleanToken parsed = FilterStringParser.parseExpression("20<=20 & 41!=40", base);
        assertNotNull(parsed);
        assertTrue(parsed.evaluate());
    }

    @Test
    public void test012(){
        TokensBaseBirthdayEvent base = new TokensBaseBirthdayEvent();
        BooleanToken parsed = FilterStringParser.parseExpression("30=20 | 41=40", base);
        assertNotNull(parsed);
        assertFalse(parsed.evaluate());
    }

    @Test
    public void test013(){
        TokensBaseBirthdayEvent base = new TokensBaseBirthdayEvent();
        BooleanToken parsed = FilterStringParser.parseExpression("30>20 | 41=40", base);
        assertNotNull(parsed);
        assertTrue(parsed.evaluate());
    }

    @Test
    public void test014(){
        TokensBaseBirthdayEvent base = new TokensBaseBirthdayEvent();
        BooleanToken parsed = FilterStringParser.parseExpression("0<1 & 1>0 & 20>1 & 40<60", base);
        assertNotNull(parsed);
        assertTrue(parsed.evaluate());
    }

    @Test
    public void test015(){
        TokensBaseBirthdayEvent base = new TokensBaseBirthdayEvent();
        BooleanToken parsed = FilterStringParser.parseExpression("0<1 & 1>0 | 0>1 & 40<60", base);
        assertNotNull(parsed);
        assertTrue(parsed.evaluate());
    }

    @Test
    public void test016(){
        TokensBaseBirthdayEvent base = new TokensBaseBirthdayEvent();
        BooleanToken parsed = FilterStringParser.parseExpression("0=1 & 1>0 | 20>1 & 40<60", base);
        assertNotNull(parsed);
        assertTrue(parsed.evaluate());
    }

    @Test
    public void test017(){
        TokensBaseBirthdayEvent base = new TokensBaseBirthdayEvent();
        BooleanToken parsed = FilterStringParser.parseExpression("0=1 & 1>0 | 0=1 & 40<60", base);
        assertNotNull(parsed);
        assertFalse(parsed.evaluate());
    }

    @Test
    public void test018(){
        TokensBaseBirthdayEvent base = new TokensBaseBirthdayEvent();
        BooleanToken parsed = FilterStringParser.parseExpression("day > 10 & month > 5", base);
        Birthday e =  new Birthday(
                LocalDate.of(2020, 7, 14),
                "Null",
                "Person",
                "Void");
        base.acceptEvent(e);
        assertNotNull(parsed);
        assertTrue(parsed.evaluate());
    }

    @Test
    public void test019(){
        TokensBaseBirthdayEvent base = new TokensBaseBirthdayEvent();
        BooleanToken parsed = FilterStringParser.parseExpression("day >= 14 & month >= 7 & person=Gachi", base);
        Birthday e =  new Birthday(
                LocalDate.of(2020, 7, 14),
                "Null",
                "Gachi",
                "Void");
        base.acceptEvent(e);
        assertNotNull(parsed);
        assertTrue(parsed.evaluate());
    }

    @Test
    public void test020(){
        TokensBaseBirthdayEvent base = new TokensBaseBirthdayEvent();
        BooleanToken parsed = FilterStringParser.parseExpression("day>", base);
        Birthday e =  new Birthday(
                LocalDate.of(2020, 7, 14),
                "Null",
                "Gachi",
                "Void");
        assertNull(parsed);
    }

    @Test
    public void test021(){
        TokensBaseBirthdayEvent base = new TokensBaseBirthdayEvent();
        BooleanToken parsed = FilterStringParser.parseExpression("1&2|3", base);
        Birthday e =  new Birthday(
                LocalDate.of(2020, 7, 14),
                "Null",
                "Gachi",
                "Void");
        assertNull(parsed);
    }

    @Test
    public void test022(){
        TokensBaseBirthdayEvent base = new TokensBaseBirthdayEvent();
        BooleanToken parsed = FilterStringParser.parseExpression("1&ааа", base);
        Birthday e =  new Birthday(
                LocalDate.of(2020, 7, 14),
                "Null",
                "Gachi",
                "Void");
        assertNull(parsed);
    }

    @Test
    public void test023(){
        TokensBaseBirthdayEvent base = new TokensBaseBirthdayEvent();
        BooleanToken parsed = FilterStringParser.parseExpression("1>ааа", base);
        Birthday e =  new Birthday(
                LocalDate.of(2020, 7, 14),
                "Null",
                "Gachi",
                "Void");
        assertNull(parsed);
    }

}