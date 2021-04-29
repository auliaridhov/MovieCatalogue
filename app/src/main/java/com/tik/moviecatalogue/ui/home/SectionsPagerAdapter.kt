package com.tik.moviecatalogue.ui.home

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.tik.moviecatalogue.R
import com.tik.moviecatalogue.ui.movie.MoviesFragment
import com.tik.moviecatalogue.ui.tvshow.TvShowFragment

class SectionsPagerAdapter(private val mContext: Context, fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.movies, R.string.tvshow)
    }

    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> MoviesFragment()
            1 -> TvShowFragment()
            else -> Fragment()
        }

    override fun getPageTitle(position: Int): CharSequence = mContext.resources.getString(TAB_TITLES[position])

    override fun getCount(): Int = 2

}