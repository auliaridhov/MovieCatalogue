package com.tik.moviecatalogue.ui.favorite.tvshow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.academies.viewmodel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar
import com.tik.moviecatalogue.R
import com.tik.moviecatalogue.databinding.FragmentMovieFavoriteBinding
import com.tik.moviecatalogue.databinding.FragmentTvShowFavoriteBinding
import com.tik.moviecatalogue.ui.favorite.movie.MovieFavoriteAdapter
import com.tik.moviecatalogue.ui.movie.MoviesViewModel
import com.tik.moviecatalogue.ui.tvshow.TvShowViewModel


class TvShowFavoriteFragment : Fragment() {


    private lateinit var fragmentTvShowFavoriteBinding: FragmentTvShowFavoriteBinding


    private lateinit var viewModel: TvShowViewModel
    private lateinit var tvShowFavoriteAdapter: TvShowFavoriteAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentTvShowFavoriteBinding = FragmentTvShowFavoriteBinding.inflate(layoutInflater, container, false)
        return fragmentTvShowFavoriteBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemTouchHelper.attachToRecyclerView(fragmentTvShowFavoriteBinding?.rvTvShowFav)
        if (activity != null) {

            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[TvShowViewModel::class.java]


            tvShowFavoriteAdapter = TvShowFavoriteAdapter()
            viewModel.getFavTvShow().observe(this, { movies ->
                if (movies != null) {
                    fragmentTvShowFavoriteBinding?.progressBar?.visibility = View.GONE
                    tvShowFavoriteAdapter.submitList(movies)
                }
            })
            with(fragmentTvShowFavoriteBinding?.rvTvShowFav) {
                this?.layoutManager = LinearLayoutManager(context)
                this?.setHasFixedSize(true)
                this?.adapter = tvShowFavoriteAdapter
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
                val courseEntity = tvShowFavoriteAdapter.getSwipedData(swipedPosition)
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