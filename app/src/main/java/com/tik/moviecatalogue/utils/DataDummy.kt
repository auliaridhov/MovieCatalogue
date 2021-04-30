package com.tik.moviecatalogue.utils

import com.tik.moviecatalogue.data.MoviesEntity
import com.tik.moviecatalogue.data.TvShowEntity
import java.util.ArrayList

object DataDummy {

    fun generateDummyMovies(): List<MoviesEntity> {

        val movies = ArrayList<MoviesEntity>()

        movies.add(MoviesEntity("460465",
            "Mortal Kombat",
            "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
            "2021-04-07",
            "https://image.tmdb.org/t/p/w500/6Wdl9N6dL0Hi0T1qJLWSz6gMLbd.jpg",
            7.9))
        movies.add(MoviesEntity("635302",
            "Demon Slayer the Movie: Mugen Train",
            "Tanjirō Kamado, joined with Inosuke Hashibira, a boy raised by boars who wears a boar's head, and Zenitsu Agatsuma, a scared boy who reveals his true power when he sleeps, boards the Infinity Train on a new mission with the Fire Hashira, Kyōjurō Rengoku, to defeat a demon who has been tormenting the people and killing the demon slayers who oppose it!",
            "2020-10-16",
            "https://image.tmdb.org/t/p/w500/h8Rb9gBr48ODIwYUttZNYeMWeUU.jpg",
            8.2,))
        movies.add(MoviesEntity("736069",
            "Justice Society: World War II",
            "While speeding off to help in an impromptu battle, The Flash blazes and rips through time, only to find himself dropped into the middle of World War II. It’s here that The Flash meets Wonder Woman and her top secret team, known as the Justice Society of America. Witness the raging tides of war, gripping combat and the velocity of valor as The Flash fights to get back to his own timeline!",
            "2021-04-27",
            "https://image.tmdb.org/t/p/w500/e4REOC6CZW8J6FslA4nRvdQXFXR.jpg",
            8.3))
        movies.add(MoviesEntity("581734",
            "Nomadland",
            "A woman in her sixties embarks on a journey through the western United States after losing everything in the Great Recession, living as a van-dwelling modern-day nomad.",
            "2021-01-29",
            "https://image.tmdb.org/t/p/w500/66GUmWpTHgAjyp4aBSXy63PZTiC.jpg",
            7.4,))
        movies.add(MoviesEntity("399566",
            "Godzilla vs. Kong",
            "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.",
            "2021-03-24",
            "https://image.tmdb.org/t/p/w500/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
            8.2))

        movies.add(MoviesEntity("559581",
            "Stowaway",
            "A three-person crew on a mission to Mars faces an impossible choice when an unplanned passenger jeopardizes the lives of everyone on board.",
            "2021-04-22",
            "https://image.tmdb.org/t/p/w500/yOscLK7KzEPDdi1P8RmevGIjOSp.jpg",
            6.2))
        movies.add(MoviesEntity("399566",
            "The Marksman",
            "Jim Hanson’s quiet life is suddenly disturbed by two people crossing the US/Mexico border – a woman and her young son – desperate to flee a Mexican cartel. After a shootout leaves the mother dead, Jim becomes the boy’s reluctant defender. He embraces his role as Miguel’s protector and will stop at nothing to get him to safety, as they go on the run from the relentless assassins.",
            "2021-01-15",
            "https://image.tmdb.org/t/p/w500/6vcDalR50RWa309vBH1NLmG2rjQ.jpg",
            7.3))
        movies.add(MoviesEntity("808023",
            "The Virtuoso",
            "A lonesome stranger with nerves of steel must track down and kill a rogue hitman to satisfy an outstanding debt. But the only information he's been given is a time and location where to find his quarry. No name. No description. Nothing.",
            "2021-04-30",
            "https://image.tmdb.org/t/p/w500/8RIjjmWZi8tJf5lRxyHGErentsv.jpg",
            7.0,))
        movies.add(MoviesEntity("615457",
            "Nobody",
            "Hutch Mansell, a suburban dad, overlooked husband, nothing neighbor — a \\\"nobody.\\\" When two thieves break into his home one night, Hutch's unknown long-simmering rage is ignited and propels him on a brutal path that will uncover dark secrets he fought to leave behind.",
            "2021-03-18",
            "https://image.tmdb.org/t/p/w500/oBgWY00bEFeZ9N25wWVyuQddbAo.jpg",
            8.5))
        movies.add(MoviesEntity("600354",
            "The Father",
            "A man refuses all assistance from his daughter as he ages and, as he tries to make sense of his changing circumstances, he begins to doubt his loved ones, his own mind and even the fabric of his reality.",
            "2020-12-23",
            "https://image.tmdb.org/t/p/w500/uxWXW1YYQENSv7OzHB4Hds0bK3b.jpg",
            8.4))
        return movies
    }



    fun generateDummyTvShow(): List<TvShowEntity> {

        val tvShow = ArrayList<TvShowEntity>()

        tvShow.add(TvShowEntity("460465",
            "Mortal Kombat",
            "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
            "2021-04-07",
            "https://image.tmdb.org/t/p/w500/6Wdl9N6dL0Hi0T1qJLWSz6gMLbd.jpg",
            7.9))
        tvShow.add(TvShowEntity("635302",
            "Demon Slayer the Movie: Mugen Train",
            "Tanjirō Kamado, joined with Inosuke Hashibira, a boy raised by boars who wears a boar's head, and Zenitsu Agatsuma, a scared boy who reveals his true power when he sleeps, boards the Infinity Train on a new mission with the Fire Hashira, Kyōjurō Rengoku, to defeat a demon who has been tormenting the people and killing the demon slayers who oppose it!",
            "2020-10-16",
            "https://image.tmdb.org/t/p/w500/h8Rb9gBr48ODIwYUttZNYeMWeUU.jpg",
            8.2,))
        tvShow.add(TvShowEntity("736069",
            "Justice Society: World War II",
            "While speeding off to help in an impromptu battle, The Flash blazes and rips through time, only to find himself dropped into the middle of World War II. It’s here that The Flash meets Wonder Woman and her top secret team, known as the Justice Society of America. Witness the raging tides of war, gripping combat and the velocity of valor as The Flash fights to get back to his own timeline!",
            "2021-04-27",
            "https://image.tmdb.org/t/p/w500/e4REOC6CZW8J6FslA4nRvdQXFXR.jpg",
            8.3))
        tvShow.add(TvShowEntity("581734",
            "Nomadland",
            "A woman in her sixties embarks on a journey through the western United States after losing everything in the Great Recession, living as a van-dwelling modern-day nomad.",
            "2021-01-29",
            "https://image.tmdb.org/t/p/w500/66GUmWpTHgAjyp4aBSXy63PZTiC.jpg",
            7.4,))
        tvShow.add(TvShowEntity("399566",
            "Godzilla vs. Kong",
            "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.",
            "2021-03-24",
            "https://image.tmdb.org/t/p/w500/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
            8.2))

        tvShow.add(TvShowEntity("559581",
            "Stowaway",
            "A three-person crew on a mission to Mars faces an impossible choice when an unplanned passenger jeopardizes the lives of everyone on board.",
            "2021-04-22",
            "https://image.tmdb.org/t/p/w500/yOscLK7KzEPDdi1P8RmevGIjOSp.jpg",
            6.2))
        tvShow.add(TvShowEntity("399566",
            "The Marksman",
            "Jim Hanson’s quiet life is suddenly disturbed by two people crossing the US/Mexico border – a woman and her young son – desperate to flee a Mexican cartel. After a shootout leaves the mother dead, Jim becomes the boy’s reluctant defender. He embraces his role as Miguel’s protector and will stop at nothing to get him to safety, as they go on the run from the relentless assassins.",
            "2021-01-15",
            "https://image.tmdb.org/t/p/w500/6vcDalR50RWa309vBH1NLmG2rjQ.jpg",
            7.3))
        tvShow.add(TvShowEntity("808023",
            "The Virtuoso",
            "A lonesome stranger with nerves of steel must track down and kill a rogue hitman to satisfy an outstanding debt. But the only information he's been given is a time and location where to find his quarry. No name. No description. Nothing.",
            "2021-04-30",
            "https://image.tmdb.org/t/p/w500/8RIjjmWZi8tJf5lRxyHGErentsv.jpg",
            7.0,))
        tvShow.add(TvShowEntity("615457",
            "Nobody",
            "Hutch Mansell, a suburban dad, overlooked husband, nothing neighbor — a \\\"nobody.\\\" When two thieves break into his home one night, Hutch's unknown long-simmering rage is ignited and propels him on a brutal path that will uncover dark secrets he fought to leave behind.",
            "2021-03-18",
            "https://image.tmdb.org/t/p/w500/oBgWY00bEFeZ9N25wWVyuQddbAo.jpg",
            8.5))
        tvShow.add(TvShowEntity("600354",
            "The Father",
            "A man refuses all assistance from his daughter as he ages and, as he tries to make sense of his changing circumstances, he begins to doubt his loved ones, his own mind and even the fabric of his reality.",
            "2020-12-23",
            "https://image.tmdb.org/t/p/w500/uxWXW1YYQENSv7OzHB4Hds0bK3b.jpg",
            8.4))
        return tvShow
    }

}