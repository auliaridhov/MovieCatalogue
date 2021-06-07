package com.tik.moviecatalogue.ui.favorite.tvshow

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.tik.moviecatalogue.R
import com.tik.moviecatalogue.data.source.local.entity.MoviesEntity
import com.tik.moviecatalogue.data.source.local.entity.TvShowEntity
import com.tik.moviecatalogue.databinding.ItemsTvShowBinding
import com.tik.moviecatalogue.ui.tvshow.DetailTvShowActivity
import java.util.ArrayList

class TvShowFavoriteAdapter : PagedListAdapter<TvShowEntity, TvShowFavoriteAdapter.CourseViewHolder>(TvShowFavoriteAdapter.DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShowEntity>() {
            override fun areItemsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    fun getSwipedData(swipedPosition: Int): TvShowEntity? = getItem(swipedPosition)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val itemsAcademyBinding = ItemsTvShowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CourseViewHolder(itemsAcademyBinding)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val course = getItem(position)
        if (course != null) {
            holder.bind(course)
        }
    }

    class CourseViewHolder(private val binding: ItemsTvShowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TvShowEntity) {
            with(binding) {
                tvItemTitle.text = tvShow.name
                tvItemDescription.text = tvShow.overview
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailTvShowActivity::class.java)
                    intent.putExtra(DetailTvShowActivity.EXTRA_TV, tvShow.id.toString())
                    itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w500"+tvShow.posterPath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error))
                    .into(imgPoster)
            }
        }
    }
}