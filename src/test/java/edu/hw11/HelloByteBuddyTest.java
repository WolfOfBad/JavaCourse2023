package edu.hw11;

import org.junit.jupiter.api.Test;
import java.lang.reflect.InvocationTargetException;
import static org.assertj.core.api.Assertions.assertThat;

public class HelloByteBuddyTest {
    @Test
    public void test()
        throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        Object object = HelloByteBuddy.createClass();

        assertThat(object.toString()).isEqualTo("Hello, ByteBuddy!");
    }
}
