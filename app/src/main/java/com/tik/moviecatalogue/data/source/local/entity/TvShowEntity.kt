package com.tik.moviecatalogue.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "tvshowentities")
data class TvShowEntity(

        @ColumnInfo(name = "firstAirDate")
        var firstAirDate: String? = null,

        @ColumnInfo(name = "overview")
        var overview: String? = null,

        @ColumnInfo(name = "originalLanguage")
        var originalLanguage: String? = null,

        @ColumnInfo(name = "posterPath")
        var posterPath: String? = null,

        @ColumnInfo(name = "backdropPath")
        var backdropPath: String? = null,

        @ColumnInfo(name = "originalName")
        var originalName: String? = null,

        @ColumnInfo(name = "popularity")
        var popularity: Double? = null,

        @ColumnInfo(name = "voteAverage")
        var voteAverage: Double? = null,

        @ColumnInfo(name = "name")
        var name: String? = null,

        @PrimaryKey
        @NonNull
        @ColumnInfo(name = "id")
        var id: Int? = null,

        @ColumnInfo(name = "voteCount")
        var voteCount: Int? = null,

        @ColumnInfo(name = "favorited")
        var favorited: Boolean? = false

)