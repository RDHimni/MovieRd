package com.example.movierd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class MovieDetails extends AppCompatActivity {

    ImageView MovieImage;
    TextView movieName;
    Button Play_button;

    String mName, mImageUrl, mFileUrl;
    Integer mId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        MovieImage = findViewById(R.id.movie_image);
        movieName = findViewById(R.id.movie_name);
        Play_button = findViewById(R.id.start_button);


        mId = (Integer) getIntent().getExtras().get("movieId");
        mName = getIntent().getExtras().getString("movieName");
        mImageUrl = getIntent().getExtras().getString("movieImageUrl");
        mFileUrl = getIntent().getExtras().getString("movieFileUrl");

        Glide.with(this).load(mImageUrl).into(MovieImage);
        movieName.setText(mName);

        Play_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MovieDetails.this, VideoPlayerActivity.class);
                i.putExtra("FileUrl",mFileUrl);
                startActivity(i);
            }
        });

    }
}