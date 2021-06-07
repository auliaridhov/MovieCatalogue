package com.tik.moviecatalogue.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.tik.moviecatalogue.data.source.local.entity.TvShowEntity
import com.tik.moviecatalogue.data.source.CatalogueRepository
import com.tik.moviecatalogue.data.source.local.entity.MoviesEntity
import com.tik.moviecatalogue.vo.Resource

class DetailTvShowViewModel (private val catalogueRepository: CatalogueRepository)  : ViewModel()   {

    var tvShowId = MutableLiveData<String>()

    fun setSelectedTv(tvShowId: String) {
        this.tvShowId.value = tvShowId
    }

    var getTv: LiveData<Resource<TvShowEntity>> =
        Transformations.switchMap(tvShowId) { tvId: String ->
            catalogueRepository.getDetailTv(
                tvId
            )
        }

    fun setFavoriteTv() {
        if (getTv.getValue() != null) {
            val tvEntity: TvShowEntity? = getTv.value?.data
            if (tvEntity != null) {
//                val state: Boolean? = tvEntity.favorited
//                state?.let { catalogueRepository.setTvFavorited(tvEntity, it) }

                val newState = !tvEntity.favorited!!
                catalogueRepository.setTvFavorited(tvEntity, newState)
            }
        }
    }

}