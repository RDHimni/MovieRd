package com.example.movierd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import android.view.WindowManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.example.movierd.adapter.BannerMoviesPagerAdapter;
import com.example.movierd.adapter.MainRecyclerAdapter;
import com.example.movierd.model.AllCategory;
import com.example.movierd.model.BannerMovie;
import com.example.movierd.model.CategoryItemList;
import com.example.movierd.retrofit.RetrofitClient;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {

    BannerMoviesPagerAdapter bannerMoviesPagerAdapter;
    TabLayout indecatorTab, categroyTab;

    ViewPager bannerMoviesViewPager;
    List<BannerMovie> homeBannerList;
    List<BannerMovie> tvShiowBannerList;
    List<BannerMovie> moviesBannerList;
    List<BannerMovie> kidsBannerList;

    MainRecyclerAdapter mainRecyclerAdapter;
    RecyclerView mainRecycler;

    List<AllCategory> allCategories;

    Toolbar toolbar;

    NestedScrollView nestedScrollView;

    AppBarLayout appBarLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       // getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        indecatorTab = findViewById(R.id.tab_indicator);
        categroyTab = findViewById(R.id.cat_tabLayout);
        nestedScrollView = findViewById(R.id.NestedScrollView);
        appBarLayout = findViewById(R.id.appbar);


        homeBannerList = new ArrayList<>();
        tvShiowBannerList = new ArrayList<>();
        moviesBannerList = new ArrayList<>();
        kidsBannerList = new ArrayList<>();

        //fetch data from server
        getBannerData();
        getAllCategoryMoviesData(1);
        categroyTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                switch (tab.getPosition()) {

                    case 0:
                        setDefaultStateNestedScrollView();
                        setBannerMoviesPagerAdapter(homeBannerList);
                        getAllCategoryMoviesData(1);

                        return;
                    case 1:
                        setDefaultStateNestedScrollView();
                        setBannerMoviesPagerAdapter(tvShiowBannerList);
                        getAllCategoryMoviesData(2);

                        return;

                    case 2:
                        setDefaultStateNestedScrollView();
                        setBannerMoviesPagerAdapter(moviesBannerList);
                        getAllCategoryMoviesData(3);

                        return;

                    case 3:
                        setDefaultStateNestedScrollView();
                        setBannerMoviesPagerAdapter(kidsBannerList);
                        getAllCategoryMoviesData(4);

                        return;

                    default:
                        setDefaultStateNestedScrollView();
                        setBannerMoviesPagerAdapter(homeBannerList);
                        getAllCategoryMoviesData(1);



                }


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


       // allCategories = new ArrayList<>();




    }

    private void setBannerMoviesPagerAdapter(List<BannerMovie> items) {
        bannerMoviesViewPager = findViewById(R.id.banner_viewPager);
        bannerMoviesPagerAdapter = new BannerMoviesPagerAdapter(this, items);
        bannerMoviesViewPager.setAdapter(bannerMoviesPagerAdapter);
        indecatorTab.setupWithViewPager(bannerMoviesViewPager);

        Timer slideTimer = new Timer();
        slideTimer.scheduleAtFixedRate(new AutoSlider(), 6000, 6000);
        Handler handler = new Handler();


        indecatorTab.setupWithViewPager(bannerMoviesViewPager, true);

    }


    public void setMainRecycler(List<AllCategory> allCategoriesList) {

        mainRecycler = findViewById(R.id.cat_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        mainRecycler.setLayoutManager(layoutManager);
        mainRecyclerAdapter = new MainRecyclerAdapter(this, allCategoriesList);
        mainRecycler.setAdapter(mainRecyclerAdapter);
    }


    private void setDefaultStateNestedScrollView() {
        nestedScrollView.fullScroll(View.FOCUS_UP);
        nestedScrollView.scrollTo(0, 0);
        appBarLayout.setExpanded(true);
    }

    private void getBannerData() {

        CompositeDisposable compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(RetrofitClient.getRetrofitClient().getAllBanners()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<List<BannerMovie>>() {
                                   @Override
                                   public void onNext(@NonNull List<BannerMovie> bannerMovies) {

                                       for (int i = 0; i < bannerMovies.size(); i++) {
                                           switch (bannerMovies.get(i).getBannerCategoryId()) {
                                               case 1:
                                                   homeBannerList.add(bannerMovies.get(i));
                                                   continue;

                                               case 2:
                                                   tvShiowBannerList.add(bannerMovies.get(i));
                                                   continue;
                                               case 3:
                                                   moviesBannerList.add(bannerMovies.get(i));
                                                   continue;

                                               case 4:
                                                   kidsBannerList.add(bannerMovies.get(i));
                                                   continue;

                                           }

                                       }



                                   }

                                   @Override
                                   public void onError(@NonNull Throwable e) {

                                       Toast.makeText(MainActivity.this,""+ e,Toast.LENGTH_SHORT).show();
                                   }

                                   @Override
                                   public void onComplete() {

                                       //this is a default tab selected
                                       setBannerMoviesPagerAdapter(homeBannerList);

                                   }
                               }
                )
        );
    }

    private void getAllCategoryMoviesData(int CategoryId) {

        CompositeDisposable compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(RetrofitClient.getRetrofitClient().getAllCategoryMovies(CategoryId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<List<AllCategory>>() {
                                   @Override
                                   public void onNext(@NonNull List<AllCategory> allCategories) {


                                       setMainRecycler(allCategories);


                                   }

                                   @Override
                                   public void onError(@NonNull Throwable e) {

                                       Toast.makeText(MainActivity.this,""+ e,Toast.LENGTH_SHORT).show();
                                   }

                                   @Override
                                   public void onComplete() {

                                       //this is a default tab selected
                                       setBannerMoviesPagerAdapter(homeBannerList);

                                   }
                               }
                )
        );
    }


    class AutoSlider extends TimerTask {

        @Override
        public void run() {

            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (bannerMoviesViewPager.getCurrentItem() < homeBannerList.size() - 1) {
                        bannerMoviesViewPager.setCurrentItem(bannerMoviesViewPager.getCurrentItem() + 1);
                    } else {
                        bannerMoviesViewPager.setCurrentItem(0);
                    }
                }
            });
        }
    }
}