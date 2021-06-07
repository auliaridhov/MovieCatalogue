package com.dicoding.academies.di

import android.content.Context
import com.tik.moviecatalogue.data.source.CatalogueRepository
import com.tik.moviecatalogue.data.source.local.LocalDataSource
import com.tik.moviecatalogue.data.source.local.room.CatalogueDatabase
import com.tik.moviecatalogue.data.source.remote.RemoteDataSource
import com.tik.moviecatalogue.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): CatalogueRepository {

        val database = CatalogueDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource()
        val localDataSource = LocalDataSource.getInstance(database.catalogueDao())
        val appExecutors = AppExecutors()

        return CatalogueRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}