package com.tik.moviecatalogue.ui.tvshow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.academies.viewmodel.ViewModelFactory
import com.tik.moviecatalogue.R
import com.tik.moviecatalogue.databinding.FragmentMoviesBinding
import com.tik.moviecatalogue.databinding.FragmentTvShowBinding
import com.tik.moviecatalogue.ui.movie.MoviesAdapter
import com.tik.moviecatalogue.ui.movie.MoviesViewModel


class TvShowFragment : Fragment() {


    private lateinit var fragmentTvShowBinding: FragmentTvShowBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        fragmentTvShowBinding = FragmentTvShowBinding.inflate(layoutInflater, container, false)
        return fragmentTvShowBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {


            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[TvShowViewModel::class.java]

            val tvShowAdapter = TvShowAdapter()

            fragmentTvShowBinding.progressBar.visibility = View.VISIBLE
            viewModel.getTvSHow().observe(this, { tv ->
                fragmentTvShowBinding.progressBar.visibility = View.GONE
                tvShowAdapter.setTvShow(tv)
                tvShowAdapter.notifyDataSetChanged()
            })

            with(fragmentTvShowBinding.rvTvShow) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvShowAdapter
            }
        }
    }


}