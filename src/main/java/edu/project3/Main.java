package edu.project3;

public final class Main {
    private Main() {
    }

    public static void main(String[] args) {
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.analyze(args);
    }

}
