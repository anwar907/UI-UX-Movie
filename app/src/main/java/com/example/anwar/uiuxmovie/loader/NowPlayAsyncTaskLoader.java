package com.example.anwar.uiuxmovie.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.example.anwar.uiuxmovie.MovieItem;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

import static com.example.anwar.uiuxmovie.BuildConfig.MOVIE_API_KEY;

public class NowPlayAsyncTaskLoader extends AsyncTaskLoader<ArrayList<MovieItem>> {

    private ArrayList<MovieItem> mMovieItem;
    private boolean mHasResult = false;

    public NowPlayAsyncTaskLoader(final Context context, ArrayList<MovieItem> mMovieItem) {
        super(context);
        onForceLoad();
    }

    @Override
    protected void onStartLoading() {
        if (takeContentChanged())
            forceLoad();
        else if (mHasResult)
            deliverResult(mMovieItem);
    }

    @Override
    public void deliverResult(final ArrayList<MovieItem> data) {
        mMovieItem = data;
        mHasResult = true;
        super.deliverResult(data);
    }

    @Override
    protected void onReset() {
        super.onReset();
        onStartLoading();
        if (mHasResult) {
            onReleaseResource(mMovieItem);
            mMovieItem = null;
            mHasResult = false;
        }
    }

    private void onReleaseResource(ArrayList<MovieItem> mMovieItem) {

    }


    @Override
    public ArrayList<MovieItem> loadInBackground() {
        SyncHttpClient client = new SyncHttpClient();

        final ArrayList<MovieItem> movie_items = new ArrayList<>();
        String url = "https://api.themoviedb.org/3/movie/now_playing?api_key=" + MOVIE_API_KEY + "&language=en-US";

        client.get(url, new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
                super.onStart();
                setUseSynchronousMode(true);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {

                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("results");

                    for (int i = 0; i < list.length(); i++) {
                        JSONObject film = list.getJSONObject(i);
                        MovieItem movieItem = new MovieItem(film);
                        movie_items.add(movieItem);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
        return movie_items;
    }

}
