package com.tik.moviecatalogue.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.tik.moviecatalogue.data.MoviesEntity
import com.tik.moviecatalogue.data.TvShowEntity
import com.tik.moviecatalogue.data.source.CatalogueRepository
import com.tik.moviecatalogue.ui.movie.DetailMovieViewModel
import com.tik.moviecatalogue.utils.DataDummy
import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class DetailTvShowViewModelTest {


    private lateinit var viewModel: DetailTvShowViewModel

    private val dummyTv = DataDummy.generateDummyTvShow()[0]
    private val tvId = dummyTv.id.toString()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogueRepository: CatalogueRepository

    @Mock
    private lateinit var tvObserver: Observer<TvShowEntity>


    @Before
    fun setUp() {
        viewModel = DetailTvShowViewModel(catalogueRepository)
        viewModel.setSelectedTv(tvId)
    }

    @Test
    fun getTv() {
        val tv = MutableLiveData<TvShowEntity>()
        tv.value = dummyTv
        Mockito.`when`(catalogueRepository.getDetailTv(tvId)).thenReturn(tv)
        val tvEntity = viewModel.getTv().value as TvShowEntity
        Mockito.verify(catalogueRepository).getDetailTv(tvId)
        Assert.assertNotNull(tvEntity)
        junit.framework.Assert.assertEquals(dummyTv.overview, tvEntity.overview)
        junit.framework.Assert.assertEquals(dummyTv.posterPath, tvEntity.posterPath)
        junit.framework.Assert.assertEquals(dummyTv.originalLanguage, tvEntity.originalLanguage)
        junit.framework.Assert.assertEquals(dummyTv.name, tvEntity.name)
        junit.framework.Assert.assertEquals(dummyTv.firstAirDate, tvEntity.firstAirDate)
        viewModel.getTv().observeForever(tvObserver)
        Mockito.verify(tvObserver).onChanged(dummyTv)
    }
}