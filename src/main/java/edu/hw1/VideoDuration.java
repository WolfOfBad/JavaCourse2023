package edu.hw1;

public final class VideoDuration {
    private VideoDuration() {
    }

    private static final int SECONDS_IN_MINUTE = 60;

    public static int minutesToSeconds(String timeFormat) {
        String[] time = timeFormat.split(":");
        if (time.length != 2 || time[1].length() != 2) {
            return -1;
        }

        int minutes = Integer.parseInt(time[0]);
        int seconds = Integer.parseInt(time[1]);

        if (seconds >= SECONDS_IN_MINUTE) {
            return -1;
        }
        return minutes * SECONDS_IN_MINUTE + seconds;
    }
}
