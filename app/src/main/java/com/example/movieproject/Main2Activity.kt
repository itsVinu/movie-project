package com.example.movieproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieproject.Model.ParentModelClass.ParentTvSeries
import com.example.movieproject.Model.TvModel.tvairingtodayresponse.ResultsItem
import com.example.movieproject.adapter.parentadapter.ParentTvAdapter
import com.example.movieproject.client.Client
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.main2.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Main2Activity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener {

    val list2 = arrayListOf<ResultsItem>()

    val list1 = arrayListOf<com.example.movieproject.Model.TvModel.tvontheairresponse.ResultsItem>()

    val list3 = arrayListOf<com.example.movieproject.Model.TvModel.tvtopratedresponse.ResultsItem>()

    val list4 = arrayListOf<com.example.movieproject.Model.TvModel.tvpopularresponse.ResultsItem>()

    val list = arrayListOf<ParentTvSeries>()
    val parentadapter = ParentTvAdapter(list)

    val category = arrayOf("Airing Today", "On The Air", "Top Rated", "Popular")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        setSupportActionBar(toolbar)

        for (i in 0..3) {
            if (i == 0) {
                list.add ( ParentTvSeries ( category[i], list2, list1, list3, list4 ) )
            }
            else if (i == 1) {
                list.add ( ParentTvSeries ( category[i], list2, list1, list3, list4 ) )
            }
            else if (i == 2) {
                list.add ( ParentTvSeries ( category[i], list2, list1, list3, list4 ) )
            }
            else if (i == 3) {
                list.add ( ParentTvSeries ( category[i], list2, list1, list3, list4 ) )
            }
        }

        tvRv.apply {
            layoutManager = LinearLayoutManager(this@Main2Activity, RecyclerView.VERTICAL, false)
            adapter = parentadapter
        }
        parentadapter.onItemClick = {
            Toast.makeText(this, "asnjkhbciaskjcjk", Toast.LENGTH_SHORT).show()

            val intent = Intent(this,Main5Activity::class.java)
            intent.putExtra("category tv",it.category)
            startActivity(intent)
        }

        GlobalScope.launch {
            val response = withContext(Dispatchers.IO) { Client.api.getAllAiringTodayTv() }
            if (response.isSuccessful) {
                response.body()?.let { res ->
                    res.results?.let {
                        list2.addAll(it)
                    }
                    runOnUiThread { parentadapter.notifyDataSetChanged() }
                }
            }
        }

        GlobalScope.launch {
            val response = withContext(Dispatchers.IO) { Client.api.getAllOnTheAirTv() }
            if (response.isSuccessful) {
                response.body()?.let { res ->
                    res.results?.let { list1.addAll(it) }
                    runOnUiThread { parentadapter.notifyDataSetChanged() }
                }
            }
        }

        GlobalScope.launch {
            val response = withContext(Dispatchers.IO) { Client.api.getAllTopRatedTv() }
            if (response.isSuccessful) {
                response.body()?.let { res ->
                    res.results?.let { list3.addAll(it) }
                    runOnUiThread { parentadapter.notifyDataSetChanged() }
                }
            }
        }

        GlobalScope.launch {
            val response = withContext(Dispatchers.IO) { Client.api.getAllPopularTv() }
            if (response.isSuccessful) {
                response.body()?.let { res ->
                    res.results?.let { list4.addAll(it) }
                    runOnUiThread { parentadapter.notifyDataSetChanged() }
                }
            }
        }



        val toggle = ActionBarDrawerToggle(
            this,
            drawer,
            toolbar,
            R.string.open,
            R.string.close
        )

        drawer.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.movie -> {
                startActivity(Intent(this,MainActivity::class.java))
                // Toast.makeText(this,"MOVIE", Toast.LENGTH_SHORT).show()
            }
            R.id.tvSeries ->{
                startActivity(Intent(this,Main2Activity::class.java))
            }
            R.id.discover ->{
                startActivity(Intent(this,Main3Activity::class.java))
            }
            R.id.people ->{
                startActivity(Intent(this,Main4Activity::class.java))
            }
//            R.id.wishlistMovie ->{
//                startActivity(Intent(this,wishlistMovieActivity::class.java))
//            }
//            R.id.wishlistTv ->{
//                startActivity(Intent(this,WishlistTvActivity::class.java))
//            }

        }
        drawer.closeDrawer(GravityCompat.START)   //used to close the navigation drawer when the items inside the drawer are clicked
        return true
    }
}

