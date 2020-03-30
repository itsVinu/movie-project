package com.example.movieproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieproject.Model.SearchModel.ResultsItem
import com.example.movieproject.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.slider.view.*
import kotlinx.android.synthetic.main.slider_people.view.*

class SearchAdapter(val list: List<ResultsItem>):
    RecyclerView.Adapter<SearchAdapter.UserViewHolder>() {

    var onItemClick: ((user: ResultsItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.slider,
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

        fun bind(user: ResultsItem) {
            itemView.apply {
                sliderPeopleTv1.text = user.title
                sliderPeopleTv2.text = user.releaseDate
                Picasso.get().load("https://image.tmdb.org/t/p/w500" + user.posterPath.toString()).into(sliderPeopleIv)
                setOnClickListener{
                    onItemClick?.invoke(user)
                }
            }
        }
    }
}