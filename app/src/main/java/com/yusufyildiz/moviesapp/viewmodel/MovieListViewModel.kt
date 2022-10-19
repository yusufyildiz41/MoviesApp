package com.yusufyildiz.moviesapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yusufyildiz.moviesapp.model.detail.Movie
import com.yusufyildiz.moviesapp.model.search.MovieSearch
import com.yusufyildiz.moviesapp.model.search.Movies
import com.yusufyildiz.moviesapp.service.MoviesAPI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val moviesAPI: MoviesAPI
) : ViewModel() {

    val movieListLiveData = MutableLiveData<ArrayList<Movies>>()
    val movieError = MutableLiveData<Boolean>()
    val movieLoading = MutableLiveData<Boolean>()

    fun getMovies(movieName: String)
    {
        viewModelScope.launch {
            val movieList = moviesAPI.getMoviesSearchList(movieName,"62e24393").moviesList

            if(movieList.isNotEmpty())
            {
                movieLoading.value = true
                movieError.value = false
                movieListLiveData.value = movieList
            }
            else
            {
                movieLoading.value = false
                movieError.value = true
            }

        }
    }
}