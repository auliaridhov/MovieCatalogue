package com.tik.moviecatalogue.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.DataSource
import androidx.paging.PagedList
import com.tik.moviecatalogue.data.source.local.entity.MoviesEntity
import com.tik.moviecatalogue.data.source.CatalogueRepository
import com.tik.moviecatalogue.utils.DataDummy
import com.tik.moviecatalogue.vo.Resource
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class MoviesViewModelTest {

    private lateinit var viewModel: MoviesViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogueRepository: CatalogueRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<MoviesEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<MoviesEntity>

    @Before
    fun setUp() {
        viewModel = MoviesViewModel(catalogueRepository)
    }


    @Test
    fun getMovies() {

        val dummyCourses = Resource.success(pagedList)
        `when`(dummyCourses.data?.size).thenReturn(5)
        val courses = MutableLiveData<Resource<PagedList<MoviesEntity>>>()
        courses.value = dummyCourses

        `when`(catalogueRepository.getAllMovie()).thenReturn(courses)
        val courseEntities = viewModel.getMovies().value?.data
        verify(catalogueRepository).getAllMovie()
        assertNotNull(courseEntities)
        assertEquals(5, courseEntities?.size)

        viewModel.getMovies().observeForever(observer)
        verify(observer).onChanged(dummyCourses)


    }
}