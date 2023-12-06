package edu.hw6;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;

public class FileWriter {
    private static final String PHRASE = "Programming is learned by writing programs. ― Brian Kernighan";

    public void writeFile(Path file, String input) throws IOException {
        // create file if not exists
        if (Files.notExists(file)) {
            Files.createFile(file);
        }

        try (OutputStream os = Files.newOutputStream(file);
             CheckedOutputStream checkedOS = new CheckedOutputStream(os, new CRC32());
             BufferedOutputStream bufferedOS = new BufferedOutputStream(checkedOS);
             OutputStreamWriter osWriter = new OutputStreamWriter(bufferedOS, StandardCharsets.UTF_8);
             PrintWriter printWriter = new PrintWriter(osWriter)) {
            printWriter.write(input);
        }
    }

    public void writePhrase(Path file) throws IOException {
        writeFile(file, PHRASE);
    }
}
