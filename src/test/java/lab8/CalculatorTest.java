package lab8;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.tientvpd10102.lab8.Calculator;

public class CalculatorTest {
    Calculator calculator = new Calculator();

    @Test
    public void testAddition() {
        Assert.assertEquals(calculator.add(2, 3), 5);
    }

    @Test
    public void testSubtraction() {
        Assert.assertEquals(calculator.subtract(5, 3), 2);
    }

    @Test
    public void testMultiplication() {
        Assert.assertEquals(calculator.multiply(2, 3), 6);
    }

    @Test
    public void testDivision() {
        Assert.assertEquals(calculator.divide(6, 2), 3);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testDivisionByZero() {
        calculator.divide(6, 0);
    }
}