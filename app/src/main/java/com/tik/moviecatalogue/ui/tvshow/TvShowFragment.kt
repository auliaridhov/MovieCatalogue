package com.tik.moviecatalogue.ui.tvshow

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
import com.tik.moviecatalogue.databinding.FragmentTvShowBinding
import com.tik.moviecatalogue.ui.movie.MoviesAdapter
import com.tik.moviecatalogue.ui.movie.MoviesViewModel
import com.tik.moviecatalogue.vo.Status


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
            viewModel.getTvSHow().observe(this, { tvs ->
                if (tvs != null) {
                    when (tvs.status) {
                        Status.LOADING -> fragmentTvShowBinding?.progressBar?.visibility = View.VISIBLE
                        Status.SUCCESS -> {
                            fragmentTvShowBinding?.progressBar?.visibility = View.GONE
                            tvShowAdapter.submitList(tvs.data)
                        }
                        Status.ERROR -> {
                            fragmentTvShowBinding?.progressBar?.visibility = View.GONE
                            Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })
            with(fragmentTvShowBinding?.rvTvShow) {
                this?.layoutManager = LinearLayoutManager(context)
                this?.setHasFixedSize(true)
                this?.adapter = tvShowAdapter
            }
        }
    }


}