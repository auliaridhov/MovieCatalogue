package com.tik.moviecatalogue.ui.favorite.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.academies.viewmodel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar
import com.tik.moviecatalogue.R
import com.tik.moviecatalogue.databinding.FragmentMovieFavoriteBinding
import com.tik.moviecatalogue.databinding.FragmentMoviesBinding
import com.tik.moviecatalogue.ui.movie.DetailMovieViewModel
import com.tik.moviecatalogue.ui.movie.MoviesAdapter
import com.tik.moviecatalogue.ui.movie.MoviesViewModel
import com.tik.moviecatalogue.vo.Status


class MovieFavoriteFragment : Fragment() {


    private lateinit var fragmentMovieFavoriteBinding: FragmentMovieFavoriteBinding


    private lateinit var viewModel: MoviesViewModel
    private lateinit var moviesAdapter: MovieFavoriteAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentMovieFavoriteBinding = FragmentMovieFavoriteBinding.inflate(layoutInflater, container, false)
        return fragmentMovieFavoriteBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemTouchHelper.attachToRecyclerView(fragmentMovieFavoriteBinding?.rvMoviesFav)
        if (activity != null) {

            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[MoviesViewModel::class.java]


            moviesAdapter = MovieFavoriteAdapter()
            viewModel.getFavMovies().observe(this, { movies ->
                if (movies != null) {
                    fragmentMovieFavoriteBinding?.progressBar?.visibility = View.GONE
                    moviesAdapter.submitList(movies)
                }
            })
            with(fragmentMovieFavoriteBinding?.rvMoviesFav) {
                this?.layoutManager = LinearLayoutManager(context)
                this?.setHasFixedSize(true)
                this?.adapter = moviesAdapter
            }
        }
    }

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int =
            makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean = true
        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (view != null) {
                val swipedPosition = viewHolder.adapterPosition
                val courseEntity = moviesAdapter.getSwipedData(swipedPosition)
                courseEntity?.let { viewModel.setFavorite(it) }
                val snackbar = Snackbar.make(view as View, R.string.message_undo, Snackbar.LENGTH_LONG)
                snackbar.setAction(R.string.message_ok) { v ->
                    courseEntity?.let { viewModel.setFavorite(it) }
                }
                snackbar.show()
            }
        }
    })

}