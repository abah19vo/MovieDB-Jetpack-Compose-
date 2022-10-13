package com.example.moviedb.feature_movie_list.domain.util

import android.net.ConnectivityManager
import android.net.NetworkCapabilities.*
import android.os.Build
import android.provider.ContactsContract.CommonDataKinds.Email.TYPE_MOBILE
import com.example.moviedb.feature_movie_list.data.util.Constants
import com.example.moviedb.feature_movie_list.data.util.ImageSize

class InternetCheck {

    companion object{
        fun getImagePath(path: String, size: ImageSize) = "${Constants.imageBaseUrl}${size.value}/$path"

        fun hasInternetConnection(connectivityManager: ConnectivityManager): Boolean {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val activeNetwork = connectivityManager.activeNetwork ?: return false
                val capabilities =
                    connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
                return when {
                    capabilities.hasTransport(TRANSPORT_WIFI) -> true
                    capabilities.hasTransport(TRANSPORT_CELLULAR) -> true
                    capabilities.hasTransport(TRANSPORT_ETHERNET) -> true
                    else -> false
                }
            }
            return false
        }
    }
}