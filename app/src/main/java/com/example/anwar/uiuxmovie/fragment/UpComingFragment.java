package com.example.anwar.uiuxmovie.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anwar.uiuxmovie.MovieItem;
import com.example.anwar.uiuxmovie.R;
import com.example.anwar.uiuxmovie.adapter.NowUpAdapter;
import com.example.anwar.uiuxmovie.loader.UpComAsyncTaskLoader;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class UpComingFragment extends Fragment implements LoaderManager.LoaderCallbacks<ArrayList<MovieItem>> {


    NowUpAdapter adapter;
    Context context;
    RecyclerView mRecyclerView;
    private ArrayList<MovieItem> upComingData;

    public UpComingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_up_coming, container, false);
        context = view.getContext();

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_up_coming);

        adapter = new NowUpAdapter(getActivity());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.setAdapter(adapter);

        getLoaderManager().initLoader(0, null, this);
        return view;

    }

    @Override
    public Loader<ArrayList<MovieItem>> onCreateLoader(int id, Bundle args) {
        return new UpComAsyncTaskLoader(getContext(), upComingData);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<MovieItem>> loader, ArrayList<MovieItem> upComingData) {
        adapter.setData(upComingData);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<MovieItem>> loader) {
        adapter.setData(null);
    }


}