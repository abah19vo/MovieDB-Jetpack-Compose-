package com.example.moviedb.feature_movie_list.domain.model

import com.google.gson.annotations.SerializedName
import java.util.ArrayList

data class MovieDetails(
    @SerializedName("adult") var adult: Boolean = false,
    @SerializedName("backdrop_path") var backdropPath: String = "",
    @SerializedName("budget") var budget: Int = 0,
    @SerializedName("genres") var genres: ArrayList<Genres> = arrayListOf(),
    @SerializedName("homepage") var homepage: String = "",
    @SerializedName("id") var id: Int = 0,
    @SerializedName("imdb_id") var imdbId: String = "",
    @SerializedName("original_language") var originalLanguage: String = "",
    @SerializedName("original_title") var originalTitle: String = "",
    @SerializedName("overview") var overview: String = "",
    @SerializedName("popularity") var popularity: Double = 0.0,
    @SerializedName("poster_path") var posterPath: String = "",
    @SerializedName("production_companies") var productionCompanies: ArrayList<ProductionCompanies> = arrayListOf(),
    @SerializedName("production_countries") var productionCountries: ArrayList<ProductionCountries> = arrayListOf(),
    @SerializedName("release_date") var releaseDate: String = "",
    @SerializedName("revenue") var revenue: Int = 0,
    @SerializedName("runtime") var runtime: Int = 0,
    @SerializedName("spoken_languages") var spokenLanguages: ArrayList<SpokenLanguages> = arrayListOf(),
    @SerializedName("status") var status: String = "",
    @SerializedName("tagline") var tagline: String = "",
    @SerializedName("title") var title: String = "",
    @SerializedName("video") var video: Boolean = false,
    @SerializedName("vote_average") var voteAverage: Double = 0.0,
    @SerializedName("vote_count") var voteCount: Int = 0
)