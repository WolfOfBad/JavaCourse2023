package edu.hw3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.NotNull;

public class FrequencyDictionary<T> {
    public Map<T, Integer> freqDict(@NotNull List<T> list) {
        Map<T, Integer> result = new HashMap<>();

        for (T item : list) {
            if (!result.containsKey(item)) {
                result.put(item, 0);
            }
            result.replace(item, result.get(item) + 1);
        }

        return result;
    }

}
