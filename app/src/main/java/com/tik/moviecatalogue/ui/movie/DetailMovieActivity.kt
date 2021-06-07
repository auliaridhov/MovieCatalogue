package com.tik.moviecatalogue.ui.movie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.dicoding.academies.viewmodel.ViewModelFactory
import com.tik.moviecatalogue.R
import com.tik.moviecatalogue.data.source.local.entity.MoviesEntity
import com.tik.moviecatalogue.databinding.ActivityDetailMovieBinding
import com.tik.moviecatalogue.databinding.DetailMovieContentBinding
import com.tik.moviecatalogue.vo.Status

class DetailMovieActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }

    private lateinit var activityDetailMovieBinding: ActivityDetailMovieBinding
    private lateinit var detailMovieContentBinding: DetailMovieContentBinding

    private lateinit var viewModel: DetailMovieViewModel
    private var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityDetailMovieBinding = ActivityDetailMovieBinding.inflate(layoutInflater)
        detailMovieContentBinding = activityDetailMovieBinding.detailContent

        setContentView(activityDetailMovieBinding.root)

        val factory = ViewModelFactory.getInstance(this)

        viewModel = ViewModelProvider(this, factory)[DetailMovieViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val movieId = extras.getString(EXTRA_MOVIE)

            if (movieId != null) {

                activityDetailMovieBinding.progressBar.visibility = View.VISIBLE
                activityDetailMovieBinding.content.visibility = View.INVISIBLE

                viewModel.setSelectedMovie(movieId)

                viewModel.getMovie.observe(this, { movie ->
                    if (movie != null) {
                        when (movie.status) {
                            Status.LOADING -> activityDetailMovieBinding.progressBar.visibility = View.VISIBLE
                            Status.SUCCESS -> if (movie.data != null) {
                                activityDetailMovieBinding.progressBar.visibility = View.GONE
                                activityDetailMovieBinding.content.visibility = View.VISIBLE
                                populateMovie(movie.data)
                            }
                            Status.ERROR -> {
                                activityDetailMovieBinding.progressBar.visibility = View.GONE
                                Toast.makeText(applicationContext, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        this.menu = menu
        viewModel.getMovie.observe(this, { movie ->
            if (movie != null) {
                when (movie.status) {
                    Status.LOADING -> activityDetailMovieBinding.progressBar.visibility = View.VISIBLE
                    Status.SUCCESS -> if (movie.data != null) {
                        activityDetailMovieBinding.progressBar.visibility = View.GONE
                        val state = movie.data.favorited
                        setFavoritedState(state)
                    }
                    Status.ERROR -> {
                        activityDetailMovieBinding.progressBar.visibility = View.GONE
                        Toast.makeText(applicationContext, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_favorited) {
            viewModel.setFavoriteMovie()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setFavoritedState(state: Boolean?) {
        if (menu == null) return
        val menuItem = menu?.findItem(R.id.action_favorited)
        if (state == true) {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorited_24)
        } else {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_24)
        }
    }

}