package com.yusufyildiz.moviesapp.service

import com.yusufyildiz.moviesapp.model.detail.Movie
import com.yusufyildiz.moviesapp.model.search.MovieSearch
import retrofit2.http.*

interface MoviesAPI {

    @GET("/")
    suspend fun getMovieData(
        @Query("i") imdbId: String,
        @Query("apikey") apiKey: String
    ): Movie

    @GET("/")
    suspend fun getMoviesSearchList(
        @Query("s") movieName: String,
        @Query("apikey") apiKey: String
    ): MovieSearch

}