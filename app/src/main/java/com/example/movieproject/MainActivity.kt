package com.example.movieproject

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieproject.Model.MovieModel.popularresponse.ResultsItem
import com.example.movieproject.Model.ParentModelClass.ParentMovies
import com.example.movieproject.adapter.SearchAdapter
import com.example.movieproject.adapter.childadapter.MovieChildAdapter.NowPlayingMovieAdapter
import com.example.movieproject.adapter.childadapter.MovieChildAdapter.PopularMovieAdapter
import com.example.movieproject.adapter.childadapter.MovieChildAdapter.TopRatedMovieAdapter
import com.example.movieproject.adapter.childadapter.MovieChildAdapter.UpcomingMovieAdapter
import com.example.movieproject.adapter.parentadapter.ParentMovieAdapter
import com.example.movieproject.client.Client
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main.*
import kotlinx.android.synthetic.main.main_recycler.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    val list2 = arrayListOf<ResultsItem>()
    val popularadapter = PopularMovieAdapter(list2)

    val list1 = arrayListOf<com.example.movieproject.Model.MovieModel.upcomingresponse.ResultsItem>()
    val upcomingadapter = UpcomingMovieAdapter(list1)

    val list3 = arrayListOf<com.example.movieproject.Model.MovieModel.topratedresponse.ResultsItem>()
    val topratedadapter = TopRatedMovieAdapter(list3)

    val list4 = arrayListOf<com.example.movieproject.Model.MovieModel.nowplayingresponse.ResultsItem>()
    val nowplayingadapter = NowPlayingMovieAdapter(list4)

    val list = arrayListOf<ParentMovies>()
    val parentadapter = ParentMovieAdapter(list)

    val list5 = arrayListOf<com.example.movieproject.Model.SearchModel.ResultsItem>()
    val searchadapter = SearchAdapter(list5)



    val category = arrayOf("Upcoming Movies", "Popular Movies", "Top Rated Movies", "Now Playing Movies")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        for (i in 0..3) {
            if (i == 0) {
                list.add( ParentMovies ( category[i], list2, list1, list3, list4 ))
            }
            else if (i == 1) {
                list.add( ParentMovies ( category[i], list2, list1, list3, list4 ) )
            }
            else if (i == 2) {
                list.add( ParentMovies ( category[i], list2, list1, list3, list4 ) )
            }
            else if (i == 3) {
                list.add( ParentMovies ( category[i], list2, list1, list3, list4 ) )
            }
        }

        movieRv.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
            adapter = parentadapter
        }
        parentadapter.onItemClick = {

            Toast.makeText(this, "cxfxfxcncd", Toast.LENGTH_SHORT).show()

            val intent = Intent(this,Main5Activity::class.java)
            intent.putExtra("category movie",it.category)
            startActivity(intent)
        }



        GlobalScope.launch {
            val response = withContext(Dispatchers.IO) { Client.api.getUpcomingMovies() }
            if (response.isSuccessful) {
                response.body()?.let { res ->
                    res.results?.let {
                        list1.addAll(it)
                    }
                    runOnUiThread { parentadapter.notifyDataSetChanged() }
                }
            }
        }

        GlobalScope.launch {
            val response = withContext(Dispatchers.IO) { Client.api.getAllPopularMovies() }
            if (response.isSuccessful) {
                response.body()?.let { res ->
                    res.results?.let { list2.addAll(it) }
                    runOnUiThread { parentadapter.notifyDataSetChanged() }
                }
            }
        }

        GlobalScope.launch {
            val response = withContext(Dispatchers.IO) { Client.api.getAllTopRatedMovies() }
            if (response.isSuccessful) {
                response.body()?.let { res ->
                    res.results?.let { list3.addAll(it) }
                    runOnUiThread { parentadapter.notifyDataSetChanged() }
                }
            }
        }

        GlobalScope.launch {
            val response = withContext(Dispatchers.IO) { Client.api.getAllNowPlayingMovies() }
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


//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.menu1
//            , menu)
//
//        val item = menu?.findItem(R.id.search)
//
//        val searchView = item?.actionView as androidx.appcompat.widget.SearchView
//
//        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
//
//        searchView.queryHint = "search here..."
//        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
//        searchView.setOnQueryTextListener(object :androidx.appcompat.widget.SearchView.OnQueryTextListener{
//
//
//            override fun onQueryTextSubmit(p0: String?): Boolean {
//
//                if (p0?.length!! > 2){
//                    loadJson(p0.toString())
//                }
//                searchadapter.notifyDataSetChanged()
//                return false
//            }
//
//
//            override fun onQueryTextChange(p0: String?): Boolean {
//                if (p0!!.length>10){
//                    loadJson(p0.toString())
//                }
//                searchadapter.notifyDataSetChanged()
//                return false
//            }
//
//        })
//
//        item.icon.setVisible(false,false)
//        return true
//    }
//
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//
//        val id:Int = item.itemId
//
//        if (id == R.id.search){
//            Toast.makeText(this,"search the news content here", Toast.LENGTH_LONG).show()
//        }
//        return true
//
//    }
//
//
//    private fun loadJson(keyword:String){
//
//        if (keyword.length > 2){
//            movieRv.apply {
//                layoutManager = LinearLayoutManager(this@MainActivity,RecyclerView.VERTICAL,false)
//                adapter = searchadapter
//            }
//            searchadapter.onItemClick = {
//                val intent = Intent(this, Main6Activity::class.java)
//                intent.putExtra("search",it.id)
//                startActivity(intent)
//            }
//            searchadapter.notifyDataSetChanged()
//
//            GlobalScope.launch {
//                val response = withContext(Dispatchers.IO){Client.api.getSearch("$keyword")}
//                if (response.isSuccessful){
//                    response.body()?.let { res->
//                        res.results?.let{
//                            list5.clear()
//                            list5.addAll(it)}
//                        runOnUiThread{searchadapter.notifyDataSetChanged()}
//                    }
//                }
//            }
//            searchadapter.notifyDataSetChanged()
//        }
//        else{
//            movieRv.apply {
//                layoutManager = LinearLayoutManager(this@MainActivity,RecyclerView.VERTICAL,false)
//                adapter = parentadapter
//            }
//
//            parentadapter.onItemClick = {
//                Toast.makeText(this,"movie",Toast.LENGTH_LONG).show()
//
//                val intent = Intent(this,MainActivity::class.java)
//                intent.putExtra("category",it.category)
//                startActivity(intent)
//            }
//            parentadapter.notifyDataSetChanged()
//
//            GlobalScope.launch (Dispatchers.Main) {
//                val response = withContext(Dispatchers.IO) { Client.api.getAllNowPlayingMovies() }
//                if (response.isSuccessful) {
//                    response.body()?.let { res ->
//                        res.results?.let {
//                            list5.clear()
//                            list4.addAll(it) }
//                        runOnUiThread { parentadapter.notifyDataSetChanged() }
//                    }
//                }
//            }
//            parentadapter.notifyDataSetChanged()
//        }
//    }

}