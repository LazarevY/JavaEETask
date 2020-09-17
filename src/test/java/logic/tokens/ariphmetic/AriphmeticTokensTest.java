package logic.tokens.ariphmetic;

import logic.tokens.base.BooleanToken;
import org.junit.Test;

import static org.junit.Assert.*;

public class AriphmeticTokensTest {

    @Test
    public void test000() {
        BooleanToken t = new EqualToken();
        t.setOperand("left", 3);
        t.setOperand("right", 3);
        assertTrue(t.evaluate());
    }

    @Test
    public void test001() {
        BooleanToken t = new EqualToken();
        t.setOperand("left", 5);
        t.setOperand("right", 3);
        assertFalse(t.evaluate());
    }

    @Test
    public void test002() {
        BooleanToken t = new EqualToken();
        t.setOperand("left", "Somename");
        t.setOperand("right", "Somename");
        assertTrue(t.evaluate());
    }

    @Test
    public void test003() {
        BooleanToken t = new EqualToken();
        t.setOperand("left", "Somename1");
        t.setOperand("right", "Somename");
        assertFalse(t.evaluate());
    }

    @Test
    public void test004() {
        BooleanToken t = new NotEqualToken();
        t.setOperand("left", 3);
        t.setOperand("right", 3);
        assertFalse(t.evaluate());
    }

    @Test
    public void test005() {
        BooleanToken t = new NotEqualToken();
        t.setOperand("left", 5);
        t.setOperand("right", 3);
        assertTrue(t.evaluate());
    }

    @Test
    public void test006() {
        BooleanToken t = new NotEqualToken();
        t.setOperand("left", "Somename");
        t.setOperand("right", "Somename");
        assertFalse(t.evaluate());
    }

    @Test
    public void test007() {
        BooleanToken t = new NotEqualToken();
        t.setOperand("left", "Somename1");
        t.setOperand("right", "Somename");
        assertTrue(t.evaluate());
    }

    @Test
    public void test008() {
        BooleanToken t = new LessToken();
        t.setOperand("left", 3);
        t.setOperand("right", 4);
        assertTrue(t.evaluate());
    }

    @Test
    public void test009() {
        BooleanToken t = new LessToken();
        t.setOperand("left", 4);
        t.setOperand("right", 4);
        assertFalse(t.evaluate());
    }

    @Test
    public void test010() {
        BooleanToken t = new LessToken();
        t.setOperand("left", 5);
        t.setOperand("right", 4);
        assertFalse(t.evaluate());
    }

    @Test
    public void test011() {
        BooleanToken t = new LessEqualToken();
        t.setOperand("left", 3);
        t.setOperand("right", 4);
        assertTrue(t.evaluate());
    }

    @Test
    public void test012() {
        BooleanToken t = new LessEqualToken();
        t.setOperand("left", 4);
        t.setOperand("right", 4);
        assertTrue(t.evaluate());
    }

    @Test
    public void test013() {
        BooleanToken t = new LessEqualToken();
        t.setOperand("left", 5);
        t.setOperand("right", 4);
        assertFalse(t.evaluate());
    }

    @Test
    public void test014() {
        BooleanToken t = new MoreToken();
        t.setOperand("left", 3);
        t.setOperand("right", 4);
        assertFalse(t.evaluate());
    }

    @Test
    public void test015() {
        BooleanToken t = new MoreToken();
        t.setOperand("left", 4);
        t.setOperand("right", 4);
        assertFalse(t.evaluate());
    }

    @Test
    public void test016() {
        BooleanToken t = new MoreToken();
        t.setOperand("left", 5);
        t.setOperand("right", 4);
        assertTrue(t.evaluate());
    }

    @Test
    public void test017() {
        BooleanToken t = new MoreEqualToken();
        t.setOperand("left", 3);
        t.setOperand("right", 4);
        assertFalse(t.evaluate());
    }

    @Test
    public void test018() {
        BooleanToken t = new MoreEqualToken();
        t.setOperand("left", 4);
        t.setOperand("right", 4);
        assertTrue(t.evaluate());
    }

    @Test
    public void test019() {
        BooleanToken t = new MoreEqualToken();
        t.setOperand("left", 5);
        t.setOperand("right", 4);
        assertTrue(t.evaluate());
    }

}