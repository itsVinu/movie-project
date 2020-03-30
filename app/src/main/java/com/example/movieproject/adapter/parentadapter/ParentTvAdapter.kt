package com.example.movieproject.adapter.parentadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieproject.Model.ParentModelClass.ParentTvSeries
import com.example.movieproject.R
import com.example.movieproject.adapter.childadapter.TvChildAdapter.AiringTodayTvAdapter
import com.example.movieproject.adapter.childadapter.TvChildAdapter.OnTheAirTvAdapter
import com.example.movieproject.adapter.childadapter.TvChildAdapter.PopularTvAdapter
import com.example.movieproject.adapter.childadapter.TvChildAdapter.TopRatedTvAdapter
import kotlinx.android.synthetic.main.main_recycler.view.*

class ParentTvAdapter(val list: List<ParentTvSeries>):
    RecyclerView.Adapter<ParentTvAdapter.UserViewHolder>() {

    var onItemClick: ((user: ParentTvSeries) -> Unit)? = null

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

        fun bind(user: ParentTvSeries) {
            itemView.apply {
                mainTv1.text = user.category

                if(user.category == "Airing Today"){
                    mainRecyclerRv.apply {
                        layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL,false)
                        adapter =
                            AiringTodayTvAdapter(
                                user.airingtoday
                            )
                    }
                }
                else if(user.category == "On The Air"){
                    mainRecyclerRv.apply {
                        layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL,false)
                        adapter =
                            OnTheAirTvAdapter(
                                user.ontheair
                            )
                    }
                }
                else if(user.category === "Top Rated"){
                    mainRecyclerRv.apply {
                        layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL,false)
                        adapter =
                            TopRatedTvAdapter(
                                user.toprated
                            )
                    }
                }
                else if(user.category === "Popular"){
                    mainRecyclerRv.apply {
                        layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL,false)
                        adapter =
                            PopularTvAdapter(
                                user.popular
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
