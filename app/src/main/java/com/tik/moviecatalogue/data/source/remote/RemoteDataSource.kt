package com.tik.moviecatalogue.data.source.remote

import EspressoIdlingResource
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
    companion object {
        private const val SERVICE_LATENCY_IN_MILLIS: Long = 2000
        private const val TAG = "MainViewModel"

    }

    fun getMovieList(): LiveData<ApiResponse<List<MovieItem>>> {
        EspressoIdlingResource.increment()
        val resultMovie = MutableLiveData<ApiResponse<List<MovieItem>>>()
        handler.postDelayed(
            {
                val client = ApiConfig.getApiService().getMovie()
                client.enqueue(object : Callback<MoviesResponse> {
                    override fun onResponse(
                        call: Call<MoviesResponse>,
                        response: Response<MoviesResponse>
                    ) {
                        if (response.isSuccessful) {
                            //_movie = response.body()?.results as ArrayList<MovieItem>
                            resultMovie.value = ApiResponse.success(response.body()?.results as List<MovieItem>)
                            EspressoIdlingResource.decrement()

                        } else {
                            Log.e(TAG, "onFailure: ${response.message()}")

                        }
                    }

                    override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                        Log.e(TAG, "onFailure: ${t.message.toString()}")
                    }
                })
            }, SERVICE_LATENCY_IN_MILLIS
        )

        return resultMovie

    }

    fun getTvList(): LiveData<ApiResponse<List<TvShowItem>>> {
        EspressoIdlingResource.increment()


        val resultTv = MutableLiveData<ApiResponse<List<TvShowItem>>>()
        handler.postDelayed(
            {
                val client = ApiConfig.getApiService().getTv()
                client.enqueue(object : Callback<TvShowResponse> {
                    override fun onResponse(
                        call: Call<TvShowResponse>,
                        response: Response<TvShowResponse>
                    ) {
                        if (response.isSuccessful) {

                            resultTv.value = ApiResponse.success(response.body()?.results as List<TvShowItem>)
                            EspressoIdlingResource.decrement()

                        } else {
                            Log.e(TAG, "onFailure: ${response.message()}")

                        }
                    }

                    override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {
                        Log.e(TAG, "onFailure: ${t.message.toString()}")
                    }
                })
            }, SERVICE_LATENCY_IN_MILLIS
        )

        return resultTv
    }

    fun detailMovie(idMovie: String): LiveData<ApiResponse<MovieItem>> {
        EspressoIdlingResource.increment()
        val resultDetailMovie = MutableLiveData<ApiResponse<MovieItem>>()
        handler.postDelayed(
            {
                val client = ApiConfig.getApiService().getDetailMovie(idMovie)
                client.enqueue(object : Callback<MovieItem> {
                    override fun onResponse(
                        call: Call<MovieItem>,
                        response: Response<MovieItem>
                    ) {
                        if (response.isSuccessful) {
                            //_detailMovie = response.body()!!
                            resultDetailMovie.value = ApiResponse.success(response.body()!!)
                            EspressoIdlingResource.decrement()

                        } else {
                            Log.e(TAG, "onFailure: ${response.message()}")

                        }
                    }

                    override fun onFailure(call: Call<MovieItem>, t: Throwable) {
                        Log.e(TAG, "onFailure: ${t.message.toString()}")
                    }
                })
            }, SERVICE_LATENCY_IN_MILLIS
        )

        return resultDetailMovie
    }

    fun detailTv(idTv: String) : LiveData<ApiResponse<TvShowItem>> {

        EspressoIdlingResource.increment()
        val resultDetailTv = MutableLiveData<ApiResponse<TvShowItem>>()
        handler.postDelayed(
            {
                val client = ApiConfig.getApiService().getDetailTv(idTv)
                client.enqueue(object : Callback<TvShowItem> {
                    override fun onResponse(
                        call: Call<TvShowItem>,
                        response: Response<TvShowItem>
                    ) {
                        if (response.isSuccessful) {
                            //_detailMovie = response.body()!!
                            resultDetailTv.value = ApiResponse.success(response.body()!!)
                            EspressoIdlingResource.decrement()

                        } else {
                            Log.e(TAG, "onFailure: ${response.message()}")

                        }
                    }

                    override fun onFailure(call: Call<TvShowItem>, t: Throwable) {
                        Log.e(TAG, "onFailure: ${t.message.toString()}")
                    }
                })
            }, SERVICE_LATENCY_IN_MILLIS
        )

        return resultDetailTv

    }


}

