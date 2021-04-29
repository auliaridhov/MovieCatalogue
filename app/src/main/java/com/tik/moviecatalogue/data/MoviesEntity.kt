package com.tik.moviecatalogue.data

data class MoviesEntity(
    var id: Int,
    var title: String,
    var overview: String,
    var release_date: String,
    var poster_path: String,
    var vote_average: Double
)
