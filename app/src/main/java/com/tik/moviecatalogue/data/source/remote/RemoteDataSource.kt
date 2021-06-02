package com.tik.moviecatalogue.data.source.remote

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tik.livedatawithapi.ApiConfig
import com.tik.moviecatalogue.data.source.remote.response.MovieItem
import com.tik.moviecatalogue.data.source.remote.response.MoviesResponse
import com.tik.moviecatalogue.data.source.remote.response.TvShowItem
import com.tik.moviecatalogue.data.source.remote.response.TvShowResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {

    private val handler = Handler(Looper.getMainLooper())

    private var _movie = ArrayList<MovieItem>()

    private var _tv = ArrayList<TvShowItem>()

    private var _detailMovie = MovieItem()

    private var _detailTv = TvShowItem()


    companion object {
        private const val SERVICE_LATENCY_IN_MILLIS: Long = 2000
        private const val TAG = "MainViewModel"

    }

    fun getMovieList(callback: LoadMovieCallback) {
        EspressoIdlingResource.increment()
        val client = ApiConfig.getApiService().getMovie()
        client.enqueue(object : Callback<MoviesResponse> {
            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {
                if (response.isSuccessful) {
                    _movie = response.body()?.results as ArrayList<MovieItem>

                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")

                }
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
        handler.postDelayed({ callback.onAllMovieReceived(_movie)
            EspressoIdlingResource.decrement()}, SERVICE_LATENCY_IN_MILLIS)

    }

    fun getTvList(callback: LoadTvCallback) {
        EspressoIdlingResource.increment()
        val client = ApiConfig.getApiService().getTv()
        client.enqueue(object : Callback<TvShowResponse> {
            override fun onResponse(
                call: Call<TvShowResponse>,
                response: Response<TvShowResponse>
            ) {
                if (response.isSuccessful) {
                    _tv = response.body()?.results as ArrayList<TvShowItem>
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
        handler.postDelayed({ callback.onAllTvReceived(_tv)
            EspressoIdlingResource.decrement()}, SERVICE_LATENCY_IN_MILLIS)
    }

    fun detailMovie(idMovie: String, callback: LoadDetailMovieCallback) {
        EspressoIdlingResource.increment()
        val client = ApiConfig.getApiService().getDetailMovie(idMovie)
        client.enqueue(object : Callback<MovieItem> {
            override fun onResponse(
                call: Call<MovieItem>,
                response: Response<MovieItem>
            ) {
                if (response.isSuccessful) {
                    _detailMovie = response.body()!!

                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")

                }
            }

            override fun onFailure(call: Call<MovieItem>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
        handler.postDelayed({ callback.onDetailMovieReceived(_detailMovie)
            EspressoIdlingResource.decrement()}, SERVICE_LATENCY_IN_MILLIS)

    }

    fun detailTv(idTv: String, callback: LoadDetailTvCallback) {
        EspressoIdlingResource.increment()
        val client = ApiConfig.getApiService().getDetailTv(idTv)
        client.enqueue(object : Callback<TvShowItem> {
            override fun onResponse(
                call: Call<TvShowItem>,
                response: Response<TvShowItem>
            ) {
                if (response.isSuccessful) {
                    _detailTv = response.body()!!

                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")

                }
            }

            override fun onFailure(call: Call<TvShowItem>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
        handler.postDelayed({ callback.onDetailTvReceived(_detailTv)
            EspressoIdlingResource.decrement()}, SERVICE_LATENCY_IN_MILLIS)

    }


    interface LoadMovieCallback {
        fun onAllMovieReceived(movieResponses: List<MovieItem>)

    }
    interface LoadDetailMovieCallback {
        fun onDetailMovieReceived(detailMovieResponses: MovieItem)
    }

    interface LoadTvCallback {
        fun onAllTvReceived(tvResponses: List<TvShowItem>)
    }

    interface LoadDetailTvCallback {
        fun onDetailTvReceived(detailTvResponses: TvShowItem)
    }


}