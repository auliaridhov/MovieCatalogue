package com.tik.moviecatalogue.ui.movie

import androidx.lifecycle.ViewModel
import com.tik.moviecatalogue.data.MoviesEntity
import com.tik.moviecatalogue.utils.DataDummy

class DetailMovieViewModel : ViewModel() {
    private lateinit var moviesId: String

    fun setSelectedMovie(moviesId: String) {
        this.moviesId = moviesId
    }

    fun getMovie(): MoviesEntity {
        lateinit var movies: MoviesEntity
        val moviesEntities = DataDummy.generateDummyMovies()
        for (movieEntity in moviesEntities) {
            if (movieEntity.id == moviesId) {
                movies = movieEntity
            }
        }
        return movies
    }

}