package com.example.suensummit.flickster.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.suensummit.flickster.R;
import com.example.suensummit.flickster.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.example.suensummit.flickster.R.id.tvOverview;
import static com.example.suensummit.flickster.R.id.tvTitle;

/**
 * Created by suensummit on 20/02/2017.
 */

public class MovieArrayAdapter extends ArrayAdapter<Movie> {

    public MovieArrayAdapter(Context context, List<Movie> movies) {
        super(context, android.R.layout.simple_list_item_1, movies);
    }

    private static class ViewHolder {
        ImageView ivImage;
        TextView tvTitle;
        TextView tvOverview;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Movie movie = getItem(position);
        ViewHolder viewHolder;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_movie, parent, false);

            viewHolder = new ViewHolder();

            viewHolder.ivImage = (ImageView) convertView.findViewById(R.id.ivMovieImage);
            viewHolder.tvTitle = (TextView) convertView.findViewById(tvTitle);
            viewHolder.tvOverview = (TextView) convertView.findViewById(tvOverview);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

//        ImageView ivImage = (ImageView) convertView.findViewById(R.id.ivMovieImage);
        viewHolder.ivImage.setImageResource(0);

//        TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
//        TextView tvOverview = (TextView) convertView.findViewById(R.id.tvOverview);

        viewHolder.tvTitle.setText(movie.getOriginalTitle());
        viewHolder.tvOverview.setText(movie.getOverview());

        int orientation = getContext().getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            Picasso.with(getContext()).load(movie.getPosterPath()).into(viewHolder.ivImage);
        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Picasso.with(getContext()).load(movie.getBackdropPath()).resize(600, 0).into(viewHolder.ivImage);
        }

//        Picasso.with(getContext()).load(movie.getPosterPath()).into(viewHolder.ivImage);

        return convertView;
    }
}
