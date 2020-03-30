package com.example.movieproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieproject.Model.MovieModel.popularresponse.ResultsItem
import com.example.movieproject.adapter.DisplayIndividualAdapter.*
import com.example.movieproject.client.Client
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main5.*
import kotlinx.android.synthetic.main.main.*
import kotlinx.android.synthetic.main.main5.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class Main5Activity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener {

    val list1 = arrayListOf<com.example.movieproject.Model.MovieModel.upcomingresponse.ResultsItem>()
    val childadapter1 = DisplayUpcomingMovieAdapter(list1)

    val list2 = arrayListOf<ResultsItem>()
    val childadapter2 = DisplayPopularMovieAdapter(list2)

    val list3 = arrayListOf<com.example.movieproject.Model.MovieModel.topratedresponse.ResultsItem>()
    val childadapter3 = DisplayTopRatedMovieAdapter(list3)

    val list4 = arrayListOf<com.example.movieproject.Model.MovieModel.nowplayingresponse.ResultsItem>()
    val childadapter4 = DisplayNowPlayingMovieAdapter(list4)

    val list5 = arrayListOf<com.example.movieproject.DiscoverModel.discovermovieresponse.ResultsItem>()
    val childadapter5 = DisplayMovieDiscoverAdapter(list5)

    val list6 = arrayListOf<com.example.movieproject.DiscoverModel.discovertvresponse.ResultsItem>()
    val childadapter6 = DisplayTvDiscoverAdapter(list6)

    val list7 = arrayListOf<com.example.movieproject.Model.TvModel.tvairingtodayresponse.ResultsItem>()
    val childadapter7 = DisplayAiringTodayTvAdapter(list7)

    val list8 = arrayListOf<com.example.movieproject.Model.TvModel.tvontheairresponse.ResultsItem>()
    val childadapter8 = DisplayOnTheAirTvAdapter(list8)

    val list9 = arrayListOf<com.example.movieproject.Model.TvModel.tvtopratedresponse.ResultsItem>()
    val childadapter9 = DisplayTopRatedTvAdapter(list9)

    val list10 = arrayListOf<com.example.movieproject.Model.TvModel.tvpopularresponse.ResultsItem>()
    val childadapter10 = DisplayPopularTvAdapter(list10)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main5)

        setSupportActionBar(toolbar5)

        var m = intent.getStringExtra("category movie")
        var t = intent.getStringExtra("category tv")
        var d = intent.getStringExtra("category discover")


        if (m == "Upcoming Movies"){

            displayRv.apply {
                layoutManager = LinearLayoutManager(this@Main5Activity, RecyclerView.VERTICAL, false)
                adapter = childadapter1
            }
            childadapter1.onItemClick = {
                val intent = Intent(this,Main6Activity::class.java)
                intent.putExtra("id",it.id.toString())
                startActivity(intent)
            }

            GlobalScope.launch {
                val response = withContext(Dispatchers.IO) { Client.api.getUpcomingMovies() }
                if (response.isSuccessful) {
                    response.body()?.let { res ->
                        res.results?.let {
                            list1.addAll(it)
                        }
                        runOnUiThread { childadapter1.notifyDataSetChanged() }
                    }
                }
            }
        }
        else if (m == "Popular Movies"){

            displayRv.apply {
                layoutManager = LinearLayoutManager(this@Main5Activity,RecyclerView.VERTICAL,false)
                adapter = childadapter2
            }

            childadapter2.onItemClick = {
                val intent = Intent(this,Main6Activity::class.java)
                intent.putExtra("id",it.id.toString())
                startActivity(intent)
            }

            GlobalScope.launch {
                val response = withContext(Dispatchers.IO) { Client.api.getAllPopularMovies() }
                if (response.isSuccessful) {
                    response.body()?.let { res ->
                        res.results?.let {
                            list2.addAll(it)
                        }
                        runOnUiThread { childadapter2.notifyDataSetChanged() }
                    }
                }
            }
        }
        else if (m == "Top Rated Movies"){

            displayRv.apply {
                layoutManager = LinearLayoutManager(this@Main5Activity,RecyclerView.VERTICAL,false)
                adapter = childadapter3
            }

            childadapter3.onItemClick = {
                val intent = Intent(this,Main6Activity::class.java)
                intent.putExtra("id",it.id.toString())
                startActivity(intent)
            }

            GlobalScope.launch {
                val response = withContext(Dispatchers.IO) { Client.api.getAllTopRatedMovies() }
                if (response.isSuccessful) {
                    response.body()?.let { res ->
                        res.results?.let {
                            list3.addAll(it)
                        }
                        runOnUiThread { childadapter3.notifyDataSetChanged() }
                    }
                }
            }
        }
        else if (m == "Now Playing Movies"){

            displayRv.apply {
                layoutManager = LinearLayoutManager(this@Main5Activity,RecyclerView.VERTICAL,false)
                adapter = childadapter4
            }

            childadapter4.onItemClick = {
                val intent = Intent(this,Main6Activity::class.java)
                intent.putExtra("id",it.id.toString())
                startActivity(intent)
            }

            GlobalScope.launch {
                val response = withContext(Dispatchers.IO) { Client.api.getAllNowPlayingMovies() }
                if (response.isSuccessful) {
                    response.body()?.let {
                        list4.addAll(it.results!!)
                        runOnUiThread { childadapter4.notifyDataSetChanged() }
                    }
                }
            }
        }


        if (t == "Airing Today"){

            displayRv.apply {
                layoutManager = LinearLayoutManager(this@Main5Activity,RecyclerView.VERTICAL,false)
                adapter = childadapter7
            }

            childadapter7.onItemClick = {
                val intent = Intent(this,Main7Activity::class.java)
                intent.putExtra("tvid",it.id.toString())
                startActivity(intent)
            }

            GlobalScope.launch {
                val response = withContext(Dispatchers.IO) { Client.api.getAllAiringTodayTv() }
                if (response.isSuccessful) {
                    response.body()?.let { res ->
                        res.results?.let {
                            list7.addAll(it)
                        }
                        runOnUiThread { childadapter7.notifyDataSetChanged() }
                    }
                }
            }
        }
        else if (t == "On The Air"){

            displayRv.apply {
                layoutManager = LinearLayoutManager(this@Main5Activity,RecyclerView.VERTICAL,false)
                adapter = childadapter8
            }

            childadapter8.onItemClick = {
                val intent = Intent(this,Main7Activity::class.java)
                intent.putExtra("tvid",it.id.toString())
                startActivity(intent)
            }

            GlobalScope.launch {
                val response = withContext(Dispatchers.IO) { Client.api.getAllOnTheAirTv() }
                if (response.isSuccessful) {
                    response.body()?.let { res ->
                        res.results?.let {
                            list8.addAll(it)
                        }
                        runOnUiThread { childadapter8.notifyDataSetChanged() }
                    }
                }
            }
        }
        else if (t == "Top Rated"){

            displayRv.apply {
                layoutManager = LinearLayoutManager(this@Main5Activity,RecyclerView.VERTICAL,false)
                adapter = childadapter9
            }

            childadapter9.onItemClick = {
                val intent = Intent(this,Main7Activity::class.java)
                intent.putExtra("tvid",it.id.toString())
                startActivity(intent)
            }

            GlobalScope.launch {
                val response = withContext(Dispatchers.IO) { Client.api.getAllTopRatedTv() }
                if (response.isSuccessful) {
                    response.body()?.let { res ->
                        res.results?.let {
                            list9.addAll(it)
                        }
                        runOnUiThread { childadapter9.notifyDataSetChanged() }
                    }
                }
            }
        }
        else if (t == "Popular"){

            displayRv.apply {
                layoutManager = LinearLayoutManager(this@Main5Activity,RecyclerView.VERTICAL,false)
                adapter = childadapter10
            }

            childadapter10.onItemClick = {
                val intent = Intent(this,Main7Activity::class.java)
                intent.putExtra("tvid",it.id.toString())
                startActivity(intent)
            }

            GlobalScope.launch {
                val response = withContext(Dispatchers.IO) { Client.api.getAllPopularTv() }
                if (response.isSuccessful) {
                    response.body()?.let { res ->
                        res.results?.let {
                            list10.addAll(it)
                        }
                        runOnUiThread { childadapter10.notifyDataSetChanged() }
                    }
                }
            }
        }


        if (d == "Movies"){

            displayRv.apply {
                layoutManager = LinearLayoutManager(this@Main5Activity,RecyclerView.VERTICAL,false)
                adapter = childadapter5
            }

            childadapter5.onItemClick = {
                val intent = Intent(this,Main6Activity::class.java)
                intent.putExtra("id",it.id.toString())
                startActivity(intent)
            }

            GlobalScope.launch {
                val response = withContext(Dispatchers.IO) { Client.api.getAllMovieDiscover() }
                if (response.isSuccessful) {
                    response.body()?.let { res ->
                        res.results?.let {
                            list5.addAll(it)
                        }
                        runOnUiThread { childadapter5.notifyDataSetChanged() }
                    }
                }
            }
        }
        else if (d == "Tv Series"){

            displayRv.apply {
                layoutManager = LinearLayoutManager(this@Main5Activity,RecyclerView.VERTICAL,false)
                adapter = childadapter6
            }

            childadapter6.onItemClick = {
                val intent = Intent(this,Main7Activity::class.java)
                intent.putExtra("tvid",it.id.toString())
                startActivity(intent)
            }

            GlobalScope.launch {
                val response = withContext(Dispatchers.IO) { Client.api.getAllTvDiscover() }
                if (response.isSuccessful) {
                    response.body()?.let { res ->
                        res.results?.let {
                            list6.addAll(it)
                        }
                        runOnUiThread { childadapter6.notifyDataSetChanged() }
                    }
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
