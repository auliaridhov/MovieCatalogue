package com.tik.moviecatalogue.ui.tvshow

import com.tik.moviecatalogue.ui.movie.MoviesViewModel
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class TvShowViewModelTest {
    private lateinit var viewModel: TvShowViewModel

    @Before
    fun setUp() {
        viewModel = TvShowViewModel()
    }

    @Test
    fun getTvs() {
        val tvEntities = viewModel.getTvSHow()
        assertNotNull(tvEntities)
        assertEquals(10, tvEntities.size)
    }
}