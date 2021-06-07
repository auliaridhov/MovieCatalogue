package com.tik.moviecatalogue.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.tik.moviecatalogue.data.source.local.entity.MoviesEntity
import com.tik.moviecatalogue.data.source.local.entity.TvShowEntity

@Dao
interface CatalogueDao {

    @Query("SELECT * FROM movieentities")
    fun getMovies(): DataSource.Factory<Int, MoviesEntity>

    @Query("SELECT * FROM movieentities where favorited = 1")
    fun getFavoritedMovies(): DataSource.Factory<Int, MoviesEntity>

    @Transaction
    @Query("SELECT * FROM movieentities WHERE id = :movieId")
    fun getMoviesById(movieId: Int): LiveData<MoviesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: List<MoviesEntity>)

    @Update
    fun updateMovie(movie: MoviesEntity)

    @Query("SELECT * FROM tvshowentities")
    fun getTvShow(): DataSource.Factory<Int, TvShowEntity>

    @Query("SELECT * FROM tvshowentities where favorited = 1")
    fun getFavoritedTvShow():  DataSource.Factory<Int, TvShowEntity>

    @Transaction
    @Query("SELECT * FROM tvshowentities WHERE id = :tvId")
    fun getTvShowById(tvId: Int): LiveData<TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTv(movie: List<TvShowEntity>)

    @Update
    fun updateTv(movie: TvShowEntity)

}