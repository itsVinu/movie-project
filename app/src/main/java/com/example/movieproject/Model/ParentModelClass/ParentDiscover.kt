package com.example.movieproject.Model.ParentModelClass

import com.example.movieproject.DiscoverModel.discovermovieresponse.ResultsItem

data class ParentDiscover (
    val category: String,
    val movie : List<ResultsItem>,
    val tv : List<com.example.movieproject.DiscoverModel.discovertvresponse.ResultsItem>
)