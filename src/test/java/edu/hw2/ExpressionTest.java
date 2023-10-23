package edu.hw2;

import edu.hw2.Expressions.Addition;
import edu.hw2.Expressions.Constant;
import edu.hw2.Expressions.Exponent;
import edu.hw2.Expressions.Multiplication;
import edu.hw2.Expressions.Negate;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ExpressionTest {

    // Тест выражений из задания
    @Test
    public void firstExpression() {
        var one = new Constant(1);

        var negOne = new Negate(one);

        assertThat(negOne.evaluate()).isEqualTo(-1);
    }

    @Test
    public void secondExpression() {
        var two = new Constant(2);
        var four = new Constant(4);

        var sumTwoFour = new Addition(two, four); // 2 + 4 = 6

        assertThat(sumTwoFour.evaluate()).isEqualTo(6);
    }

    @Test
    public void thirdExpression() {
        var six = new Constant(6);
        var negOne = new Constant(-1);

        var mult = new Multiplication(six, negOne); // 6 * (-1) = -6

        assertThat(mult.evaluate()).isEqualTo(-6);
    }

    @Test
    public void fourthExpression() {
        var negSix = new Constant(-6);

        var exp = new Exponent(negSix, 2); // -6 * -6 = 36

        assertThat(exp.evaluate()).isEqualTo(36);
    }

    @Test
    public void fifthExpression() {
        var num = new Constant(36);
        var one = new Constant(1);

        var res = new Addition(num, one); // -6 * -6 = 36

        assertThat(res.evaluate()).isEqualTo(37);
    }
}
