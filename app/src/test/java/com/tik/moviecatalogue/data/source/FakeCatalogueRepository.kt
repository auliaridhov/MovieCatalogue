package com.tik.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.tik.moviecatalogue.data.source.local.LocalDataSource
import com.tik.moviecatalogue.data.source.local.entity.MoviesEntity
import com.tik.moviecatalogue.data.source.local.entity.TvShowEntity
import com.tik.moviecatalogue.data.source.remote.ApiResponse
import com.tik.moviecatalogue.data.source.remote.RemoteDataSource
import com.tik.moviecatalogue.data.source.remote.response.MovieItem
import com.tik.moviecatalogue.data.source.remote.response.TvShowItem
import com.tik.moviecatalogue.utils.AppExecutors
import com.tik.moviecatalogue.vo.Resource

class FakeCatalogueRepository (
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : CatalogueDataSource {

    companion object {
        @Volatile
        private var instance: FakeCatalogueRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): FakeCatalogueRepository =
            instance ?: synchronized(this) {
                instance ?: FakeCatalogueRepository(remoteData, localData, appExecutors).apply { instance = this }
            }
    }

    override fun getAllMovie(): LiveData<Resource<PagedList<MoviesEntity>>> {
        return object : NetworkBoundResource<PagedList<MoviesEntity>, List<MovieItem>>(appExecutors) {

            public override fun loadFromDB(): LiveData<PagedList<MoviesEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(8)
                    .setPageSize(8)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllMovies(), config).build()
            }

            override fun shouldFetch(data: PagedList<MoviesEntity>?): Boolean =
                data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<MovieItem>>> =
                remoteDataSource.getMovieList()

            public override fun saveCallResult(movieResponses: List<MovieItem>) {
                val movieList = ArrayList<MoviesEntity>()
                for (response in movieResponses) {
                    val course = MoviesEntity(
                        response.overview,
                        response.originalLanguage,
                        response.originalTitle,
                        response.video,
                        response.title,
                        response.posterPath,
                        response.backdropPath,
                        response.releaseDate,
                        response.popularity,
                        response.voteAverage,
                        response.id,
                        response.adult,
                        response.voteCount
                    )
                    movieList.add(course)
                }

                localDataSource.insertMovie(movieList)
            }
        }.asLiveData()
    }

    override fun getAllTv(): LiveData<Resource<PagedList<TvShowEntity>>> {

        return object : NetworkBoundResource<PagedList<TvShowEntity>, List<TvShowItem>>(appExecutors) {

            public override fun loadFromDB(): LiveData<PagedList<TvShowEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(8)
                    .setPageSize(8)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllTvShow(), config).build()
            }

            override fun shouldFetch(data: PagedList<TvShowEntity>?): Boolean =
                data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<TvShowItem>>> =
                remoteDataSource.getTvList()

            public override fun saveCallResult(tvResponse: List<TvShowItem>) {
                val tvList = ArrayList<TvShowEntity>()
                for (response in tvResponse) {
                    val tv = TvShowEntity(
                        response.firstAirDate,
                        response.overview,
                        response.originalLanguage,
                        response.posterPath,
                        response.backdropPath,
                        response.originalName,
                        response.popularity,
                        response.voteAverage,
                        response.name,
                        response.id,
                        response.voteCount
                    )
                    tvList.add(tv)
                }

                localDataSource.insertTvShow(tvList)
            }
        }.asLiveData()
    }

    override fun getDetailMovie(idMovie: String): LiveData<Resource<MoviesEntity>> {

        return object : NetworkBoundResource<MoviesEntity, MovieItem>(appExecutors) {

            public override fun loadFromDB(): LiveData<MoviesEntity> =
                localDataSource.getMoviesById(idMovie.toInt())

            override fun shouldFetch(data: MoviesEntity?): Boolean =
                data == null

            public override fun createCall(): LiveData<ApiResponse<MovieItem>> =
                remoteDataSource.detailMovie(idMovie)

            public override fun saveCallResult(response: MovieItem) {
                val movieEntity = MoviesEntity(
                    response.overview,
                    response.originalLanguage,
                    response.originalTitle,
                    response.video,
                    response.title,
                    response.posterPath,
                    response.backdropPath,
                    response.releaseDate,
                    response.popularity,
                    response.voteAverage,
                    response.id,
                    response.adult,
                    response.voteCount
                )
                val movieEntities: MutableList<MoviesEntity> = ArrayList<MoviesEntity>()
                movieEntities.add(movieEntity)
                localDataSource.insertMovie(movieEntities)
            }
        }.asLiveData()
    }

    override fun getDetailTv(idTv: String): LiveData<Resource<TvShowEntity>>  {

        return object : NetworkBoundResource<TvShowEntity, TvShowItem>(appExecutors) {

            public override fun loadFromDB(): LiveData<TvShowEntity> =
                localDataSource.getTvShowById(idTv.toInt())

            override fun shouldFetch(data: TvShowEntity?): Boolean =
                data == null

            public override fun createCall(): LiveData<ApiResponse<TvShowItem>> =
                remoteDataSource.detailTv(idTv)

            public override fun saveCallResult(response: TvShowItem) {
                val tvEntity = TvShowEntity(
                    response.firstAirDate,
                    response.overview,
                    response.originalLanguage,
                    response.posterPath,
                    response.backdropPath,
                    response.originalName,
                    response.popularity,
                    response.voteAverage,
                    response.name,
                    response.id,
                    response.voteCount
                )
                val tvEntities: MutableList<TvShowEntity> = ArrayList<TvShowEntity>()
                tvEntities.add(tvEntity)
                localDataSource.insertTvShow(tvEntities)
            }
        }.asLiveData()
    }

    override fun setMovieFavorited(moviesEntity: MoviesEntity, state: Boolean) {
        val runnable = Runnable { localDataSource.setMovieFavorited(moviesEntity, state) }
        appExecutors.diskIO().execute(runnable)
    }

    override fun setTvFavorited(tvShowEntity: TvShowEntity, state: Boolean) {
        val runnable = Runnable { localDataSource.setTvShowFavorited(tvShowEntity, state) }
        appExecutors.diskIO().execute(runnable)
    }

    override fun getFavoritedMovie(): LiveData<PagedList<MoviesEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(8)
            .setPageSize(8)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoritedMovies(), config).build()
    }

    override fun getFavoritedTvShow(): LiveData<PagedList<TvShowEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(8)
            .setPageSize(8)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoritedTvShow(), config).build()
    }

}