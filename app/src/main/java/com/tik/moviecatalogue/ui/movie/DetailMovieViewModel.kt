package com.tik.moviecatalogue.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.tik.moviecatalogue.data.MoviesEntity
import com.tik.moviecatalogue.data.source.CatalogueRepository


class DetailMovieViewModel (private val catalogueRepository: CatalogueRepository)  : ViewModel()   {
    private lateinit var moviesId: String

    fun setSelectedMovie(moviesId: String) {
        this.moviesId = moviesId
    }

    fun getMovie(): LiveData<MoviesEntity> = catalogueRepository.getDetailMovie(moviesId)


}