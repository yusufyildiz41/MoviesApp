package com.yusufyildiz.moviesapp.model.detail

import com.google.gson.annotations.SerializedName

data class Ratings (
    @SerializedName("Source")
    var movieSource: String? = null,
    @SerializedName("Value")
    var movieValue: String? = null
)