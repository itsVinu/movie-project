package com.example.movieproject.adapter.parentadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieproject.Model.ParentModelClass.ParentMovies
import com.example.movieproject.R
import com.example.movieproject.adapter.childadapter.MovieChildAdapter.NowPlayingMovieAdapter
import com.example.movieproject.adapter.childadapter.MovieChildAdapter.PopularMovieAdapter
import com.example.movieproject.adapter.childadapter.MovieChildAdapter.TopRatedMovieAdapter
import com.example.movieproject.adapter.childadapter.MovieChildAdapter.UpcomingMovieAdapter
import kotlinx.android.synthetic.main.main_recycler.view.*

class ParentMovieAdapter(val list: List<ParentMovies>):
    RecyclerView.Adapter<ParentMovieAdapter.UserViewHolder>() {

    var onItemClick: ((user: ParentMovies) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.main_recycler,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class UserViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        fun bind(user: ParentMovies) {
            itemView.apply {
                mainTv1.text = user.category

                if(user.category == "Popular Movies"){
                    mainRecyclerRv.apply {
                        layoutManager = LinearLayoutManager(context,RecyclerView.HORIZONTAL,false)
                        adapter =
                            PopularMovieAdapter(
                                user.popular
                            )
                    }
                }
                else if(user.category === "Upcoming Movies"){
                    mainRecyclerRv.apply {
                        layoutManager = LinearLayoutManager(context,RecyclerView.HORIZONTAL,false)
                        adapter =
                            UpcomingMovieAdapter(
                                user.upcoming
                            )
                    }
                }
                else if(user.category === "Top Rated Movies"){
                    mainRecyclerRv.apply {
                        layoutManager = LinearLayoutManager(context,RecyclerView.HORIZONTAL,false)
                        adapter =
                            TopRatedMovieAdapter(
                                user.toprated
                            )
                    }
                }
                else if(user.category === "Now Playing Movies"){
                    mainRecyclerRv.apply {
                        layoutManager = LinearLayoutManager(context,RecyclerView.HORIZONTAL,false)
                        adapter =
                            NowPlayingMovieAdapter(
                                user.nowplaying
                            )
                    }
                }

                setOnClickListener{
                    onItemClick?.invoke(user)
                }
            }
        }
    }
}
