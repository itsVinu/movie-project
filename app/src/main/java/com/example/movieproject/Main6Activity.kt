package com.example.movieproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieproject.Model.MovieModel.castmovieresponse.CrewItem
import com.example.movieproject.adapter.castadapter.CastMovieAdapter
import com.example.movieproject.client.Client
import com.google.android.material.navigation.NavigationView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main.*
import kotlinx.android.synthetic.main.main6.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Main6Activity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener {

    val list = arrayListOf<CrewItem>()
    val castmovieadapter =
        CastMovieAdapter(list)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main6)

        setSupportActionBar(toolbar6)

        val movie_id = intent.getStringExtra("id")

        trailerBtn.setOnClickListener{
            val intent = Intent(this,TrailerMovieActivity::class.java)
            intent.putExtra("id",movie_id)
            startActivity(intent)
        }


        GlobalScope.launch {
            val response = withContext(Dispatchers.IO) { Client.api.getAllDetailMovies(movie_id)}
            if (response.isSuccessful){
                response.body()?.let {
                    runOnUiThread{
                        detailTv1.text = it.originalTitle
                        detailTv2.text = it.releaseDate
                        detailTv3.text = it.overview
                        Picasso.get().load("https://image.tmdb.org/t/p/w500" + it.posterPath.toString()).into(detailIv)
                        Picasso.get().load("https://image.tmdb.org/t/p/w500" + it.posterPath.toString()).into(detailIv2)
                    }
                }
            }
        }

        main6Rv.apply {
            layoutManager = LinearLayoutManager(this@Main6Activity, RecyclerView.HORIZONTAL, false)
            adapter = castmovieadapter
        }

        GlobalScope.launch {
            val response = withContext(Dispatchers.IO) { Client.api.getAllCastMovies(movie_id) }
            if (response.isSuccessful) {
                response.body()?.let { res ->
                    res.crew?.let {
                        list.addAll(it)
                    }
                    runOnUiThread { castmovieadapter.notifyDataSetChanged() }
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


        }
        drawer.closeDrawer(GravityCompat.START)   //used to close the navigation drawer when the items inside the drawer are clicked
        return true
    }
}
