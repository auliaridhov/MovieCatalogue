package com.tik.moviecatalogue.ui.tvshow

import androidx.lifecycle.ViewModel
import com.tik.moviecatalogue.data.MoviesEntity
import com.tik.moviecatalogue.data.TvShowEntity
import com.tik.moviecatalogue.utils.DataDummy

class TvShowViewModel : ViewModel(){
    fun getTvSHow(): List<TvShowEntity> = DataDummy.generateDummyTvShow()
}