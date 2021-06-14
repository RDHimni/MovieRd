package com.example.movierd.retrofit;

import com.example.movierd.model.AllCategory;
import com.example.movierd.model.BannerMovie;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Rida Dhimni
 * 25/11/2020
 **/

public interface ApiInterface {

    @GET("banner_movie.json")
    Observable<List<BannerMovie>> getAllBanners();

    @GET("{categoryId}/all_movies.json")
    Observable<List<AllCategory>> getAllCategoryMovies(@Path("categoryId") int categoryId);

}
