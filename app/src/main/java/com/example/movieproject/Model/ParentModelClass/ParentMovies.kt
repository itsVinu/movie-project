package com.example.movieproject.Model.ParentModelClass

import com.example.movieproject.Model.MovieModel.popularresponse.ResultsItem

data class ParentMovies (
    val category: String,
    val popular : List<ResultsItem>,
    val upcoming: List<com.example.movieproject.Model.MovieModel.upcomingresponse.ResultsItem>,
    val toprated : List<com.example.movieproject.Model.MovieModel.topratedresponse.ResultsItem>,
    val nowplaying : List<com.example.movieproject.Model.MovieModel.nowplayingresponse.ResultsItem>
)