package com.tik.moviecatalogue.utils

import com.tik.moviecatalogue.data.MoviesEntity
import com.tik.moviecatalogue.data.TvShowEntity
import java.util.*

object DataDummy {

    fun generateDummyMovies(): List<MoviesEntity> {

        val genreIds: MutableList<Int> = ArrayList()
        genreIds.add(21)
        genreIds.add(32)

        val movies = ArrayList<MoviesEntity>()

        movies.add(
            MoviesEntity(
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                "en",
                "Mortal Kombat",
                false,
                "Mortal Kombat",
                genreIds,
                "/nkayOAUBUu4mMvyNf9iHSUiPjF1.jpg",
                "/6ELCZlTA5lGUops70hKdB83WJxH.jpg",
                "2021-04-07",
                2612.747,
                7.6,
                460465,
                false,
                2497
            )
        )
        movies.add(
            MoviesEntity(
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                "en",
                "Godzilla vs. Kong",
                false,
                "Godzilla vs. Kong",
                genreIds,
                "/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                "/inJjDhCjfhh3RtrJWBmmDqeuSYC.jpg",
                "2021-03-24",
                1775.66,
                8.1,
                399566,
                false,
                5525
            )
        )
        movies.add(
            MoviesEntity(
                "Hutch Mansell, a suburban dad, overlooked husband, nothing neighbor — a \\\"nobody.\\\" When two thieves break into his home one night, Hutch's unknown long-simmering rage is ignited and propels him on a brutal path that will uncover dark secrets he fought to leave behind.",
                "en",
                "Nobody",
                false,
                "Nobody",
                genreIds,
                "/oBgWY00bEFeZ9N25wWVyuQddbAo.jpg",
                "/6zbKgwgaaCyyBXE4Sun4oWQfQmi.jpg",
                "2021-03-26",
                1388.757,
                8.4,
                615457,
                false,
                1509
            )
        )

        return movies
    }



    fun generateDummyTvShow(): List<TvShowEntity> {

        val tvShow = ArrayList<TvShowEntity>()

        val genreIds: MutableList<Int> = ArrayList()
        genreIds.add(21)
        genreIds.add(32)

        val originCountry: MutableList<String> = ArrayList()
        originCountry.add("US")
        originCountry.add("US")

        tvShow.add(
            TvShowEntity(
                "2014-10-07",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \\\"meta-human\\\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                "en",
                genreIds,
                "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
                originCountry,
                "/jeruqNWhqRqOR1QyqdQdHunrvU5.jpg",
                "The Flash",
                1067.857,
                7.7,
                "The Flash",
                60735,
                7623
            )
        )
        tvShow.add(
            TvShowEntity(
                "2017-09-25",
                "A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives",
                "en",
                genreIds,
                "/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
                originCountry,
                "/mZjZgY6ObiKtVuKVDrnS9VnuNlE.jpg",
                "The Good Doctor",
                1087.176,
                8.6,
                "The Good Doctor",
                71712,
                8402
            )
        )
        tvShow.add(
            TvShowEntity(
                "2005-03-27",
                "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
                "en",
                genreIds,
                "/clnyhPqj1SNgpAdeSS6a6fwE6Bo.jpg",
                originCountry,
                "/edmk8xjGBsYVIf4QtLY9WMaMcXZ.jpg",
                "Grey's Anatomy",
                774.332,
                8.2,
                "Grey's Anatomy",
                1416,
                6059
            )
        )

        return tvShow
    }

}