package com.tik.moviecatalogue.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.tik.moviecatalogue.data.MoviesEntity
import com.tik.moviecatalogue.data.source.CatalogueRepository
import com.tik.moviecatalogue.data.source.remote.response.MovieItem
import com.tik.moviecatalogue.utils.DataDummy
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class MoviesViewModelTest {

    private lateinit var viewModel: MoviesViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogueRepository: CatalogueRepository

    @Mock
    private lateinit var observer: Observer<List<MoviesEntity>>

    @Before
    fun setUp() {
        viewModel = MoviesViewModel(catalogueRepository)
    }


    @Test
    fun getMovies() {
        val resource: List<MoviesEntity> = DataDummy.generateDummyMovies()
        val movies: MutableLiveData<List<MoviesEntity>> = MutableLiveData<List<MoviesEntity>>()
        movies.value = resource
        Mockito.`when`(catalogueRepository.getAllMovie()).thenReturn(movies)
        viewModel.getMovies().observeForever(observer)
        Mockito.verify<Observer<List<MoviesEntity>>>(observer).onChanged(resource)
    }
}