package com.tik.moviecatalogue.ui.movie

import androidx.lifecycle.ViewModel
import com.tik.moviecatalogue.data.MoviesEntity
import com.tik.moviecatalogue.utils.DataDummy

class MoviesViewModel  : ViewModel()  {
    fun getMovies(): List<MoviesEntity> = DataDummy.generateDummyMovies()
}