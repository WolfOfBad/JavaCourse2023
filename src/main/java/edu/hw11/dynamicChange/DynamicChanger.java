package edu.hw11.dynamicChange;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.pool.TypePool;
import static net.bytebuddy.implementation.MethodDelegation.to;
import static net.bytebuddy.matcher.ElementMatchers.isStatic;
import static net.bytebuddy.matcher.ElementMatchers.named;
import static net.bytebuddy.matcher.ElementMatchers.returns;
import static net.bytebuddy.matcher.ElementMatchers.takesArgument;

public final class DynamicChanger {
    private DynamicChanger() {
    }

    public static void change() {
        ByteBuddyAgent.install();

        TypeDescription typeDescription = TypePool.Default.ofSystemLoader()
            .describe("edu.hw11.dynamicChange.ArithmeticUtils")
            .resolve();

        new ByteBuddy()
            .redefine(typeDescription, ClassFileLocator.ForClassLoader.ofSystemLoader())
            .method(named("sum")
                .and(takesArgument(0, int.class))
                .and(takesArgument(1, int.class))
                .and(returns(int.class))
                .and(isStatic()))
            .intercept(to(Interceptor.class))
            .make()
            .load(ArithmeticUtils.class.getClassLoader(), ClassReloadingStrategy.fromInstalledAgent());
    }

    private static class Interceptor {
        public static int multiplication(int a, int b) {
            return a * b;
        }
    }
}
