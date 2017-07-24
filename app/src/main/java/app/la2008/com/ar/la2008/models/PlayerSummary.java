package app.la2008.com.ar.la2008.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by gdconde on 31/3/17.
 */

public class PlayerSummary implements Parcelable {

    public String key;
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
    public int stl;
    public int tov;
    public int blk;

    @Override
    public boolean equals(Object obj) {
        return obj instanceof PlayerSummary && this.index == ((PlayerSummary) obj).index;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.key);
        dest.writeInt(this.index);
        dest.writeString(this.name);
        dest.writeLong(this.secondsPlayed);
        dest.writeString(this.tag);
        dest.writeInt(this.ftm);
        dest.writeInt(this.fta);
        dest.writeInt(this.fgm);
        dest.writeInt(this.fga);
        dest.writeInt(this.tpm);
        dest.writeInt(this.tpa);
        dest.writeInt(this.pts);
        dest.writeInt(this.reb);
        dest.writeInt(this.ast);
        dest.writeInt(this.pf);
        dest.writeInt(this.stl);
        dest.writeInt(this.tov);
        dest.writeInt(this.blk);
    }

    public PlayerSummary() {
    }

    private PlayerSummary(Parcel in) {
        this.key = in.readString();
        this.index = in.readInt();
        this.name = in.readString();
        this.secondsPlayed = in.readLong();
        this.tag = in.readString();
        this.ftm = in.readInt();
        this.fta = in.readInt();
        this.fgm = in.readInt();
        this.fga = in.readInt();
        this.tpm = in.readInt();
        this.tpa = in.readInt();
        this.pts = in.readInt();
        this.reb = in.readInt();
        this.ast = in.readInt();
        this.pf = in.readInt();
        this.stl = in.readInt();
        this.tov = in.readInt();
        this.blk = in.readInt();
    }

    public static final Creator<PlayerSummary> CREATOR = new Creator<PlayerSummary>() {
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
