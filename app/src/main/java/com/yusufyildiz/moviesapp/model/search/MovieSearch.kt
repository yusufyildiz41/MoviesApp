package com.yusufyildiz.moviesapp.model.search

import com.google.gson.annotations.SerializedName

data class MovieSearch(
    @SerializedName("Search")
    var moviesList: ArrayList<Movies> = arrayListOf(),
    @SerializedName("totalResults")
    var totalResults: String? = null,
    @SerializedName("Response")
    var moviesResponse: String? = null
)