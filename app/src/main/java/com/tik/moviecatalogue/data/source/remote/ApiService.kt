package com.tik.livedatawithapi

import com.tik.moviecatalogue.data.source.remote.response.MovieItem
import com.tik.moviecatalogue.data.source.remote.response.MoviesResponse
import com.tik.moviecatalogue.data.source.remote.response.TvShowItem
import com.tik.moviecatalogue.data.source.remote.response.TvShowResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("movie/now_playing?api_key=575fd5ed78ff0431fdabe8bd63eecaeb&language=en-US&page=1")
    fun getMovie(): Call<MoviesResponse>

    @GET("tv/on_the_air?api_key=575fd5ed78ff0431fdabe8bd63eecaeb&language=en-US&page=1")
    fun getTv(): Call<TvShowResponse>

    @GET("movie/{id}?api_key=575fd5ed78ff0431fdabe8bd63eecaeb&language=en-US")
    fun getDetailMovie(@Path("id") id: String): Call<MovieItem>

    @GET("tv/{id}?api_key=575fd5ed78ff0431fdabe8bd63eecaeb&language=en-US")
    fun getDetailTv(@Path("id") id: String): Call<TvShowItem>
}