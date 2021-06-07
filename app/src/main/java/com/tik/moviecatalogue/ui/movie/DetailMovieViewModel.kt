package com.tik.moviecatalogue.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.tik.moviecatalogue.data.source.CatalogueRepository
import com.tik.moviecatalogue.data.source.local.entity.MoviesEntity
import com.tik.moviecatalogue.vo.Resource


class DetailMovieViewModel(private val catalogueRepository: CatalogueRepository)  : ViewModel()   {


    var moviesId = MutableLiveData<String>()

    fun setSelectedMovie(moviesId: String) {
        this.moviesId.value = moviesId
    }


    var getMovie: LiveData<Resource<MoviesEntity>> =
        Transformations.switchMap(moviesId) { movieId: String ->
                catalogueRepository.getDetailMovie(
                    movieId
                )
            }




    fun setFavoriteMovie() {
        if (getMovie.getValue() != null) {
            val movieEntity: MoviesEntity? = getMovie.value?.data
            if (movieEntity != null) {
                val newState = !movieEntity.favorited!!
                catalogueRepository.setMovieFavorited(movieEntity, newState)
            }
        }
    }


}