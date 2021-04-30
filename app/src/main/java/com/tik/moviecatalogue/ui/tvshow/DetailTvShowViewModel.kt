package com.tik.moviecatalogue.ui.tvshow

import androidx.lifecycle.ViewModel
import com.tik.moviecatalogue.data.MoviesEntity
import com.tik.moviecatalogue.data.TvShowEntity
import com.tik.moviecatalogue.utils.DataDummy

class DetailTvShowViewModel : ViewModel() {
    private lateinit var tvShowId: String

    fun setSelectedTv(tvShowId: String) {
        this.tvShowId = tvShowId
    }

    fun getTv(): TvShowEntity {
        lateinit var tv: TvShowEntity
        val tvEntities = DataDummy.generateDummyTvShow()
        for (tvEntity in tvEntities) {
            if (tvEntity.id == tvShowId) {
                tv = tvEntity
            }
        }
        return tv
    }

}