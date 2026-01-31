package util;

public final class TimeFormat {
    private TimeFormat() {}

    public static String formatSeconds(long totalSeconds) {
        if (totalSeconds < 0) totalSeconds = 0;

        long hours = totalSeconds / 3600;
        long rem = totalSeconds % 3600;
        long minutes = rem / 60;
        long seconds = rem % 60;

        if (hours > 0) {
            return String.format("%02d:%02d:%02d", hours, minutes, seconds);
        }
        return String.format("%02d:%02d", minutes, seconds);
    }
}

