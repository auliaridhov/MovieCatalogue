package com.tik.moviecatalogue.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.DataSource
import com.nhaarman.mockitokotlin2.verify
import com.tik.moviecatalogue.data.source.local.LocalDataSource
import com.tik.moviecatalogue.data.source.local.entity.MoviesEntity
import com.tik.moviecatalogue.data.source.local.entity.TvShowEntity
import com.tik.moviecatalogue.data.source.remote.RemoteDataSource
import com.tik.moviecatalogue.utils.AppExecutors
import com.tik.moviecatalogue.utils.DataDummy
import com.tik.moviecatalogue.utils.PagedListUtil
import com.tik.moviecatalogue.vo.Resource
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class CatalogueRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private val remote = Mockito.mock(RemoteDataSource::class.java)

    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)
    private val fakeCatalogueRepository = FakeCatalogueRepository(remote, local, appExecutors)
    private val movieResponses = DataDummy.generateRemoteDummyMovies()
    private val movieDetailResponses = DataDummy.generateRemoteDetailMovie()
    private val movieId = movieResponses[0].id
    private val tvResponses = DataDummy.generateRemoteDummyTvShow()
    private val tvDetailResponses = DataDummy.generateRemoteDetailTv()
    private val tvId = tvResponses[0].id




    @Test
    fun getAllMovie() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MoviesEntity>
        `when`(local.getAllMovies()).thenReturn(dataSourceFactory)
        fakeCatalogueRepository.getAllMovie()

        val movieEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateRemoteDummyMovies()))

        verify(local).getAllMovies()
        assertNotNull(movieEntities.data)
        assertEquals(movieResponses.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun getAllTv() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(local.getAllTvShow()).thenReturn(dataSourceFactory)
        fakeCatalogueRepository.getAllTv()

        val tvEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateRemoteDummyTvShow()))

        verify(local).getAllTvShow()
        assertNotNull(tvEntities.data)
        assertEquals(tvResponses.size.toLong(), tvEntities.data?.size?.toLong())
    }

    @Test
    fun getDetailMovie() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MoviesEntity>
        `when`(local.getAllMovies()).thenReturn(dataSourceFactory)
        fakeCatalogueRepository.getDetailMovie(movieId.toString())

        val movieDetailEntity = Resource.success(DataDummy.generateRemoteDetailMovie())
        if (movieId != null) {
            verify(local).getMoviesById(movieId)
        }
        assertNotNull(movieDetailEntity)
        assertEquals(movieDetailResponses.id, movieDetailEntity.data?.id)
    }

    @Test
    fun getDetailTv() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(local.getAllTvShow()).thenReturn(dataSourceFactory)
        fakeCatalogueRepository.getDetailTv(movieId.toString())

        val tvShowDetailEntity = Resource.success(DataDummy.generateRemoteDetailTv())
        assertNotNull(tvShowDetailEntity)
        assertEquals(tvDetailResponses.id, tvShowDetailEntity.data?.id)
    }
}