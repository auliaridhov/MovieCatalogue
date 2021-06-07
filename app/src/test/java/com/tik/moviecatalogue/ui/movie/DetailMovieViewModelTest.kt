package com.tik.moviecatalogue.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.tik.moviecatalogue.data.source.local.entity.MoviesEntity
import com.tik.moviecatalogue.data.source.CatalogueRepository
import com.tik.moviecatalogue.utils.DataDummy
import com.tik.moviecatalogue.vo.Resource
import junit.framework.Assert.assertEquals
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailMovieViewModelTest {
    private lateinit var viewModel: DetailMovieViewModel

    private val dummyMovie = DataDummy.generateDummyMovies()[0]
    private val movieId = dummyMovie.id.toString()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogueRepository: CatalogueRepository

    @Mock
    private lateinit var movieObserver: Observer<Resource<MoviesEntity>>


    @Before
    fun setUp() {
        viewModel = DetailMovieViewModel(catalogueRepository)
        viewModel.setSelectedMovie(movieId)
    }


    @Test
    fun getMovie() {

//        val movie = MutableLiveData<MoviesEntity>()
//        movie.value = dummyMovie
//
//        Mockito.`when`(catalogueRepository.getDetailMovie(movieId)).thenReturn(movie)
//        val moviesEntity = viewModel.getMovie().value as MoviesEntity
//        Mockito.verify(catalogueRepository).getDetailMovie(movieId)
//        Assert.assertNotNull(moviesEntity)
//        assertEquals(dummyMovie.overview, moviesEntity.overview)
//        assertEquals(dummyMovie.posterPath, moviesEntity.posterPath)
//        assertEquals(dummyMovie.releaseDate, moviesEntity.releaseDate)
//        assertEquals(dummyMovie.originalTitle, moviesEntity.originalTitle)
//        assertEquals(dummyMovie.title, moviesEntity.title)
//
//        viewModel.getMovie().observeForever(movieObserver)
//        Mockito.verify(movieObserver).onChanged(dummyMovie)


        val expected = MutableLiveData<Resource<MoviesEntity>>()
        //expected.value = Resource.success(DataDummy.generateDummyMovies(dummyMovie))

        Mockito.`when`(catalogueRepository.getDetailMovie(movieId)).thenReturn(expected)

        viewModel.getMovie.observeForever(movieObserver)

        //Mockito.verify(movieObserver).onChanged(expected.value)

    }
}