package edu.hw3;

import java.util.List;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BackwardIteratorTest {

    @Test
    public void iteratorTest() {
        List<Integer> list = List.of(1, 2, 3);

        BackwardIterator<Integer> iterator = new BackwardIterator<>(list);

        assertThat(iterator.next()).isEqualTo(3);
        assertThat(iterator.next()).isEqualTo(2);
        assertThat(iterator.next()).isEqualTo(1);
        assertThat(iterator.hasNext()).isFalse();
    }

}
