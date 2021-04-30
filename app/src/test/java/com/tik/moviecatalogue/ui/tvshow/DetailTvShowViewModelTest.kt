package com.tik.moviecatalogue.ui.tvshow

import com.tik.moviecatalogue.ui.movie.DetailMovieViewModel
import com.tik.moviecatalogue.utils.DataDummy
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class DetailTvShowViewModelTest {
    private lateinit var viewModel: DetailTvShowViewModel
    private val dummyTv = DataDummy.generateDummyTvShow()[0]
    private val tvId = dummyTv.id

    @Before
    fun setUp() {
        viewModel = DetailTvShowViewModel()
        viewModel.setSelectedTv(tvId)
    }

    @Test
    fun getTv() {
        viewModel.setSelectedTv(dummyTv.id)
        val tvEntity = viewModel.getTv()
        assertNotNull(tvEntity)
        assertEquals(dummyTv.id, tvEntity.id)
        assertEquals(dummyTv.name, tvEntity.name)
        assertEquals(dummyTv.overview, tvEntity.overview)
        assertEquals(dummyTv.first_air_date, tvEntity.first_air_date)
    }
}