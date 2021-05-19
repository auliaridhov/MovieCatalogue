package com.tik.moviecatalogue.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.tik.moviecatalogue.data.MoviesEntity
import com.tik.moviecatalogue.data.TvShowEntity
import com.tik.moviecatalogue.data.source.CatalogueRepository
import com.tik.moviecatalogue.utils.DataDummy

class TvShowViewModel (private val catalogueRepository: CatalogueRepository)  : ViewModel()  {
    fun getTvSHow(): LiveData<List<TvShowEntity>> = catalogueRepository.getAllTv()
}