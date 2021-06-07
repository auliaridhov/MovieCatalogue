package com.tik.moviecatalogue.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.tik.moviecatalogue.data.source.local.entity.TvShowEntity
import com.tik.moviecatalogue.data.source.CatalogueRepository
import com.tik.moviecatalogue.data.source.local.entity.MoviesEntity
import com.tik.moviecatalogue.vo.Resource

class TvShowViewModel (private val catalogueRepository: CatalogueRepository)  : ViewModel()  {
    fun getTvSHow(): LiveData<Resource<PagedList<TvShowEntity>>> = catalogueRepository.getAllTv()

    fun getFavTvShow(): LiveData<PagedList<TvShowEntity>> = catalogueRepository.getFavoritedTvShow()

    fun setFavorite(tvShowEntity: TvShowEntity) {
        val newState = !tvShowEntity.favorited!!
        catalogueRepository.setTvFavorited(tvShowEntity, newState)
    }

}