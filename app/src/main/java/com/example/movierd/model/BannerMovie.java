package com.example.movierd.model;

/**
 * Rida Dhimni
 * 23/11/2020
 **/

public class BannerMovie {

    Integer bannerCategoryId;
    Integer id;
    String movieName;
    String imageUrl;
    String fileUrl;

    public BannerMovie(Integer bannerCategoryId, Integer id, String movieName, String imageUrl, String fileUrl) {
        this.bannerCategoryId = bannerCategoryId;
        this.id = id;
        this.movieName = movieName;
        this.imageUrl = imageUrl;
        this.fileUrl = fileUrl;
    }

    public Integer getBannerCategoryId() {
        return bannerCategoryId;
    }

    public void setBannerCategoryId(Integer bannerCategoryId) {
        this.bannerCategoryId = bannerCategoryId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }
}
