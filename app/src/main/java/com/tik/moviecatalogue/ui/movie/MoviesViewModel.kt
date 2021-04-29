package com.tik.moviecatalogue.ui.movie

import com.tik.moviecatalogue.data.MoviesEntity
import com.tik.moviecatalogue.utils.DataDummy

class MoviesViewModel {
    fun getMovies(): List<MoviesEntity> = DataDummy.generateDummyMovies()
}