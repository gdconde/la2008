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

}
