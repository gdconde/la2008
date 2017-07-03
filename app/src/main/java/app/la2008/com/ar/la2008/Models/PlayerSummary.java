package app.la2008.com.ar.la2008.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by gdconde on 31/3/17.
 */

public class PlayerSummary implements Parcelable {

    public String name;
    public String timePlayed;
    public long base;
    public int simplesConversions;
    public int twoPointsConversions;
    public int threePointsConversions;
    public int totalPoints;
    public int rebounds;
    public int assists;
    public int fouls;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.timePlayed);
        dest.writeLong(this.base);
        dest.writeInt(this.simplesConversions);
        dest.writeInt(this.twoPointsConversions);
        dest.writeInt(this.threePointsConversions);
        dest.writeInt(this.totalPoints);
        dest.writeInt(this.rebounds);
        dest.writeInt(this.assists);
        dest.writeInt(this.fouls);
    }

    public PlayerSummary() {
    }

    protected PlayerSummary(Parcel in) {
        this.name = in.readString();
        this.timePlayed = in.readString();
        this.base = in.readLong();
        this.simplesConversions = in.readInt();
        this.twoPointsConversions = in.readInt();
        this.threePointsConversions = in.readInt();
        this.totalPoints = in.readInt();
        this.rebounds = in.readInt();
        this.assists = in.readInt();
        this.fouls = in.readInt();
    }

    public static final Parcelable.Creator<PlayerSummary> CREATOR = new Parcelable.Creator<PlayerSummary>() {
        @Override
        public PlayerSummary createFromParcel(Parcel source) {
            return new PlayerSummary(source);
        }

        @Override
        public PlayerSummary[] newArray(int size) {
            return new PlayerSummary[size];
        }
    };
}
