package com.tik.moviecatalogue.ui.tvshow

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
import com.tik.moviecatalogue.data.source.local.entity.TvShowEntity
import com.tik.moviecatalogue.databinding.ActivityDetailTvShowBinding
import com.tik.moviecatalogue.databinding.DetailTvContentBinding
import com.tik.moviecatalogue.ui.movie.DetailMovieViewModel
import com.tik.moviecatalogue.vo.Status

class DetailTvShowActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_TV = "extra_tv"
    }

    private lateinit var activityDetailTvShowBinding: ActivityDetailTvShowBinding
    private lateinit var detailTvContentBinding: DetailTvContentBinding


    private lateinit var viewModel: DetailTvShowViewModel
    private var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityDetailTvShowBinding = ActivityDetailTvShowBinding.inflate(layoutInflater)
        detailTvContentBinding = activityDetailTvShowBinding.detailContent

        setContentView(activityDetailTvShowBinding.root)


        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(
            this,
            factory
        )[DetailTvShowViewModel::class.java]



        val extras = intent.extras
        if (extras != null) {
            val tvId = extras.getString(DetailTvShowActivity.EXTRA_TV)
            if (tvId != null) {
                activityDetailTvShowBinding.progressBar.visibility = View.VISIBLE
                activityDetailTvShowBinding.content.visibility = View.INVISIBLE

                viewModel.setSelectedTv(tvId)

                viewModel.getTv.observe(this, { tv ->
                    if (tv != null) {
                        when (tv.status) {
                            Status.LOADING ->
                            {
                                activityDetailTvShowBinding.progressBar.visibility = View.VISIBLE
                            }
                            Status.SUCCESS -> if (tv.data != null) {
                                activityDetailTvShowBinding.progressBar.visibility = View.GONE
                                activityDetailTvShowBinding.content.visibility = View.VISIBLE
                                populateTv(tv.data)
                            }
                            Status.ERROR -> {
                                activityDetailTvShowBinding.progressBar.visibility = View.GONE
                                Toast.makeText(applicationContext, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                })

            }
        }
    }

    private fun populateTv(tvShowEntity: TvShowEntity) {
        detailTvContentBinding.detailTitle.text = tvShowEntity.name
        detailTvContentBinding.descDetail.text = tvShowEntity.overview
        detailTvContentBinding.voteAvg.text = tvShowEntity.voteAverage.toString()
        detailTvContentBinding.dateDetail.text = tvShowEntity.firstAirDate

        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500"+tvShowEntity.posterPath)
            .transform(RoundedCorners(20))
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
            )
            .into(detailTvContentBinding.ivPoster)

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        this.menu = menu
        viewModel.getTv.observe(this, { tvs ->
            if (tvs != null) {
                when (tvs.status) {
                    Status.LOADING -> activityDetailTvShowBinding.progressBar.visibility = View.VISIBLE
                    Status.SUCCESS -> if (tvs.data != null) {
                        activityDetailTvShowBinding.progressBar.visibility = View.GONE
                        val state = tvs.data.favorited
                        setFavoritedState(state)
                    }
                    Status.ERROR -> {
                        activityDetailTvShowBinding.progressBar.visibility = View.GONE
                        Toast.makeText(applicationContext, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_favorited) {
            viewModel.setFavoriteTv()
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