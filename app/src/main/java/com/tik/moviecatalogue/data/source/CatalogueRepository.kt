package com.tik.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tik.moviecatalogue.data.MoviesEntity
import com.tik.moviecatalogue.data.TvShowEntity
import com.tik.moviecatalogue.data.source.remote.RemoteDataSource
import com.tik.moviecatalogue.data.source.remote.response.MovieItem
import com.tik.moviecatalogue.data.source.remote.response.TvShowItem

class CatalogueRepository private constructor(private val remoteDataSource: RemoteDataSource) : CatalogueDataSource {

    companion object {
        @Volatile
        private var instance: CatalogueRepository? = null

        fun getInstance(remoteData: RemoteDataSource): CatalogueRepository =
            instance ?: synchronized(this) {
                instance ?: CatalogueRepository(remoteData).apply { instance = this }
            }
    }

    override fun getAllMovie(): LiveData<List<MoviesEntity>> {
        val movieResults = MutableLiveData<List<MoviesEntity>>()
        remoteDataSource.getMovieList(object : RemoteDataSource.LoadMovieCallback {
            override fun onAllMovieReceived(movieResponses: List<MovieItem>) {
                val movieList = ArrayList<MoviesEntity>()
                for (response in movieResponses) {
                    val course = MoviesEntity(response.overview,
                        response.originalLanguage,
                        response.originalTitle,
                        response.video,
                        response.title,
                        response.genreIds,
                        response.posterPath,
                        response.backdropPath,
                        response.releaseDate,
                        response.popularity,
                        response.voteAverage,
                        response.id,
                        response.adult,
                        response.voteCount)
                    movieList.add(course)
                }
                movieResults.postValue(movieList)
            }

        })
        return movieResults
    }

    override fun getAllTv(): LiveData<List<TvShowEntity>> {
        val tvResults = MutableLiveData<List<TvShowEntity>>()
        remoteDataSource.getTvList(object : RemoteDataSource.LoadTvCallback {
            override fun onAllTvReceived(tvResponse: List<TvShowItem>) {
                val tvList = ArrayList<TvShowEntity>()
                for (response in tvResponse) {
                    val tv = TvShowEntity(response.firstAirDate,
                        response.overview,
                        response.originalLanguage,
                        response.genreIds,
                        response.posterPath,
                        response.originCountry,
                        response.backdropPath,
                        response.originalName,
                        response.popularity,
                        response.voteAverage,
                        response.name,
                        response.id,
                        response.voteCount)
                    tvList.add(tv)
                }
                tvResults.postValue(tvList)
            }

        })
        return tvResults
    }

    override fun getDetailMovie(idMovie: String): LiveData<MoviesEntity> {

        val movieResults = MutableLiveData<MoviesEntity>()
        remoteDataSource.detailMovie(idMovie, object : RemoteDataSource.LoadDetailMovieCallback {
            override fun onDetailMovieReceived(detailMovieResponse: MovieItem) {
                val movie = MoviesEntity(detailMovieResponse.overview,
                    detailMovieResponse.originalLanguage,
                    detailMovieResponse.originalTitle,
                    detailMovieResponse.video,
                    detailMovieResponse.title,
                    detailMovieResponse.genreIds,
                    detailMovieResponse.posterPath,
                    detailMovieResponse.backdropPath,
                    detailMovieResponse.releaseDate,
                    detailMovieResponse.popularity,
                    detailMovieResponse.voteAverage,
                    detailMovieResponse.id,
                    detailMovieResponse.adult,
                    detailMovieResponse.voteCount)
                movieResults.postValue(movie)
            }

        })
        return movieResults
    }

    override fun getDetailTv(idTv: String): LiveData<TvShowEntity> {
        val tvResults = MutableLiveData<TvShowEntity>()
        remoteDataSource.detailTv(idTv, object : RemoteDataSource.LoadDetailTvCallback {
            override fun onDetailTvReceived(detailTvResponse: TvShowItem) {
                val tv = TvShowEntity(detailTvResponse.firstAirDate,
                    detailTvResponse.overview,
                    detailTvResponse.originalLanguage,
                    detailTvResponse.genreIds,
                    detailTvResponse.posterPath,
                    detailTvResponse.originCountry,
                    detailTvResponse.backdropPath,
                    detailTvResponse.originalName,
                    detailTvResponse.popularity,
                    detailTvResponse.voteAverage,
                    detailTvResponse.name,
                    detailTvResponse.id,
                    detailTvResponse.voteCount)
                tvResults.postValue(tv)
            }

        })
        return tvResults
    }
}