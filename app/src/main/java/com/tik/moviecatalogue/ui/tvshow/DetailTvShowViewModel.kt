package com.tik.moviecatalogue.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.tik.moviecatalogue.data.MoviesEntity
import com.tik.moviecatalogue.data.TvShowEntity
import com.tik.moviecatalogue.data.source.CatalogueRepository
import com.tik.moviecatalogue.utils.DataDummy

class DetailTvShowViewModel (private val catalogueRepository: CatalogueRepository)  : ViewModel()   {
    private lateinit var tvShowId: String

    fun setSelectedTv(tvShowId: String) {
        this.tvShowId = tvShowId
    }


    fun getTv(): LiveData<TvShowEntity> = catalogueRepository.getDetailTv(tvShowId)

}