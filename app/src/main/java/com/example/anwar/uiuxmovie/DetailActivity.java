package com.example.anwar.uiuxmovie;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.provider.BaseColumns._ID;
import static android.provider.UserDictionary.Words.CONTENT_URI;

public class DetailActivity extends AppCompatActivity {

    public static String EXTRA_ID = "extra_id";
    public static String EXTRA_TITLE = "extra_title";
    public static String EXTRA_RELEASE_DATE = "extra_release_date";
    public static String EXTRA_OVERVIEW = "extra_overview";
    public static String EXTRA_POSTER_BACKDROP = "extra_poster_backdrop";
    public static String EXTRA_POPULAR = "extra_popular";
    public static String EXTRA_SYNOPSIS = "extra_synopsis";
    public static String EXTRA_RATE_COUNT = "extra_rate_count";
    public static String EXTRA_POSTER_JPG = "extra_poster_jpg";



    private Context context;
    private Boolean isFav = false;

    @BindView(R.id.title_mov_detail)
    TextView tvTitle;

    @BindView(R.id.rate_mov_detail)
    TextView tvOverview;

    @BindView(R.id.release_date_detail)
    TextView tvReleaseDate;

    @BindView(R.id.synopsis_mov_detail)
    TextView tvSynopsis;

    @BindView(R.id.popular_mov_detail)
    TextView tvPopular;

    @BindView(R.id.rate_mov_count)
    TextView tvRateCount;

    @BindView(R.id.poster_mov_detail)
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);


        final String title = getIntent().getStringExtra(EXTRA_TITLE);
        final String release_date = getIntent().getStringExtra(EXTRA_RELEASE_DATE);
        final String overview = getIntent().getStringExtra(EXTRA_OVERVIEW);
        final String popular = getIntent().getStringExtra(EXTRA_POPULAR);
        final String rate_count = getIntent().getStringExtra(EXTRA_RATE_COUNT);
        final String synopsis = getIntent().getStringExtra(EXTRA_SYNOPSIS);
        final String poster_backdrop = getIntent().getStringExtra(EXTRA_POSTER_BACKDROP);
        final int id_movie = getIntent().getIntExtra(EXTRA_ID,0);
        final String poster_jpg = getIntent().getStringExtra(EXTRA_POSTER_JPG);

        SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = date_format.parse(release_date);

            SimpleDateFormat new_date_format = new SimpleDateFormat("EEEE, dd MMMM yyyy");
            String date_of_release = new_date_format.format(date);
            tvReleaseDate.setText(date_of_release);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        tvTitle.setText(title);
        tvOverview.setText(overview);
        tvPopular.setText(popular);
        tvSynopsis.setText(synopsis);
        tvRateCount.setText(rate_count);
        Picasso.with(context).load("http://image.tmdb.org/t/p/w500/" + poster_backdrop).into(imageView);

        setUpToolbar();

    }


    private void setUpToolbar() {

        String title = getIntent().getStringExtra(EXTRA_TITLE);

        Toolbar tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);


        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(title);


        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
    }
}