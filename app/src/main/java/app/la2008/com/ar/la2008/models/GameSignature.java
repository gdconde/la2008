package app.la2008.com.ar.la2008.models;

import android.os.Parcel;
import android.os.Parcelable;

public class GameSignature implements Parcelable {

    public String key;
    public String name;

    public GameSignature(String key, String name) {
        this.key = key;
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.key);
        dest.writeString(this.name);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof GameSignature
                && ((GameSignature) obj).name.equalsIgnoreCase(this.name)
                && ((GameSignature) obj).key.equalsIgnoreCase(this.key);
    }

    private GameSignature(Parcel in) {
        this.key = in.readString();
        this.name = in.readString();
    }

    public static final Parcelable.Creator<GameSignature> CREATOR = new Parcelable.Creator<GameSignature>() {
        @Override
        public GameSignature createFromParcel(Parcel source) {
            return new GameSignature(source);
        }

        @Override
        public GameSignature[] newArray(int size) {
            return new GameSignature[size];
        }
    };
}
