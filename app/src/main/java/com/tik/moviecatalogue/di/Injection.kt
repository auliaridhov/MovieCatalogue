package com.dicoding.academies.di

import android.content.Context
import com.tik.moviecatalogue.data.source.CatalogueRepository
import com.tik.moviecatalogue.data.source.remote.RemoteDataSource

object Injection {
    fun provideRepository(context: Context): CatalogueRepository {

        val remoteDataSource = RemoteDataSource()

        return CatalogueRepository.getInstance(remoteDataSource)
    }
}