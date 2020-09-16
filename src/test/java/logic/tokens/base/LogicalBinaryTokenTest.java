package logic.tokens.base;

import logic.tokens.logical.AndToken;
import logic.tokens.logical.OrToken;
import logic.tokens.logical.TestBoolToken;
import org.junit.Test;

import static org.junit.Assert.*;

public class LogicalBinaryTokenTest {

    @Test
    public void test000(){
        LogicalBinaryToken t = new AndToken();
        t.setLeftOperand(new TestBoolToken(true));
        t.setRightOperand(new TestBoolToken(true));
        assertTrue(t.evaluate());
    }

    @Test
    public void test001(){
        LogicalBinaryToken t = new AndToken();
        t.setLeftOperand(new TestBoolToken(false));
        t.setRightOperand(new TestBoolToken(true));
        assertFalse(t.evaluate());
    }

    @Test
    public void test002(){
        LogicalBinaryToken t = new AndToken();
        t.setLeftOperand(new TestBoolToken(true));
        t.setRightOperand(new TestBoolToken(false));
        assertFalse(t.evaluate());
    }

    @Test
    public void test003(){
        LogicalBinaryToken t = new AndToken();
        t.setLeftOperand(new TestBoolToken(false));
        t.setRightOperand(new TestBoolToken(false));
        assertFalse(t.evaluate());
    }

    @Test
    public void test004(){
        LogicalBinaryToken t = new OrToken();
        t.setLeftOperand(new TestBoolToken(true));
        t.setRightOperand(new TestBoolToken(true));
        assertTrue(t.evaluate());
    }

    @Test
    public void test005(){
        LogicalBinaryToken t = new OrToken();
        t.setLeftOperand(new TestBoolToken(false));
        t.setRightOperand(new TestBoolToken(true));
        assertTrue(t.evaluate());
    }

    @Test
    public void test006(){
        LogicalBinaryToken t = new OrToken();
        t.setLeftOperand(new TestBoolToken(true));
        t.setRightOperand(new TestBoolToken(false));
        assertTrue(t.evaluate());
    }

    @Test
    public void test007(){
        LogicalBinaryToken t = new OrToken();
        t.setLeftOperand(new TestBoolToken(false));
        t.setRightOperand(new TestBoolToken(false));
        assertFalse(t.evaluate());
    }

    @Test
    public void test008(){
        BooleanToken t = new TestBoolToken(true);
        assertTrue(t.evaluate());
    }

    @Test
    public void test009(){
        BooleanToken t = new TestBoolToken(false);
        assertFalse(t.evaluate());
    }

}