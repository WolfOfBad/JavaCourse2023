package edu.hw2;

import edu.hw2.SquareAndRectangle.Rectangle;
import edu.hw2.SquareAndRectangle.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SquareAndRectangleTest {
    static Arguments[] rectangles() {
        return new Arguments[] {
            Arguments.of(new Rectangle()),
            Arguments.of(new Square())
        };
    }

    @ParameterizedTest
    @DisplayName("Тест из задания")
    @MethodSource("rectangles")
    void rectangleArea(Rectangle rect) {
        Rectangle rectangle = rect.setWidth(20);
        rectangle = rectangle.setHeight(10);

        assertThat(rectangle.area()).isEqualTo(200.0);
    }

    @Test
    @DisplayName("Проверка вычисления площади квадрата")
    public void squareArea() {
        Square square = new Square();

        square = square.setSides(10);

        // не скомпилируется, потому что методы возвращают Rectangle
        // square = square.setHeight(20);
        // square = square.setWidth(10);

        assertThat(square.area()).isEqualTo(100.0);
    }

}
