package com.tik.moviecatalogue.ui.movie

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
import com.tik.moviecatalogue.databinding.ItemsMoviesBinding
import java.util.ArrayList


class MoviesAdapter  : PagedListAdapter<MoviesEntity, MoviesAdapter.CourseViewHolder>(DIFF_CALLBACK)  {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MoviesEntity>() {
            override fun areItemsTheSame(oldItem: MoviesEntity, newItem: MoviesEntity): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: MoviesEntity, newItem: MoviesEntity): Boolean {
                return oldItem == newItem
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val itemsAcademyBinding = ItemsMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CourseViewHolder(itemsAcademyBinding)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val course = getItem(position)
        if (course != null) {
            holder.bind(course)
        }
    }



    class CourseViewHolder(private val binding: ItemsMoviesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movies: MoviesEntity) {
            with(binding) {
                tvItemTitle.text = movies.title
                tvItemDescription.text = movies.overview
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailMovieActivity::class.java)
                    intent.putExtra(DetailMovieActivity.EXTRA_MOVIE, movies.id.toString())
                    itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w500"+movies.posterPath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                    .into(imgPoster)
            }
        }
    }
}