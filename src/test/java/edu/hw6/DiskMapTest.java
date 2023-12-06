package edu.hw6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.CleanupMode;
import org.junit.jupiter.api.io.TempDir;
import static org.assertj.core.api.Assertions.assertThat;

public class DiskMapTest {
    @Test
    @DisplayName("size()")
    public void sizeTest(@TempDir(cleanup = CleanupMode.ALWAYS) Path dir) throws IOException {
        DiskMap map = new DiskMap(Path.of(dir.toString(), "TestDiskMap.txt"));
        map.setCacheSize(0);

        map.put("1", "1");
        map.put("2", "2");
        map.put("3", "3");

        assertThat(map.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("isEmpty()")
    public void isEmptyTest(@TempDir(cleanup = CleanupMode.ALWAYS) Path dir) throws IOException {
        DiskMap emptyMap = new DiskMap(Path.of(dir.toString(), "TestEmptyDiskMap.txt"));
        DiskMap filledMap = new DiskMap(Path.of(dir.toString(), "TestFilledDiskMap.txt"));
        emptyMap.setCacheSize(0);
        filledMap.setCacheSize(0);

        filledMap.put("1", "1");

        assertThat(emptyMap.isEmpty()).isTrue();
        assertThat(filledMap.isEmpty()).isFalse();
    }

    @Test
    @DisplayName("containsKey()")
    public void containsKeyTest(@TempDir(cleanup = CleanupMode.ALWAYS) Path dir) throws IOException {
        DiskMap map = new DiskMap(Path.of(dir.toString(), "TestDiskMap.txt"));
        map.setCacheSize(0);

        map.put("a", "b");

        assertThat(map.containsKey("a")).isTrue();
        assertThat(map.containsKey("b")).isFalse();
    }

    @Test
    @DisplayName("containsValue()")
    public void containsValueTest(@TempDir(cleanup = CleanupMode.ALWAYS) Path dir) throws IOException {
        DiskMap map = new DiskMap(Path.of(dir.toString(), "TestDiskMap.txt"));
        map.setCacheSize(0);

        map.put("a", "b");

        assertThat(map.containsValue("b")).isTrue();
        assertThat(map.containsValue("a")).isFalse();
    }

    @Test
    @DisplayName("get()")
    public void getTest(@TempDir(cleanup = CleanupMode.ALWAYS) Path dir) throws IOException {
        DiskMap map = new DiskMap(Path.of(dir.toString(), "TestDiskMap.txt"));
        map.setCacheSize(0);

        map.put("a", "b");

        assertThat(map).size().isEqualTo(1);
        assertThat(map).contains(Map.entry("a", "b"));
    }

    @Test
    @DisplayName("put()")
    public void putTest(@TempDir(cleanup = CleanupMode.ALWAYS) Path dir) throws IOException {
        Path file = Path.of(dir.toString(), "TestDiskMap.txt");
        DiskMap map = new DiskMap(file);
        map.setCacheSize(0);

        map.put("1", "1");
        map.put("2", "2");

        List<String> lines = Files.readAllLines(file);

        assertThat(lines.get(0)).isEqualTo("1:1");
        assertThat(lines.get(1)).isEqualTo("2:2");
    }

    @Test
    @DisplayName("remove()")
    public void removeTest(@TempDir(cleanup = CleanupMode.ALWAYS) Path dir) throws IOException {
        Path file = Path.of(dir.toString(), "TestDiskMap.txt");
        DiskMap map = new DiskMap(file);
        map.setCacheSize(0);

        map.put("1", "1");
        map.put("2", "2");
        map.remove("1");

        List<String> lines = Files.readAllLines(file);

        assertThat(lines.size()).isEqualTo(1);
        assertThat(lines.get(0)).isEqualTo("2:2");
    }

    @Test
    @DisplayName("putAll()")
    public void putAllTest(@TempDir(cleanup = CleanupMode.ALWAYS) Path dir) throws IOException {
        Map<String, String> items = Map.of("1", "1", "2", "2");
        DiskMap map = new DiskMap(Path.of(dir.toString(), "TestDiskMap.txt"));
        map.setCacheSize(0);

        map.putAll(items);

        for (var entry : items.entrySet()) {
            assertThat(map.get(entry.getKey())).isEqualTo(entry.getValue());
        }
    }

    @Test
    @DisplayName("clear()")
    public void clearTest(@TempDir(cleanup = CleanupMode.ALWAYS) Path dir) throws IOException {
        Path file = Path.of(dir.toString(), "TestDiskMap.txt");
        DiskMap map = new DiskMap(file);
        map.setCacheSize(0);

        map.put("1", "1");
        map.put("2", "2");

        map.clear();

        assertThat(Files.size(file)).isEqualTo(0);
    }

    @Test
    @DisplayName("keySet()")
    public void keySetTest(@TempDir(cleanup = CleanupMode.ALWAYS) Path dir) throws IOException {
        DiskMap map = new DiskMap(Path.of(dir.toString(), "TestDiskMap.txt"));
        map.setCacheSize(0);

        map.put("1", "3");
        map.put("2", "4");

        var set = map.keySet();

        assertThat(set.size()).isEqualTo(2);
        assertThat(set.contains("1")).isTrue();
        assertThat(set.contains("2")).isTrue();
    }

    @Test
    @DisplayName("values()")
    public void valuesTest(@TempDir(cleanup = CleanupMode.ALWAYS) Path dir) throws IOException {
        DiskMap map = new DiskMap(Path.of(dir.toString(), "TestDiskMap.txt"));
        map.setCacheSize(0);

        map.put("1", "3");
        map.put("2", "4");

        var set = map.values();

        assertThat(set.size()).isEqualTo(2);
        assertThat(set.contains("3")).isTrue();
        assertThat(set.contains("4")).isTrue();
    }

    @Test
    @DisplayName("entrySet()")
    public void entrySetTest(@TempDir(cleanup = CleanupMode.ALWAYS) Path dir) throws IOException {
        DiskMap map = new DiskMap(Path.of(dir.toString(), "TestDiskMap.txt"));
        map.setCacheSize(0);

        map.put("1", "3");
        map.put("2", "4");

        var set = map.entrySet();

        assertThat(set.size()).isEqualTo(2);
        assertThat(set.contains(Map.entry("1", "3"))).isTrue();
        assertThat(set.contains(Map.entry("1", "3"))).isTrue();
    }
}
