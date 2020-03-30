package com.example.movieproject.adapter.parentadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieproject.Model.ParentModelClass.ParentDiscover
import com.example.movieproject.R
import com.example.movieproject.adapter.childadapter.DiscoverChildAdapter.MovieDiscoverAdapter
import com.example.movieproject.adapter.childadapter.DiscoverChildAdapter.TvDiscoverAdapter
import kotlinx.android.synthetic.main.main_recycler.view.*

class ParentDiscoverAdapter(val list: List<ParentDiscover>):
    RecyclerView.Adapter<ParentDiscoverAdapter.UserViewHolder>() {

    var onItemClick: ((user: ParentDiscover) -> Unit)? = null

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

        fun bind(user: ParentDiscover) {
            itemView.apply {
                mainTv1.text = user.category

                if(user.category == "Movies"){
                    mainRecyclerRv.apply {
                        layoutManager = LinearLayoutManager(context,RecyclerView.HORIZONTAL,false)
                        adapter =
                            MovieDiscoverAdapter(
                                user.movie
                            )
                    }
                }
                else if(user.category === "Tv Series"){
                    mainRecyclerRv.apply {
                        layoutManager = LinearLayoutManager(context,RecyclerView.HORIZONTAL,false)
                        adapter =
                            TvDiscoverAdapter(
                                user.tv
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
