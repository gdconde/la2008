package app.la2008.com.ar.la2008.util;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by gdconde on 7/11/17.
 */

public class Utils {

    private static FirebaseDatabase mDatabase;

    public static FirebaseDatabase getDatabase() {
        if (mDatabase == null) {
            mDatabase = FirebaseDatabase.getInstance();
            mDatabase.setPersistenceEnabled(true);
        }
        return mDatabase;
    }

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

}
