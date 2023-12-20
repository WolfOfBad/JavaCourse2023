package edu.hw10.randomObjectGenerator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class RandomObjectGenerator {
    private static final Random RANDOM = new Random();
    private static final int NULL_FREQUENCY = 10;
    private static final List<Class<?>> SUPPORTED_TYPES = List.of(
        Integer.class, int.class,
        String.class
        // другие типы
    );

    public Object nextObject(Class<?> className)
        throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        List<Constructor<?>> constructors = Arrays.stream(className.getConstructors())
            .filter(constructor ->
                Arrays.stream(constructor.getParameterTypes())
                .allMatch(SUPPORTED_TYPES::contains))
            .toList();

        if (constructors.isEmpty()) {
            throw new NoSuchMethodException("There is no constructors with supported types");
        }

        Constructor<?> constructor = constructors.get(RANDOM.nextInt(0, constructors.size()));
        return constructor.newInstance(randomParameters(constructor.getParameters()));
    }

    public Object nextObject(Class<?> className, String fabricMethodName)
        throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<Method> methods = Arrays.stream(className.getMethods())
            .filter(method -> method.getName().compareTo(fabricMethodName) == 0)
            .filter(method ->
                Arrays.stream(method.getParameterTypes())
                .allMatch(SUPPORTED_TYPES::contains))
            .filter(method -> Modifier.isStatic(method.getModifiers()))
            .toList();

        if (methods.isEmpty()) {
            throw new NoSuchMethodException("There is no fabric methods with supported types");
        }

        Method fabric = methods.get(RANDOM.nextInt(0, methods.size()));
        return fabric.invoke(className, randomParameters(fabric.getParameters()));
    }

    private String randomString() {
        return UUID.randomUUID().toString();
    }

    @SuppressWarnings("InnerAssignment")
    private Object[] randomParameters(Parameter[] parameters) {
        boolean nullable = true;
        Object[] result = new Object[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            int max = Integer.MAX_VALUE;
            int min = Integer.MIN_VALUE;

            for (Annotation annotation : parameters[i].getAnnotations()) {
                switch (annotation) {
                    case Max maxAnnotation -> max = maxAnnotation.value();
                    case Min minAnnotation -> min = minAnnotation.value();
                    case NotNull ignored -> nullable = false;
                    default -> throw new RuntimeException("You cannot use this annotation: " + annotation);
                }
            }

            result[i] = switch (parameters[i].getType().getName()) {
                case "int", "java.lang.Integer" -> RANDOM.nextInt(min, max);
                case "java.lang.String" -> randomString();

                // логика для создания случайных инстансов других объектов
                // case "any.class" -> randomClassInstance();

                default ->
                    throw new IllegalStateException("Unexpected class name: " + parameters[i].getType().getName());
            };

            // с определенной частотой делает параметр null
            if (nullable
                && RANDOM.nextInt(1, NULL_FREQUENCY) == 1
                && !(result[i] instanceof Number)) {
                result[i] = null;
            }
        }
        return result;
    }
}
