package com.tik.moviecatalogue.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dicoding.academies.utils.LiveDataTestUtil
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import com.tik.moviecatalogue.data.source.remote.RemoteDataSource
import com.tik.moviecatalogue.utils.DataDummy
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.mockito.Mockito

class CatalogueRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val fakeCatalogueRepository = FakeCatalogueRepository(remote)
    private val movieResponses = DataDummy.generateRemoteDummyMovies()
    private val movieDetailResponses = DataDummy.generateRemoteDetailMovie()
    private val movieId = movieResponses[0].id
    private val tvResponses = DataDummy.generateRemoteDummyTvShow()
    private val tvDetailResponses = DataDummy.generateRemoteDetailTv()
    private val tvId = tvResponses[0].id

    @Test
    fun getAllMovie() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadMovieCallback)
                .onAllMovieReceived(movieResponses)
            null
        }.`when`(remote).getMovieList(any())
        val movieEntities = LiveDataTestUtil.getValue(fakeCatalogueRepository.getAllMovie())
        verify(remote).getMovieList(any())
        assertNotNull(movieEntities)
        assertEquals(movieResponses.size.toLong(), movieEntities.size.toLong())
    }

    @Test
    fun getAllTv() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadTvCallback)
                .onAllTvReceived(tvResponses)
            null
        }.`when`(remote).getTvList(any())
        val tvEntities = LiveDataTestUtil.getValue(fakeCatalogueRepository.getAllTv())
        verify(remote).getTvList(any())
        assertNotNull(tvEntities)
        assertEquals(tvResponses.size.toLong(), tvEntities.size.toLong())
    }

    @Test
    fun getDetailMovie() {
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadDetailMovieCallback).onDetailMovieReceived(movieDetailResponses)
            null
        }.`when`(remote).detailMovie(eq(movieId.toString()), any())

        val movieDetailEntity = LiveDataTestUtil.getValue(fakeCatalogueRepository.getDetailMovie(movieId.toString()))
        verify(remote).detailMovie(eq(movieId.toString()), any())
        assertNotNull(movieDetailEntity)
        assertEquals(movieDetailResponses.id, movieDetailEntity.id)
    }

    @Test
    fun getDetailTv() {
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadDetailTvCallback).onDetailTvReceived(tvDetailResponses)
            null
        }.`when`(remote).detailTv(eq(tvId.toString()), any())

        val tvShowDetailEntity = LiveDataTestUtil.getValue(fakeCatalogueRepository.getDetailTv(tvId.toString()))
        verify(remote).detailTv(eq(tvId.toString()), any())
        assertNotNull(tvShowDetailEntity)
        assertEquals(tvDetailResponses.id, tvShowDetailEntity.id)
    }
}