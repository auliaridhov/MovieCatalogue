package com.tik.moviecatalogue.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.tik.moviecatalogue.data.source.local.entity.TvShowEntity
import com.tik.moviecatalogue.data.source.CatalogueRepository
import com.tik.moviecatalogue.data.source.local.entity.MoviesEntity
import com.tik.moviecatalogue.utils.DataDummy
import com.tik.moviecatalogue.vo.Resource
import junit.framework.Assert
import org.junit.Test

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
    private lateinit var observer: Observer<Resource<PagedList<TvShowEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<TvShowEntity>

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(catalogueRepository)
    }

    @Test
    fun getTvs() {
//        val resource: List<TvShowEntity> = DataDummy.generateDummyTvShow()
//        val tv: MutableLiveData<List<TvShowEntity>> = MutableLiveData<List<TvShowEntity>>()
//        tv.value = resource
//        Mockito.`when`(catalogueRepository.getAllTv()).thenReturn(tv)
//        viewModel.getTvSHow().observeForever(observer)
//        Mockito.verify<Observer<List<TvShowEntity>>>(observer).onChanged(resource)


        val dummyCourses = Resource.success(pagedList)
        Mockito.`when`(dummyCourses.data?.size).thenReturn(5)
        val courses = MutableLiveData<Resource<PagedList<TvShowEntity>>>()
        courses.value = dummyCourses

        Mockito.`when`(catalogueRepository.getAllTv()).thenReturn(courses)
        val courseEntities = viewModel.getTvSHow().value?.data
        Mockito.verify(catalogueRepository).getAllTv()
        Assert.assertNotNull(courseEntities)
        Assert.assertEquals(5, courseEntities?.size)

        viewModel.getTvSHow().observeForever(observer)
        Mockito.verify(observer).onChanged(dummyCourses)
    }
}