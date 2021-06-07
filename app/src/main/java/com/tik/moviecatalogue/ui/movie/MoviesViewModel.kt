package com.tik.moviecatalogue.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.tik.moviecatalogue.data.source.local.entity.MoviesEntity
import com.tik.moviecatalogue.data.source.CatalogueRepository
import com.tik.moviecatalogue.vo.Resource

class MoviesViewModel(private val catalogueRepository: CatalogueRepository)  : ViewModel()  {

    fun getMovies(): LiveData<Resource<PagedList<MoviesEntity>>> = catalogueRepository.getAllMovie()

    fun getFavMovies(): LiveData<PagedList<MoviesEntity>> = catalogueRepository.getFavoritedMovie()

    fun setFavorite(moviesEntity: MoviesEntity) {
        val newState = !moviesEntity.favorited!!
        catalogueRepository.setMovieFavorited(moviesEntity, newState)
    }

}