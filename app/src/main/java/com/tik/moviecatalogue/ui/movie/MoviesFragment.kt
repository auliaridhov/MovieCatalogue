package com.tik.moviecatalogue.ui.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.academies.viewmodel.ViewModelFactory
import com.tik.moviecatalogue.R
import com.tik.moviecatalogue.databinding.FragmentMoviesBinding
import com.tik.moviecatalogue.vo.Status


class MoviesFragment : Fragment() {

    private lateinit var fragmenMoviesBinding: FragmentMoviesBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        fragmenMoviesBinding = FragmentMoviesBinding.inflate(layoutInflater, container, false)
        return fragmenMoviesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[MoviesViewModel::class.java]


            val moviesAdapter = MoviesAdapter()
            viewModel.getMovies().observe(this, { movies ->
                if (movies != null) {
                    when (movies.status) {
                        Status.LOADING -> fragmenMoviesBinding?.progressBar?.visibility = View.VISIBLE
                        Status.SUCCESS -> {
                            fragmenMoviesBinding?.progressBar?.visibility = View.GONE
                            moviesAdapter.submitList(movies.data)

                        }
                        Status.ERROR -> {
                            fragmenMoviesBinding?.progressBar?.visibility = View.GONE
                            Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })
            with(fragmenMoviesBinding?.rvMovies) {
                this?.layoutManager = LinearLayoutManager(context)
                this?.setHasFixedSize(true)
                this?.adapter = moviesAdapter
            }
        }
    }
}