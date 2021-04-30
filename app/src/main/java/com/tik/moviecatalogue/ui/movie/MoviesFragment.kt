package com.tik.moviecatalogue.ui.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.tik.moviecatalogue.R
import com.tik.moviecatalogue.databinding.FragmentMoviesBinding


class MoviesFragment : Fragment() {

    private lateinit var fragmenMoviesBinding: FragmentMoviesBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        fragmenMoviesBinding = FragmentMoviesBinding.inflate(layoutInflater, container, false)
        return fragmenMoviesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[MoviesViewModel::class.java]
            val movies = viewModel.getMovies()


            val moviesAdapter = MoviesAdapter()
            moviesAdapter.setMovies(movies)

            with(fragmenMoviesBinding.rvMovies) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = moviesAdapter
            }
        }
    }
}