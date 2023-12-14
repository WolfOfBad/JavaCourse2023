package edu.hw10;

import edu.hw10.randomObjectGenerator.Max;
import edu.hw10.randomObjectGenerator.Min;
import edu.hw10.randomObjectGenerator.NotNull;
import edu.hw10.randomObjectGenerator.RandomObjectGenerator;
import java.lang.reflect.InvocationTargetException;
import org.junit.jupiter.api.RepeatedTest;
import static org.assertj.core.api.Assertions.assertThat;

public class RandomObjectGeneratorTest {
    public static class TestClass {
        public final int value;
        public final String string;

        public TestClass(
            @Max(value = 100)
            @Min(value = -100)
            int value,
            @NotNull String string
        ) {
            this.value = value;
            this.string = string;
        }
    }

    public record TestRecord(
        @Max(value = 100)
        @Min(value = -100)
        int value,
        @NotNull String string
    ) {
    }

    @RepeatedTest(10)
    public void classCreationTest()
        throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        RandomObjectGenerator rog = new RandomObjectGenerator();

        TestClass obj = (TestClass) rog.nextObject(TestClass.class);

        assertThat(obj.value).isGreaterThanOrEqualTo(-100).isLessThanOrEqualTo(100);
        assertThat(obj.string).isNotNull();
    }

    @RepeatedTest(10)
    public void recordCreationTest()
        throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        RandomObjectGenerator rog = new RandomObjectGenerator();

        TestRecord obj = (TestRecord) rog.nextObject(TestRecord.class);

        assertThat(obj.value).isGreaterThanOrEqualTo(-100).isLessThanOrEqualTo(100);
        assertThat(obj.string).isNotNull();
    }

}
