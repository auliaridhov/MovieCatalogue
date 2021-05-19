package com.tik.moviecatalogue.ui.movie

import android.graphics.Movie
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.tik.moviecatalogue.data.MoviesEntity
import com.tik.moviecatalogue.data.source.CatalogueRepository
import com.tik.moviecatalogue.utils.DataDummy

class MoviesViewModel(private val catalogueRepository: CatalogueRepository)  : ViewModel()  {

    fun getMovies(): LiveData<List<MoviesEntity>> = catalogueRepository.getAllMovie()


}