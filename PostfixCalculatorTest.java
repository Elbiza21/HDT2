package calculator.com;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PostfixCalculatorTest {
    private PostfixCalculator calculator;
    private VectorStack<Integer> stack;

    @BeforeEach
    void setUp() {
        stack = new VectorStack<>();
        calculator = new PostfixCalculator(stack);
    }

    @Test
    void testSimpleAddition() {
        assertEquals(7, calculator.evaluate("3 4 +"));
    }

    @Test
    void testSimpleSubtraction() {
        assertEquals(1, calculator.evaluate("5 4 -"));
    }

    @Test
    void testSimpleMultiplication() {
        assertEquals(20, calculator.evaluate("5 4 *"));
    }

    @Test
    void testSimpleDivision() {
        assertEquals(2, calculator.evaluate("8 4 /"));
    }

    @Test
    void testModuloOperation() {
        assertEquals(1, calculator.evaluate("10 3 %"));
    }

    @Test
    void testComplexExpression() {
        assertEquals(14, calculator.evaluate("5 1 2 + 4 * + 3 -"));
    }

    @Test
    void testDivisionByZero() {
        Exception exception = assertThrows(ArithmeticException.class, () -> {
            calculator.evaluate("3 0 /");
        });
        assertEquals("División entre cero", exception.getMessage());
    }

    @Test
    void testInvalidExpression() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            calculator.evaluate("3 +");
        });
        assertEquals("Expresión inválida", exception.getMessage());
    }
}
