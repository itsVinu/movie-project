package com.example.movieproject.Model.PeopleModel.popularpeopleresponse

import com.google.gson.annotations.SerializedName

data class PeoplePopularResponse(

    @field:SerializedName("page")
	val page: Int? = null,

    @field:SerializedName("total_pages")
	val totalPages: Int? = null,

    @field:SerializedName("results")
	val results: List<ResultsItem>? = null,

    @field:SerializedName("total_results")
	val totalResults: Int? = null
)