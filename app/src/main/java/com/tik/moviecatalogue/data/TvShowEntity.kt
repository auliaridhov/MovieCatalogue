package com.tik.moviecatalogue.data


data class TvShowEntity(
    var id: String,
    var name: String,
    var overview: String,
    var first_air_date: String,
    var poster_path: String,
    var vote_average: Double
)