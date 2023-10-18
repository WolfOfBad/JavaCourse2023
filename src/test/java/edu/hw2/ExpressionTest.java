package edu.hw2;

import edu.hw2.Expressions.Addition;
import edu.hw2.Expressions.Constant;
import edu.hw2.Expressions.Exponent;
import edu.hw2.Expressions.Multiplication;
import edu.hw2.Expressions.Negate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ExpressionTest {
    @Test
    @DisplayName("Проверка всех выражений из задания")
    public void expressionTest() {
        // given
        var two = new Constant(2);
        var four = new Constant(4);

        // when
        var negOne = new Negate(new Constant(1)); // -1
        var sumTwoFour = new Addition(two, four); // 2 + 4 = 6
        var mult = new Multiplication(sumTwoFour, negOne); // 6 * -1 = -6
        var exp = new Exponent(mult, 2); // -6 * -6 = 36
        var res = new Addition(exp, new Constant(1)); // 36 + 1 = 1 = 37

        // then
        assertThat(negOne.evaluate()).isEqualTo(-1);
        assertThat(sumTwoFour.evaluate()).isEqualTo(6);
        assertThat(mult.evaluate()).isEqualTo(-6);
        assertThat(exp.evaluate()).isEqualTo(36);
        assertThat(res.evaluate()).isEqualTo(37);
    }

    @Test
    @DisplayName("Проверка перегрузки конструкторов")
    public void overloadingTest() {
        var first = new Constant(3);
        var second = new Constant(4);

        // Negate test
        var neg1 = new Negate(first);
        var neg2 = new Negate(first.evaluate());
        assertThat(neg1.evaluate()).isEqualTo(-3);
        assertThat(neg2.evaluate()).isEqualTo(-3);

        // Addition test
        var sum1 = new Addition(first, second);
        var sum2 = new Addition(first.evaluate(), second);
        var sum3 = new Addition(first, second.evaluate());
        var sum4 = new Addition(first.evaluate(), second.evaluate());
        assertThat(sum1.evaluate()).isEqualTo(7);
        assertThat(sum2.evaluate()).isEqualTo(7);
        assertThat(sum3.evaluate()).isEqualTo(7);
        assertThat(sum4.evaluate()).isEqualTo(7);

        // Mutltiplication test
        var mult1 = new Multiplication(first, second);
        var mult2 = new Multiplication(first.evaluate(), second);
        var mult3 = new Multiplication(first, second.evaluate());
        var mult4 = new Multiplication(first.evaluate(), second.evaluate());
        assertThat(mult1.evaluate()).isEqualTo(12);
        assertThat(mult2.evaluate()).isEqualTo(12);
        assertThat(mult3.evaluate()).isEqualTo(12);
        assertThat(mult4.evaluate()).isEqualTo(12);

        // Exponent test
        var exp1 = new Exponent(first, second);
        var exp2 = new Exponent(first.evaluate(), second);
        var exp3 = new Exponent(first, second.evaluate());
        var exp4 = new Exponent(first.evaluate(), second.evaluate());
        assertThat(exp1.evaluate()).isEqualTo(81);
        assertThat(exp2.evaluate()).isEqualTo(81);
        assertThat(exp3.evaluate()).isEqualTo(81);
        assertThat(exp4.evaluate()).isEqualTo(81);
    }

}
