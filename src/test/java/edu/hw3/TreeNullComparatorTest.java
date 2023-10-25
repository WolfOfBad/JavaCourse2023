package edu.hw3;

import java.util.TreeMap;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TreeNullComparatorTest {

    @Test
    public void comparatorTest() {
        TreeMap<String, String> tree = new TreeMap<>(new TreeNullComparator<>());

        tree.put("t", "test");
        tree.put(null, "test");
        tree.put("e", "test");

        assertThat(tree.containsKey(null)).isTrue();
    }

}
