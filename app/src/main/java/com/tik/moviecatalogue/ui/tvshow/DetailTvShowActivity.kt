package com.tik.moviecatalogue.ui.tvshow

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
import com.tik.moviecatalogue.data.TvShowEntity
import com.tik.moviecatalogue.databinding.ActivityDetailMovieBinding
import com.tik.moviecatalogue.databinding.ActivityDetailTvShowBinding
import com.tik.moviecatalogue.databinding.DetailMovieContentBinding
import com.tik.moviecatalogue.databinding.DetailTvContentBinding
import com.tik.moviecatalogue.ui.movie.DetailMovieActivity
import com.tik.moviecatalogue.ui.movie.DetailMovieViewModel

class DetailTvShowActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_TV = "extra_tv"
    }

    private lateinit var activityDetailTvShowBinding: ActivityDetailTvShowBinding
    private lateinit var detailTvContentBinding: DetailTvContentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityDetailTvShowBinding = ActivityDetailTvShowBinding.inflate(layoutInflater)
        detailTvContentBinding = activityDetailTvShowBinding.detailContent



        setContentView(activityDetailTvShowBinding.root)


        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(
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
                viewModel.getTv().observe(this, {
                        tv -> populateTv(tv)

                    activityDetailTvShowBinding.progressBar.visibility = View.GONE
                    activityDetailTvShowBinding.content.visibility = View.VISIBLE
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
}