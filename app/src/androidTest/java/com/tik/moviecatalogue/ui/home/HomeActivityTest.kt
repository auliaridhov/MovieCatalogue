package com.tik.moviecatalogue.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.dicoding.academies.utils.EspressoIdlingResource
import com.tik.moviecatalogue.R
import com.tik.moviecatalogue.utils.DataDummy
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeActivityTest {

    private val dummyMovie = DataDummy.generateDummyMovies()
    private val dummyTv = DataDummy.generateDummyTvShow()

    @get:Rule
    var activityRule = ActivityScenarioRule(HomeActivity::class.java)



    @Before
    fun setUp() {
        ActivityScenario.launch(HomeActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource .idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun loadMovie() {
        delayThreeSecond()
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovie.size))
    }

    @Test
    fun loadDetailMovie() {
        delayThreeSecond()
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        delayThreeSecond()
        onView(withId(R.id.detailTitle)).check(matches(isDisplayed()))
        onView(withId(R.id.detailTitle)).check(matches(withText(dummyMovie[0].title)))
        onView(withId(R.id.dateDetail)).check(matches(isDisplayed()))
        onView(withId(R.id.dateDetail)).check(matches(withText(dummyMovie[0].releaseDate)))
    }

    @Test
    fun loadTvShow() {
        onView(withText("Tv Show")).perform(click())
        delayThreeSecond()
        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovie.size))
    }

    @Test
    fun loadDetailTvShow() {
        onView(withText("Tv Show")).perform(click())
        delayThreeSecond()
        onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        delayThreeSecond()
        onView(withId(R.id.detailTitle)).check(matches(isDisplayed()))
        onView(withId(R.id.detailTitle)).check(matches(withText(dummyTv[0].name)))
        onView(withId(R.id.dateDetail)).check(matches(isDisplayed()))
        onView(withId(R.id.dateDetail)).check(matches(withText(dummyTv[0].firstAirDate)))
    }

    private fun delayThreeSecond() {
        try {
            Thread.sleep(3000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}