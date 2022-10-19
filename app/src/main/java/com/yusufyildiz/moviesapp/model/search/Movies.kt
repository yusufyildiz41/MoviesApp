package com.yusufyildiz.moviesapp.model.search

import com.google.gson.annotations.SerializedName

data class Movies(
    @SerializedName("Title")
    var moviesTitle: String? = null,
    @SerializedName("Year")
    var moviesYear: String? = null,
    @SerializedName("imdbID")
    var moviesImdbId: String? = null,
    @SerializedName("Type")
    var moviesType: String? = null,
    @SerializedName("Poster")
    var moviesPoster: String? = null
)