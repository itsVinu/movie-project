package com.example.movieproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieproject.adapter.castadapter.CastTvAdapter
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

class Main7Activity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    val list = arrayListOf<com.example.movieproject.Model.TvModel.casttvresponse.CastItem>()
    val casttvadapter =
        CastTvAdapter(list)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main7)

        val tv_id = intent.getStringExtra("tvid")


        trailerBtn.setOnClickListener{
            val intent = Intent(this,TrailerTvActivity::class.java)
            intent.putExtra("tvid",tv_id)
            startActivity(intent)
        }


        GlobalScope.launch {
            val response = withContext(Dispatchers.IO) { Client.api.getAllDetailTv(tv_id)}
            if (response.isSuccessful){
                response.body()?.let {
                    runOnUiThread{
                        detailTv1.text = it.originalName
                        detailTv2.text = it.firstAirDate
                        detailTv3.text = it.overview
                        Picasso.get().load("https://image.tmdb.org/t/p/w500" + it.posterPath.toString()).into(detailIv)
                        Picasso.get().load("https://image.tmdb.org/t/p/w500" + it.posterPath.toString()).into(detailIv2)
                    }
                }
            }
        }

        main6Rv.apply {
            layoutManager = LinearLayoutManager(this@Main7Activity, RecyclerView.HORIZONTAL, false)
            adapter = casttvadapter
        }

        GlobalScope.launch {
            val response = withContext(Dispatchers.IO) { Client.api.getAllCastTv(tv_id) }
            if (response.isSuccessful) {
                response.body()?.let { res ->
                    res.cast?.let {
                        list.addAll(it)
                    }
                    runOnUiThread { casttvadapter.notifyDataSetChanged() }
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
