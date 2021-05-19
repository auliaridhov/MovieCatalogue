package com.tik.moviecatalogue.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.tik.moviecatalogue.data.MoviesEntity
import com.tik.moviecatalogue.data.TvShowEntity
import com.tik.moviecatalogue.data.source.CatalogueRepository
import com.tik.moviecatalogue.ui.movie.MoviesViewModel
import com.tik.moviecatalogue.utils.DataDummy
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest {
    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogueRepository: CatalogueRepository

    @Mock
    private lateinit var observer: Observer<List<TvShowEntity>>

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(catalogueRepository)
    }

    @Test
    fun getTvs() {
        val resource: List<TvShowEntity> = DataDummy.generateDummyTvShow()
        val tv: MutableLiveData<List<TvShowEntity>> = MutableLiveData<List<TvShowEntity>>()
        tv.value = resource
        Mockito.`when`(catalogueRepository.getAllTv()).thenReturn(tv)
        viewModel.getTvSHow().observeForever(observer)
        Mockito.verify<Observer<List<TvShowEntity>>>(observer).onChanged(resource)
    }
}