package com.tik.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.tik.moviecatalogue.data.source.local.entity.MoviesEntity
import com.tik.moviecatalogue.data.source.local.entity.TvShowEntity
import com.tik.moviecatalogue.vo.Resource

interface CatalogueDataSource {

    fun getAllMovie(): LiveData<Resource<PagedList<MoviesEntity>>>

    fun getAllTv(): LiveData<Resource<PagedList<TvShowEntity>>>

    fun getDetailMovie(idMovie: String): LiveData<Resource<MoviesEntity>>

    fun getDetailTv(idTv: String): LiveData<Resource<TvShowEntity>>

    fun setMovieFavorited(moviesEntity: MoviesEntity, state: Boolean)

    fun setTvFavorited(tvShowEntity: TvShowEntity, state: Boolean)

    fun getFavoritedMovie(): LiveData<PagedList<MoviesEntity>>

    fun getFavoritedTvShow(): LiveData<PagedList<TvShowEntity>>

}