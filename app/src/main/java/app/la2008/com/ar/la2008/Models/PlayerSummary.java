package app.la2008.com.ar.la2008.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by gdconde on 31/3/17.
 */

public class PlayerSummary implements Parcelable {

    public int index;
    public String name;
    public long secondsPlayed;
    public String tag;
    public int ftm;
    public int fta;
    public int fgm;
    public int fga;
    public int tpm;
    public int tpa;
    public int pts;
    public int reb;
    public int ast;
    public int pf;
    public int rob;
    public int per;
    public int tap;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.index);
        dest.writeString(this.name);
        dest.writeLong(this.secondsPlayed);
        dest.writeString(this.tag);
        dest.writeInt(this.ftm);
        dest.writeInt(this.fgm);
        dest.writeInt(this.tpm);
        dest.writeInt(this.pts);
        dest.writeInt(this.reb);
        dest.writeInt(this.ast);
        dest.writeInt(this.pf);
    }

    public PlayerSummary() {
    }

    protected PlayerSummary(Parcel in) {
        this.index = in.readInt();
        this.name = in.readString();
        this.secondsPlayed = in.readLong();
        this.tag = in.readString();
        this.ftm = in.readInt();
        this.fgm = in.readInt();
        this.tpm = in.readInt();
        this.pts = in.readInt();
        this.reb = in.readInt();
        this.ast = in.readInt();
        this.pf = in.readInt();
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

    @Override
    public boolean equals(Object obj) {
        return obj instanceof PlayerSummary && this.index == ((PlayerSummary) obj).index;
    }
}
