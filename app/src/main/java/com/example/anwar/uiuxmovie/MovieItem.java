package com.example.anwar.uiuxmovie;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.json.JSONObject;

public class MovieItem implements Parcelable {

    private int id;
    private String mov_title;
    private String mov_synopsis;
    private String mov_releasedate;
    private String mov_poster;
    private String mov_rate_count;
    private String mov_rate;
    private String mov_popularity;
    private String mov_poster_backdrop;

    public MovieItem(JSONObject object) {

        try {
            int id = object.getInt("id");
            String title = object.getString("title");
            String synopsis = object.getString("overview");
            String rlsdate = object.getString("release_date");
            String poster = object.getString("poster_path");
            String poster_backdrop = object.getString("backdrop_path");
            String rating = object.getString("vote_average");
            String rating_count = object.getString("vote_count");
            String popularity = object.getString("popularity");

            this.id = id;
            this.mov_title = title;
            this.mov_synopsis = synopsis;
            this.mov_releasedate = rlsdate;
            this.mov_poster = poster;
            this.mov_poster_backdrop = poster_backdrop;
            this.mov_rate = rating;
            this.mov_rate_count = rating_count;
            this.mov_popularity = popularity;


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public MovieItem() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMov_title() {
        return mov_title;
    }

    public void setMov_title(String mov_title) {
        this.mov_title = mov_title;
    }

    public String getMov_synopsis() {
        return mov_synopsis;
    }

    public void setMov_synopsis(String mov_synopsis) {
        this.mov_synopsis = mov_synopsis;
    }

    public String getMov_releasedate() {
        return mov_releasedate;
    }

    public void setMov_releasedate(String mov_releasedate) {
        this.mov_releasedate = mov_releasedate;
    }

    public String getMov_poster() {
        return mov_poster;
    }

    public void setMov_poster(String mov_poster) {
        this.mov_poster = mov_poster;
    }

    public String getMov_poster_backdrop() {
        return mov_poster_backdrop;
    }

    public void setMov_poster_backdrop(String mov_poster_backdrop) {
        this.mov_poster = mov_poster_backdrop;
    }

    public String getMov_popularity() {
        return mov_popularity;
    }

    public void setMov_popularity(String mov_popularity) {
        this.mov_popularity = mov_popularity;
    }


    public String getMov_rate_count() {
        return mov_rate_count;
    }

    public void setMov_rate_count(String mov_rate_count) {
        this.mov_rate_count = mov_rate_count;
    }

    public String getMov_rate() {
        return mov_rate;
    }

    public void setMov_rate(String mov_rate) {
        this.mov_rate = mov_rate;
    }

    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.mov_title);
        dest.writeString(this.mov_synopsis);
        dest.writeString(this.mov_releasedate);
        dest.writeString(this.mov_poster);
        dest.writeString(this.mov_rate_count);
        dest.writeString(this.mov_rate);
        dest.writeString(this.mov_popularity);
        dest.writeString(this.mov_poster_backdrop);
    }
    protected MovieItem(Parcel in) {
        this.id = in.readInt();
        this.mov_title = in.readString();
        this.mov_synopsis = in.readString();
        this.mov_releasedate = in.readString();
        this.mov_poster = in.readString();
        this.mov_rate_count = in.readString();
        this.mov_rate = in.readString();
        this.mov_popularity = in.readString();
        this.mov_poster_backdrop = in.readString();
    }

    public static final Creator<MovieItem> CREATOR = new Creator<MovieItem>() {
        @Override
        public MovieItem createFromParcel(Parcel source) {
            return new MovieItem(source);
        }

        @Override
        public MovieItem[] newArray(int size) {
            return new MovieItem[size];
        }
    };
}
