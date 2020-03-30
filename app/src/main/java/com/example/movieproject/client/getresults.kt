package com.example.movieproject.client

import com.example.movieproject.DiscoverModel.discovertvresponse.DiscoverTvResponse
import com.example.movieproject.DiscoverModel.discovermovieresponse.DiscoverMovieResponse
import com.example.movieproject.Model.MovieModel.castmovieresponse.CastMovieResponse
import com.example.movieproject.Model.MovieModel.detailresponse.DetailMovieResponse
import com.example.movieproject.Model.MovieModel.nowplayingresponse.MovieNowPlayingResponse
import com.example.movieproject.Model.MovieModel.popularresponse.MoviePopularResponse
import com.example.movieproject.Model.MovieModel.topratedresponse.MovieTopRatedResponse
import com.example.movieproject.Model.MovieModel.upcomingresponse.MovieUpcomingResponse
import com.example.movieproject.Model.PeopleModel.peopledetailresponse.PeopleDetailResponse
import com.example.movieproject.Model.PeopleModel.popularpeopleresponse.PeoplePopularResponse
import com.example.movieproject.Model.SearchModel.SearchResponse
import com.example.movieproject.Model.TrailerMovieModel.TrailerMovieResponse
import com.example.movieproject.Model.TrailerTvModel.TrailerTvResponse
import com.example.movieproject.Model.TvModel.casttvresponse.CastTvResponse
import com.example.movieproject.Model.TvModel.tvairingtodayresponse.TvAiringTodayResponse
import com.example.movieproject.Model.TvModel.tvdetailresponse.DetailTvResponse
import com.example.movieproject.Model.TvModel.tvontheairresponse.TvOnTheAirResponse
import com.example.movieproject.Model.TvModel.tvpopularresponse.TvPopularResponse
import com.example.movieproject.Model.TvModel.tvtopratedresponse.TvTopRatedResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface getresults {

    @GET("movie/upcoming?api_key=3d812b53e8327e3496dbe05fb820cf0d")
    suspend fun getUpcomingMovies():Response<MovieUpcomingResponse>

    @GET("movie/popular?api_key=3d812b53e8327e3496dbe05fb820cf0d")
    suspend fun getAllPopularMovies():Response<MoviePopularResponse>

    @GET("movie/top_rated?api_key=3d812b53e8327e3496dbe05fb820cf0d")
    suspend fun getAllTopRatedMovies():Response<MovieTopRatedResponse>

    @GET("movie/now_playing?api_key=3d812b53e8327e3496dbe05fb820cf0d")
    suspend fun getAllNowPlayingMovies():Response<MovieNowPlayingResponse>

    @GET("tv/airing_today?api_key=3d812b53e8327e3496dbe05fb820cf0d")
    suspend fun getAllAiringTodayTv() : Response<TvAiringTodayResponse>

    @GET("tv/on_the_air?api_key=3d812b53e8327e3496dbe05fb820cf0d")
    suspend fun getAllOnTheAirTv() : Response<TvOnTheAirResponse>

    @GET("tv/top_rated?api_key=3d812b53e8327e3496dbe05fb820cf0d")
    suspend fun getAllTopRatedTv() : Response<TvTopRatedResponse>

    @GET("tv/popular?api_key=3d812b53e8327e3496dbe05fb820cf0d")
    suspend fun getAllPopularTv() : Response<TvPopularResponse>

    @GET("discover/tv?api_key=3d812b53e8327e3496dbe05fb820cf0d")
    suspend fun getAllTvDiscover() : Response<DiscoverTvResponse>

    @GET("discover/movie?api_key=3d812b53e8327e3496dbe05fb820cf0d")
    suspend fun getAllMovieDiscover() : Response<DiscoverMovieResponse>

    @GET("person/popular?api_key=3d812b53e8327e3496dbe05fb820cf0d")
    suspend fun getAllPeoplePopular() : Response<PeoplePopularResponse>

    @GET("movie/{movie_id}?api_key=3d812b53e8327e3496dbe05fb820cf0d&language=en-US")
    suspend fun getAllDetailMovies(@Path("movie_id")movie_id:String):Response<DetailMovieResponse>

    @GET("tv/{tv_id}?api_key=3d812b53e8327e3496dbe05fb820cf0d&language=en-US")
    suspend fun getAllDetailTv(@Path("tv_id")tv_id:String):Response<DetailTvResponse>

    @GET("movie/{movie_id}/credits?api_key=3d812b53e8327e3496dbe05fb820cf0d&language=en-US")
    suspend fun getAllCastMovies(@Path("movie_id")movie_id:String):Response<CastMovieResponse>

    @GET("tv/{tv_id}/credits?api_key=3d812b53e8327e3496dbe05fb820cf0d&language=en-US")
    suspend fun getAllCastTv(@Path("tv_id")tv_id:String):Response<CastTvResponse>

    @GET("person/{person_id}?api_key=3d812b53e8327e3496dbe05fb820cf0d&language=en-US")
    suspend fun getAllPeopleDetails(@Path("person_id")person_id:String):Response<PeopleDetailResponse>

    @GET("search/movie?api_key=3d812b53e8327e3496dbe05fb820cf0d")
    suspend fun getSearch(@Query("query")query:String) : Response<SearchResponse>

    @GET("movie/{movie_id}/videos?api_key=3d812b53e8327e3496dbe05fb820cf0d")
    suspend fun getAllMovieTrailer(@Path("movie_id")movie_id: String) : Response<TrailerMovieResponse>

    @GET("movie/{tv_id}/videos?api_key=3d812b53e8327e3496dbe05fb820cf0d")
    suspend fun getAllTvTrailer(@Path("tv_id")tv_id: String) : Response<TrailerTvResponse>

}
