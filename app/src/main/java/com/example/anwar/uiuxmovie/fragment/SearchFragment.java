package com.example.anwar.uiuxmovie.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.anwar.uiuxmovie.DetailActivity;
import com.example.anwar.uiuxmovie.MovieItem;
import com.example.anwar.uiuxmovie.R;
import com.example.anwar.uiuxmovie.adapter.MovieAdapter;
import com.example.anwar.uiuxmovie.loader.MovieAsyncTaskLoader;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment implements LoaderManager.LoaderCallbacks<ArrayList<MovieItem>> {

    ImageView ivPoster;
    ListView listView;
    MovieAdapter adapter;
    EditText etTitle;
    Button btnSearch;


    static final String EXTRAS_FILM = "EXTRAS_FILM";


    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        adapter = new MovieAdapter(getActivity());
        adapter.notifyDataSetChanged();

        listView = view.findViewById(R.id.listView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                MovieItem item = (MovieItem) parent.getItemAtPosition(position);

                Intent intent = new Intent(getActivity(), DetailActivity.class);

                intent.putExtra(DetailActivity.EXTRA_TITLE, item.getMov_title());
                intent.putExtra(DetailActivity.EXTRA_RELEASE_DATE, item.getMov_releasedate());
                intent.putExtra(DetailActivity.EXTRA_OVERVIEW, item.getMov_rate());
                intent.putExtra(DetailActivity.EXTRA_POPULAR, item.getMov_popularity());
                intent.putExtra(DetailActivity.EXTRA_SYNOPSIS, item.getMov_synopsis());
                intent.putExtra(DetailActivity.EXTRA_RATE_COUNT, item.getMov_rate_count());
                intent.putExtra(DetailActivity.EXTRA_POSTER_BACKDROP, item.getMov_poster());
                intent.putExtra(DetailActivity.EXTRA_RATE_COUNT, item.getMov_rate_count());
                intent.putExtra(DetailActivity.EXTRA_POSTER_JPG, item.getMov_poster());

                startActivity(intent);


            }
        });

        etTitle = view.findViewById(R.id.et_cari_film);
        ivPoster = view.findViewById(R.id.poster_mov);
        btnSearch = view.findViewById(R.id.btn_cari_film);
        btnSearch.setOnClickListener(myListener);

        String title = etTitle.getText().toString();

        Bundle bundle = new Bundle();
        bundle.putString(EXTRAS_FILM, title);

        getLoaderManager().initLoader(0, bundle, this);


        return view;
    }


    View.OnClickListener myListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String titleMovie = etTitle.getText().toString();

            if (TextUtils.isEmpty(titleMovie)) return;

            Bundle bundle = new Bundle();
            bundle.putString(EXTRAS_FILM, titleMovie);
            getLoaderManager().restartLoader(0, bundle, SearchFragment.this);
        }
    };


    @Override
    public Loader<ArrayList<MovieItem>> onCreateLoader(int i, Bundle bundle) {
        String titleMovie = "";
        if (bundle != null) {
            titleMovie = bundle.getString(EXTRAS_FILM);
        }
        return new MovieAsyncTaskLoader(getActivity(), titleMovie);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<MovieItem>> loader, ArrayList<MovieItem> data) {

        adapter.setData(data);
    }


    @Override
    public void onLoaderReset(Loader<ArrayList<MovieItem>> loader) {
        adapter.setData(null);
    }
}