package edu.hw3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.NotNull;

public class FrequencyDictionary {

    public Map<Object, Integer> freqDict(@NotNull List<Object> list) {
        Map<Object, Integer> result = new HashMap<>();

        for (Object item : list) {
            if (!result.containsKey(item)) {
                result.put(item, 0);
            }
            result.replace(item, result.get(item) + 1);
        }

        return result;
    }

}
