package edu.hw9;

import edu.hw9.PathFinder.DirectoryFinder;
import java.io.File;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DirectoryFinderTest {

    @Test
    public void directoryFinderTest() {
        DirectoryFinder obj = new DirectoryFinder();

        var result = obj.findDirectories(new File("./src/test/resources/hw9"), 5);

        assertThat(result).size().isEqualTo(1);
        assertThat(result.get(0)).isEqualTo(new File("./src/test/resources/hw9/5files"));
    }

    @Test
    public void predicateFinderTest() {
        DirectoryFinder obj = new DirectoryFinder();

        var result = obj.findFiles(new File("./src/test/resources/hw9"), (file) -> {
            String name = file.toString();

            return name.substring(name.lastIndexOf(".") + 1).compareTo("png") == 0;
        });

        assertThat(result).size().isEqualTo(1);
        assertThat(result.get(0)).isEqualTo(new File("./src/test/resources/hw9/5files/meme.png"));
    }

}
