package com.tik.moviecatalogue.ui.movie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.tik.moviecatalogue.R
import com.tik.moviecatalogue.data.MoviesEntity
import com.tik.moviecatalogue.databinding.ActivityDetailMovieBinding

class DetailMovieActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }

    private lateinit var activityDetailMovieBinding: ActivityDetailMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityDetailMovieBinding = ActivityDetailMovieBinding.inflate(layoutInflater)



        setContentView(activityDetailMovieBinding.root)


        val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[DetailMovieViewModel::class.java]


        val extras = intent.extras
        if (extras != null) {
            val movieId = extras.getString(EXTRA_MOVIE)
            if (movieId != null) {
                viewModel.setSelectedMovie(movieId)
                populateMovie(viewModel.getMovie() as MoviesEntity)
            }
        }



    }

    private fun populateMovie(moviesEntity: MoviesEntity) {
        activityDetailMovieBinding.detailTitle.text = moviesEntity.title
        activityDetailMovieBinding.descDetail.text = moviesEntity.overview
        activityDetailMovieBinding.voteAvg.text = moviesEntity.vote_average.toString()
        activityDetailMovieBinding.dateDetail.text = moviesEntity.release_date

        Glide.with(this)
            .load(moviesEntity.poster_path)
            .transform(RoundedCorners(20))
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                .error(R.drawable.ic_error))
            .into(activityDetailMovieBinding.ivPoster)

    }
}