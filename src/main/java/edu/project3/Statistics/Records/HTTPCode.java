package edu.project3.Statistics.Records;

public record HTTPCode(
    int code,
    String name
) {
    public HTTPCode(int code) {
        this(code, getCodeName(code));
    }

    @SuppressWarnings("MagicNumber")
    public static String getCodeName(int code) {
        return switch (code) {
            case 200 -> "OK";
            case 206 -> "Partial Content";
            case 304 -> "Not Modified";
            case 403 -> "Forbidden";
            case 404 -> "Not Found";
            case 500 -> "Internal Server Error";
            default -> "-";
        };
    }
}
