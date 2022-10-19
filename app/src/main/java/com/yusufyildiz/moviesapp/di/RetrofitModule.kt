package com.yusufyildiz.moviesapp.di

import com.yusufyildiz.moviesapp.common.Constants.BASE_URL
import com.yusufyildiz.moviesapp.service.MoviesAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideMovieService(retrofit: Retrofit): MoviesAPI =
        retrofit.create(MoviesAPI::class.java)

}