package com.tik.moviecatalogue.ui.movie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.dicoding.academies.viewmodel.ViewModelFactory
import com.tik.moviecatalogue.R
import com.tik.moviecatalogue.data.MoviesEntity
import com.tik.moviecatalogue.databinding.ActivityDetailMovieBinding
import com.tik.moviecatalogue.databinding.DetailMovieContentBinding

class DetailMovieActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }

    private lateinit var detailMovieContentBinding: DetailMovieContentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityDetailMovieBinding = ActivityDetailMovieBinding.inflate(layoutInflater)
        detailMovieContentBinding = activityDetailMovieBinding.detailContent

        setContentView(activityDetailMovieBinding.root)

        val factory = ViewModelFactory.getInstance(this)

        val viewModel = ViewModelProvider(this, factory)[DetailMovieViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val movieId = extras.getString(EXTRA_MOVIE)

            if (movieId != null) {

                activityDetailMovieBinding.progressBar.visibility = View.VISIBLE
                activityDetailMovieBinding.content.visibility = View.INVISIBLE

                viewModel.setSelectedMovie(movieId)
                viewModel.getMovie().observe(this, {
                        movie -> populateMovie(movie)
                    activityDetailMovieBinding.progressBar.visibility = View.GONE
                    activityDetailMovieBinding.content.visibility = View.VISIBLE

                })



            }
        }

    }

    private fun populateMovie(moviesEntity: MoviesEntity) {
        detailMovieContentBinding.detailTitle.text = moviesEntity.title
        detailMovieContentBinding.descDetail.text = moviesEntity.overview
        detailMovieContentBinding.voteAvg.text = moviesEntity.voteAverage.toString()
        detailMovieContentBinding.dateDetail.text = moviesEntity.releaseDate

        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500"+moviesEntity.posterPath)
            .transform(RoundedCorners(20))
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                .error(R.drawable.ic_error))
            .into(detailMovieContentBinding.ivPoster)

    }
}