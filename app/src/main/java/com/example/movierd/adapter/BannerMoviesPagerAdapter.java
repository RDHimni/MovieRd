package com.example.movierd.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.movierd.MovieDetails;
import com.example.movierd.R;
import com.example.movierd.model.BannerMovie;

import java.util.List;

/**
 * Rida Dhimni
 * 23/11/2020
 **/

public class BannerMoviesPagerAdapter extends PagerAdapter {

    Context context;
    List<BannerMovie> items;

    public BannerMoviesPagerAdapter(Context context, List<BannerMovie> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
        }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container,final int position) {

        View view = LayoutInflater.from(context).inflate(R.layout.banner_movies_layout,null);
        ImageView bannerImage = view.findViewById(R.id.banner_view_image);

        Glide.with(context).load(items.get(position).getImageUrl()).into(bannerImage);
        container.addView(view);

        bannerImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(context,""+items.get(position).getMovieName(),Toast.LENGTH_SHORT).show();

                Intent i = new Intent(context, MovieDetails.class);
                i.putExtra("movieId",items.get(position).getId());
                i.putExtra("movieName",items.get(position).getMovieName());
                i.putExtra("movieImageUrl",items.get(position).getImageUrl());
                i.putExtra("movieFileUrl",items.get(position).getFileUrl());
                context.startActivity(i);


            }
        });

        return view;
    }
}
