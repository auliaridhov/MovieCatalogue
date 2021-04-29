package com.tik.moviecatalogue.ui.tvshow

import com.tik.moviecatalogue.data.MoviesEntity
import com.tik.moviecatalogue.data.TvShowEntity
import com.tik.moviecatalogue.utils.DataDummy

class TvShowViewModel {
    fun getTvSHow(): List<TvShowEntity> = DataDummy.generateDummyTvShow()
}