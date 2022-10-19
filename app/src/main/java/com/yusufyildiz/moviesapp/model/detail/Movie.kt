package com.yusufyildiz.moviesapp.model.detail

import com.google.gson.annotations.SerializedName

data class Movie(

    @SerializedName("Title")
    var movieTitle: String? = null,
    @SerializedName("Year")
    var movieYear: String? = null,
    @SerializedName("Rated")
    var movieRated: String? = null,
    @SerializedName("Released")
    var movieRelease: String? = null,
    @SerializedName("Runtime")
    var movieRuntime: String? = null,
    @SerializedName("Genre")
    var movieGenre: String? = null,
    @SerializedName("Director")
    var movieDirector: String? = null,
    @SerializedName("Writer")
    var movieWriter: String? = null,
    @SerializedName("Actors")
    var movieActors: String? = null,
    @SerializedName("Plot")
    var moviePlot: String? = null,
    @SerializedName("Language")
    var movieLanguage: String? = null,
    @SerializedName("Country")
    var movieCountry: String? = null,
    @SerializedName("Awards")
    var movieAwards: String? = null,
    @SerializedName("Poster")
    var moviePoster: String? = null,
    @SerializedName("Ratings")
    var movieRatings: ArrayList<Ratings> = arrayListOf(),
    @SerializedName("Metascore")
    var movieMetascore: String? = null,
    @SerializedName("imdbRating")
    var movieImdbRating: String? = null,
    @SerializedName("imdbVotes")
    var movieVotes: String? = null,
    @SerializedName("imdbID")
    var movieImdbId: String? = null,
    @SerializedName("Type")
    var movieType: String? = null,
    @SerializedName("DVD")
    var dvd: String? = null,
    @SerializedName("BoxOffice")
    var boxOffice: String? = null,
    @SerializedName("Production")
    var production: String? = null,
    @SerializedName("Website")
    var webSite: String? = null,
    @SerializedName("Response")
    var response: String? = null

)