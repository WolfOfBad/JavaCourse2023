package edu.project5;

import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

@State(Scope.Thread)
public class ReflectionBenchmark {
    @SuppressWarnings({"MagicNumber", "UncommentedMain"})
    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
            .include(ReflectionBenchmark.class.getSimpleName())
            .shouldFailOnError(true)
            .shouldDoGC(true)
            .mode(Mode.AverageTime)
            .timeUnit(TimeUnit.NANOSECONDS)
            .forks(1)
            .warmupForks(1)
            .warmupIterations(5)
            .warmupTime(TimeValue.seconds(5))
            .measurementIterations(5)
            .measurementTime(TimeValue.seconds(15))
            .build();

        new Runner(options).run();
    }

    private Student student;
    private Method method;
    private MethodHandle methodHandle;
    private Supplier<String> lambdaGetter;

    @SuppressWarnings("MultipleStringLiterals")
    @Setup
    public void setup() throws Throwable {
        student = new Student("Igor", "Mechkov");

        method = student.getClass().getMethod("name");

        MethodType methodType = MethodType.methodType(String.class);
        methodHandle = MethodHandles.publicLookup().findVirtual(Student.class, "name", methodType);

        MethodHandles.Lookup lookup = MethodHandles.lookup();
        CallSite site = LambdaMetafactory.metafactory(
            lookup,
            "get",
            MethodType.methodType(Supplier.class, Student.class),
            MethodType.methodType(Object.class),
            lookup.findVirtual(Student.class, "surname", MethodType.methodType(String.class)),
            MethodType.methodType(String.class)
        );

        lambdaGetter = (Supplier<String>) site.getTarget().invokeExact(student);
    }

    @Benchmark
    public void directAccess(Blackhole bh) {
        String name = student.name();
        bh.consume(name);
    }

    @Benchmark
    public void reflectionAccess(Blackhole bh) throws InvocationTargetException, IllegalAccessException {
        String name = (String) method.invoke(student);
        bh.consume(name);
    }

    @Benchmark
    public void methodHandleAccess(Blackhole bh) throws Throwable {
        String name = (String) methodHandle.invoke(student);
        bh.consume(name);
    }

    @Benchmark
    public void lambdaAccess(Blackhole bh) {
        String name = lambdaGetter.get();
        bh.consume(name);
    }

}
