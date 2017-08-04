package app.la2008.com.ar.la2008.util;

import android.support.annotation.NonNull;

import com.google.firebase.database.FirebaseDatabase;

public class Utils {

    private static FirebaseDatabase mDatabase;

    public static FirebaseDatabase getDatabase() {
        if (mDatabase == null) {
            mDatabase = FirebaseDatabase.getInstance();
            mDatabase.setPersistenceEnabled(true);
        }
        return mDatabase;
    }

    @NonNull
    public static String secondsToString(long secondsPlayed) {
        StringBuilder builder = new StringBuilder();
        int minutes = (int) secondsPlayed / 60;
        int seconds = (int) secondsPlayed % 60;
        if (minutes < 10) builder.append("0");
        builder.append(minutes);
        builder.append(":");
        if (seconds < 10) builder.append("0");
        builder.append(seconds);
        return builder.toString();
    }

    @NonNull
    public static String gameTimeInSecondsToHumanString(long timeInSeconds) {
        long remainingInSeconds;
        String quarter = "1st";
        if (timeInSeconds < 600) {
            remainingInSeconds = 600 - timeInSeconds;
        } else if (timeInSeconds < 1200) {
            remainingInSeconds = 1200 - timeInSeconds;
            quarter = "2nd";
        } else if (timeInSeconds < 1800) {
            remainingInSeconds = 1800 - timeInSeconds;
            quarter = "3rd";
        } else {
            remainingInSeconds = 2400 - timeInSeconds;
            quarter = "4th";
        }
        long timeOfQuarterRemainingMinutes = remainingInSeconds / 60;
        long timeOfQuarterRemainingSeconds = remainingInSeconds % 60;

        StringBuilder builder = new StringBuilder();
        builder.append(quarter);
        builder.append(" Q - ");
        if (timeOfQuarterRemainingMinutes < 10) { builder.append("0"); }
        builder.append(timeOfQuarterRemainingMinutes);
        builder.append(":");
        if (timeOfQuarterRemainingSeconds < 10) { builder.append("0"); }
        builder.append(timeOfQuarterRemainingSeconds);

        return builder.toString();
    }

}
