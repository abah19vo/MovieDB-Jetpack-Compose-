package com.example.moviedb.feature_movie_list.domain.model

import com.google.gson.annotations.SerializedName

data class SpokenLanguages(

    @SerializedName("iso_639_1") var iso6391: String? = null,
    @SerializedName("name") var name: String? = null

)