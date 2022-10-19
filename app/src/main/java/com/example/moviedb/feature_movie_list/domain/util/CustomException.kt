package com.example.moviedb.feature_movie_list.domain.util

sealed class CustomException(
    val data: String,
): Throwable() {
    class UnexpectedError(data:String = "An Unexpected Error Occurred") : CustomException(data)
    class NoContent(data:String = "No Content") : CustomException(data)
    class UnexpectedNullValue(data:String = "Unexpected Null Value") : CustomException(data)
    class UnSuccessfulRequest(data:String = "An Un Successful Request") : CustomException(data)
    class NetworkFailure(data:String = "A Network Failure Occurred") : CustomException(data)
}