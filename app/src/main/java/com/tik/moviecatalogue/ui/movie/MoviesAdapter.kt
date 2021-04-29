package com.tik.moviecatalogue.ui.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.tik.moviecatalogue.R
import com.tik.moviecatalogue.data.MoviesEntity
import com.tik.moviecatalogue.databinding.ItemsMoviesBinding
import java.util.ArrayList

class MoviesAdapter  : RecyclerView.Adapter<MoviesAdapter.CourseViewHolder>() {
    private var listMovies = ArrayList<MoviesEntity>()

    fun setMovies(movies: List<MoviesEntity>?) {
        if (movies == null) return
        this.listMovies.clear()
        this.listMovies.addAll(movies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val itemsAcademyBinding = ItemsMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CourseViewHolder(itemsAcademyBinding)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val course = listMovies[position]
        holder.bind(course)
    }

    override fun getItemCount(): Int = listMovies.size


    class CourseViewHolder(private val binding: ItemsMoviesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movies: MoviesEntity) {
            with(binding) {
                tvItemTitle.text = movies.title
                tvItemDescription.text = movies.overview
//                itemView.setOnClickListener {
//                    val intent = Intent(itemView.context, DetailCourseActivity::class.java)
//                    intent.putExtra(DetailCourseActivity.EXTRA_COURSE, movies.courseId)
//                    itemView.context.startActivity(intent)
//                }
                Glide.with(itemView.context)
                    .load(movies.poster_path)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                    .into(imgPoster)
            }
        }
    }
}