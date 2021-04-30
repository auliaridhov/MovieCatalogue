package com.tik.moviecatalogue.ui.tvshow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.tik.moviecatalogue.R
import com.tik.moviecatalogue.data.MoviesEntity
import com.tik.moviecatalogue.data.TvShowEntity
import com.tik.moviecatalogue.databinding.ActivityDetailMovieBinding
import com.tik.moviecatalogue.databinding.ActivityDetailTvShowBinding
import com.tik.moviecatalogue.ui.movie.DetailMovieActivity
import com.tik.moviecatalogue.ui.movie.DetailMovieViewModel

class DetailTvShowActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_TV = "extra_tv"
    }

    private lateinit var activityDetailTvShowBinding: ActivityDetailTvShowBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityDetailTvShowBinding = ActivityDetailTvShowBinding.inflate(layoutInflater)



        setContentView(activityDetailTvShowBinding.root)


        val viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[DetailTvShowViewModel::class.java]


        val extras = intent.extras
        if (extras != null) {
            val tvId = extras.getString(DetailTvShowActivity.EXTRA_TV)
            if (tvId != null) {
                viewModel.setSelectedTv(tvId)
                populateTv(viewModel.getTv() as TvShowEntity)
            }
        }


    }

    private fun populateTv(tvShowEntity: TvShowEntity) {
        activityDetailTvShowBinding.detailTitle.text = tvShowEntity.name
        activityDetailTvShowBinding.descDetail.text = tvShowEntity.overview
        activityDetailTvShowBinding.voteAvg.text = tvShowEntity.vote_average.toString()
        activityDetailTvShowBinding.dateDetail.text = tvShowEntity.first_air_date

        Glide.with(this)
            .load(tvShowEntity.poster_path)
            .transform(RoundedCorners(20))
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
            )
            .into(activityDetailTvShowBinding.ivPoster)

    }
}