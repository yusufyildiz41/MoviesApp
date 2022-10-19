package com.yusufyildiz.moviesapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yusufyildiz.moviesapp.model.detail.Movie
import com.yusufyildiz.moviesapp.service.MoviesAPI
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val moviesAPI: MoviesAPI
) : ViewModel() {

    val movieDetailsLiveData = MutableLiveData<Movie>()

    fun getMovieDetails(imdbId: String)
    {
        viewModelScope.launch {
            val movieDetails = moviesAPI.getMovieData(imdbId, "62e24393")
            movieDetailsLiveData.value = movieDetails
        }
    }
}