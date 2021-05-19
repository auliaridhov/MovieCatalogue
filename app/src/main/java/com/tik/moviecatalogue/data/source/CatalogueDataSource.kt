package com.tik.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import com.tik.moviecatalogue.data.MoviesEntity
import com.tik.moviecatalogue.data.TvShowEntity
import com.tik.moviecatalogue.data.source.remote.response.MovieItem
import com.tik.moviecatalogue.data.source.remote.response.TvShowItem

interface CatalogueDataSource {

    fun getAllMovie(): LiveData<List<MoviesEntity>>

    fun getAllTv(): LiveData<List<TvShowEntity>>

    fun getDetailMovie(idMovie: String): LiveData<MoviesEntity>

    fun getDetailTv(idTv: String): LiveData<TvShowEntity>

}