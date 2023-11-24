package edu.hw6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DiskMap implements Map<String, String> {
    private final Path directory;
    private final static int DEFAULT_CACHE_SIZE = 50;
    private final static Pattern ENTRY_PATTERN = Pattern.compile("^([^:]*):([^:]*)$");
    private int cacheSize = DEFAULT_CACHE_SIZE;
    private final Map<String, String> cache = new LinkedHashMap<>(50);

    /**
     * Создает .txt файл в котором хранятся записи в формате ключ:значение по указанному пути
     */
    public DiskMap(Path directory) throws IOException {
        if (Files.notExists(directory)) {
            Files.createFile(directory);
        }
        this.directory = directory;
    }

    public void setCacheSize(int size) {
        cacheSize = size;
    }

    private Entry<String, String> convertStringToEntry(String entry) {
        Matcher matcher = ENTRY_PATTERN.matcher(entry);
        if (matcher.find()) {
            return Map.entry(matcher.group(1), matcher.group(2));
        }
        return null;
    }

    @Override
    public int size() {
        if (cache.size() < cacheSize) {
            return cache.size();
        }

        try (Stream<String> lines = Files.lines(directory)) {
            return (int) lines.count();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isEmpty() {
        return cache.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        if (cache.containsKey(key)) {
            return true;
        }

        String keyString = (String) key;

        try (Stream<String> entries = Files.lines(directory)) {
            var foundEntry = entries
                .map(this::convertStringToEntry)
                .filter(entry -> entry != null && keyString.equals(entry.getKey()))
                .findAny();
            return foundEntry.isPresent();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean containsValue(Object value) {
        if (cache.containsValue(value)) {
            return true;
        }

        String valueString = (String) value;

        try (Stream<String> entries = Files.lines(directory)) {
            var foundEntry = entries
                .map(this::convertStringToEntry)
                .filter(entry -> entry != null && valueString.equals(entry.getValue()))
                .findAny();
            return foundEntry.isPresent();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String get(Object key) {
        if (cache.get(key) != null) {
            return cache.get(key);
        }

        String keyString = (String) key;

        try (Stream<String> entries = Files.lines(directory)) {
            var foundEntry = entries
                .map(this::convertStringToEntry)
                .filter(entry -> entry != null && keyString.equals(entry.getKey()))
                .findAny();
            return foundEntry
                .map(Entry::getValue)
                .orElse(null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String findValueInFileLines(String key, Stream<String> lines) {
        for (String line : lines.toList()) {
            Entry<String, String> entry = convertStringToEntry(line);
            if (entry != null && entry.getKey().compareTo(key) == 0) {
                return entry.getValue();
            }
        }
        return null;
    }

    @Nullable
    @Override
    public String put(String key, String value) {
        if (cache.size() == cacheSize && !cache.containsKey(key)) {
            ((LinkedHashMap<String, String>) cache).pollFirstEntry();
        }

        String previousValue = ((LinkedHashMap<String, String>) cache).putLast(key, value);
        try (Stream<String> lines = Files.lines(directory)) {
            if (previousValue == null) {
                previousValue = findValueInFileLines(key, lines);
            }

            String entries = new String(Files.readAllBytes(directory));
            if (previousValue != null) {
                entries = entries.replaceAll(key + ":" + previousValue, key + ":" + value);
            } else {
                entries = entries.concat(key + ":" + value + "\n");
            }

            Files.write(directory, entries.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return previousValue;
    }

    @Override
    public String remove(Object key) {
        String previousValue = cache.remove(key);
        try (Stream<String> lines = Files.lines(directory)) {
            if (previousValue == null) {
                for (String line : lines.toList()) {
                    Entry<String, String> entry = convertStringToEntry(line);
                    if (entry != null && entry.getKey().equals(key)) {
                        previousValue = entry.getValue();
                        break;
                    }
                }
            }

            String entries = new String(Files.readAllBytes(directory));
            if (previousValue != null) {
                entries = entries.replaceAll(key + ":" + previousValue + "\n", "");
            }

            Files.write(directory, entries.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return previousValue;
    }

    @Override
    public void putAll(@NotNull Map<? extends String, ? extends String> m) {
        for (var entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void clear() {
        cache.clear();
        try {
            Files.write(directory, "".getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @NotNull
    @Override
    public Set<String> keySet() {
        if (cache.size() < cacheSize) {
            return cache.keySet();
        }

        Set<String> result = new HashSet<>();
        try (Stream<String> lines = Files.lines(directory)) {
            lines.map(entry -> Objects.requireNonNull(convertStringToEntry(entry)).getKey())
                .forEach(result::add);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @NotNull
    @Override
    public Collection<String> values() {
        if (cache.size() < cacheSize) {
            return cache.values();
        }

        List<String> result = new ArrayList<>();
        try (Stream<String> lines = Files.lines(directory)) {
            lines.map(entry -> Objects.requireNonNull(convertStringToEntry(entry)).getValue())
                .forEach(result::add);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @NotNull
    @Override
    public Set<Entry<String, String>> entrySet() {
        if (cache.size() < cacheSize) {
            return cache.entrySet();
        }

        Set<Entry<String, String>> result = new HashSet<>();
        try (Stream<String> lines = Files.lines(directory)) {
            lines.map(this::convertStringToEntry)
                .filter(Objects::nonNull)
                .forEach(result::add);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
