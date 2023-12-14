package edu.hw11;

import java.lang.reflect.InvocationTargetException;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.FixedValue;
import static net.bytebuddy.matcher.ElementMatchers.named;

public class HelloByteBuddy {
    private HelloByteBuddy() {
    }

    public static Object createClass()
        throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        return new ByteBuddy()
            .subclass(Object.class)
            .name("HelloPrinter")
            .method(named("toString")).intercept(FixedValue.value("Hello, ByteBuddy!"))
            .make()
            .load(ClassLoader.getSystemClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
            .getLoaded()
            .getConstructor()
            .newInstance();
    }
}
