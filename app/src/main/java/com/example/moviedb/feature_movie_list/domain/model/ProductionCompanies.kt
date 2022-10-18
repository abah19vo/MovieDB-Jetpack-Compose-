package com.example.moviedb.feature_movie_list.domain.model

import com.google.gson.annotations.SerializedName

data class ProductionCompanies(

    @SerializedName("id") var id: Int,
    @SerializedName("logo_path") var logoPath: String? = null,
    @SerializedName("name") var name: String = "",
    @SerializedName("origin_country") var originCountry: String = "",

)
