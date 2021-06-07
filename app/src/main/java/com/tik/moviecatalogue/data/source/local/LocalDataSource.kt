package com.tik.moviecatalogue.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.tik.moviecatalogue.data.source.local.entity.MoviesEntity
import com.tik.moviecatalogue.data.source.local.entity.TvShowEntity
import com.tik.moviecatalogue.data.source.local.room.CatalogueDao


class LocalDataSource private constructor(private val mCatalogueDao: CatalogueDao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(catalogueDao: CatalogueDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(catalogueDao)
    }

    fun getAllMovies(): DataSource.Factory<Int, MoviesEntity> = mCatalogueDao.getMovies()

    fun getFavoritedMovies(): DataSource.Factory<Int, MoviesEntity> = mCatalogueDao.getFavoritedMovies()

    fun getMoviesById(movieId: Int): LiveData<MoviesEntity> = mCatalogueDao.getMoviesById(movieId)

    fun insertMovie(movie: List<MoviesEntity>) = mCatalogueDao.insertMovie(movie)

    fun setMovieFavorited(movie: MoviesEntity, newState: Boolean) {
        movie.favorited = newState
        mCatalogueDao.updateMovie(movie)
    }

    fun getAllTvShow(): DataSource.Factory<Int, TvShowEntity> = mCatalogueDao.getTvShow()

    fun getFavoritedTvShow(): DataSource.Factory<Int, TvShowEntity> = mCatalogueDao.getFavoritedTvShow()

    fun getTvShowById(tvId: Int): LiveData<TvShowEntity> = mCatalogueDao.getTvShowById(tvId)

    fun insertTvShow(tv: List<TvShowEntity>) = mCatalogueDao.insertTv(tv)

    fun setTvShowFavorited(tv: TvShowEntity, newState: Boolean) {
        tv.favorited = newState
        mCatalogueDao.updateTv(tv)
    }

}