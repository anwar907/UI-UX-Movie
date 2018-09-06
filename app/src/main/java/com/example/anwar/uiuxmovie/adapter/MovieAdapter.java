package com.example.anwar.uiuxmovie.adapter;


import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.anwar.uiuxmovie.MovieItem;
import com.example.anwar.uiuxmovie.R;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MovieAdapter extends BaseAdapter {

    private ArrayList<MovieItem> mData = new ArrayList<>();
    private LayoutInflater mInflater;
    private Context context;

    private String final_overview;

    public MovieAdapter(Context context) {
        this.context = context;
        mInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(ArrayList<MovieItem> item) {
        mData = item;
        notifyDataSetChanged();
    }

    public void addItem(final MovieItem item) {
        mData.add(item);
        notifyDataSetChanged();
    }

    public void clearData() {
        mData.clear();
    }

    @Override
    public int getItemViewType(int positition) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public int getCount() {
        if (mData == null) return 0;
        return mData.size();
    }

    @Override
    public MovieItem getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.list_movie_items, null);
            holder.tv_poster_movie = convertView.findViewById(R.id.poster_mov);
            holder.tv_title_movie = convertView.findViewById(R.id.title_mov);
            holder.tv_synopsis_movie = convertView.findViewById(R.id.synopsis_mov);
            holder.tv_rlsdate_movie = convertView.findViewById(R.id.release_date);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_title_movie.setText(mData.get(position).getMov_title());
        String overview = mData.get(position).getMov_synopsis();
        if (TextUtils.isEmpty(overview)) {
            final_overview = "No Data";
        } else {
            final_overview = overview;
        }
        holder.tv_synopsis_movie.setText(final_overview);

        String retrieveDate = mData.get(position).getMov_releasedate();
        SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date date = date_format.parse(retrieveDate);
            SimpleDateFormat new_date_format = new SimpleDateFormat("EEEE, dd MMMM yyyy");
            String release_date = new_date_format.format(date);
            holder.tv_rlsdate_movie.setText(release_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Picasso.with(context).load("http://image.tmdb.org/t/p/w154/" + mData.get(position).getMov_poster())
                .placeholder(context.getResources()
                        .getDrawable(R.drawable.film))
                .error(context.getResources()
                        .getDrawable(R.drawable.film))
                .into(holder.tv_poster_movie);

        return convertView;
    }

    public static class ViewHolder {
        ImageView tv_poster_movie;
        TextView tv_title_movie;
        TextView tv_synopsis_movie;
        TextView tv_rlsdate_movie;
    }
}