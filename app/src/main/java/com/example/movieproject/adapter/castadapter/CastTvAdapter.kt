package com.example.movieproject.adapter.castadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieproject.R
import com.example.movieproject.Model.TvModel.casttvresponse.CastItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.slider.view.*

class CastTvAdapter(val list: List<CastItem>):
    RecyclerView.Adapter<CastTvAdapter.UserViewHolder>() {

    var onItemClick: ((user: CastItem) -> Unit)? = null

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

        fun bind(user: CastItem) {
            itemView.apply {
                sliderTv1.text = user.name
                sliderTv2.text = user.character
                Picasso.get().load("https://image.tmdb.org/t/p/w500" + user.profilePath.toString()).into(sliderIv)
                setOnClickListener{
                    onItemClick?.invoke(user)
                }
            }
        }
    }
}