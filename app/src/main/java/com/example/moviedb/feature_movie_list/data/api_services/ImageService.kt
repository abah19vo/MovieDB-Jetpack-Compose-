package com.example.moviedb.feature_movie_list.data.api_services

import android.content.Context
import android.graphics.Bitmap
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.example.moviedb.feature_movie_list.data.util.Constants.imageBaseUrl
import com.example.moviedb.feature_movie_list.data.util.ImageSize

class ImageService {
    fun loadImage(path: String, size: ImageSize, context: Context): RequestBuilder<Bitmap> =
        Glide.with(context).asBitmap().load("$imageBaseUrl${size.value}/$path")
}