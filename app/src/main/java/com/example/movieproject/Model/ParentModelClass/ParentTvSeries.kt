package com.example.movieproject.Model.ParentModelClass

import com.example.movieproject.Model.TvModel.tvairingtodayresponse.ResultsItem

data class ParentTvSeries(
    val category: String,
    val airingtoday: ArrayList<ResultsItem>,
    val ontheair: ArrayList<com.example.movieproject.Model.TvModel.tvontheairresponse.ResultsItem>,
    val toprated: ArrayList<com.example.movieproject.Model.TvModel.tvtopratedresponse.ResultsItem>,
    val popular: ArrayList<com.example.movieproject.Model.TvModel.tvpopularresponse.ResultsItem>
)