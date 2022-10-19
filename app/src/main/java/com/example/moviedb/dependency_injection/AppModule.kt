package com.example.moviedb.dependency_injection

import com.example.moviedb.feature_movie_list.data.repository.MovieRepository
import com.example.moviedb.feature_movie_list.domain.use_case.GetMovieById
import com.example.moviedb.feature_movie_list.domain.use_case.GetMovieList
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule{
    @Provides
    @Singleton
    fun provideGetMovieById( repository: MovieRepository): GetMovieById {
        return GetMovieById( repository)
    }

    @Provides
    @Singleton
    fun provideGetMovieList( repository: MovieRepository): GetMovieList {
        return GetMovieList( repository)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(): MovieRepository {
        return MovieRepository()
    }

}