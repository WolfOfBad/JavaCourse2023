package edu.hw11;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class DynamicClassCreatorTest {
    private static Arguments[] args() {
        return new Arguments[] {
            Arguments.of(0, 0),
            Arguments.of(1, 1),
            Arguments.of(2, 1),
            Arguments.of(3, 2),
            Arguments.of(4, 3),
            Arguments.of(5, 5),
            Arguments.of(6, 8),
            Arguments.of(7, 13)
        };
    }

    @ParameterizedTest
    @MethodSource("args")
    public void test(int n, long expected)
        throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> cls = DynamicClassCreator.create();

        Method method = cls.getMethod("fib", int.class);
        long result = (long) method.invoke(cls, n);

        assertThat(result).isEqualTo(expected);
    }
}
