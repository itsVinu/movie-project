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
import com.example.movieproject.DiscoverModel.discovermovieresponse.ResultsItem
import com.example.movieproject.Model.ParentModelClass.ParentDiscover
import com.example.movieproject.adapter.parentadapter.ParentDiscoverAdapter
import com.example.movieproject.client.Client
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main3.*
import kotlinx.android.synthetic.main.main3.*
import kotlinx.android.synthetic.main.main3.toolbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Main3Activity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    val list1 = arrayListOf<ResultsItem>()

    val list2 = arrayListOf<com.example.movieproject.DiscoverModel.discovertvresponse.ResultsItem>()

    val list = arrayListOf<ParentDiscover>()
    val discoveradapter = ParentDiscoverAdapter(list)

    val category = arrayOf("Movies","Tv Series")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        setSupportActionBar(toolbar)

        for (i in 0..1) {
            if (i == 0) {
                list.add(ParentDiscover(category[i], list1, list2))
            } else if (i == 1) {
                list.add(ParentDiscover(category[i], list1, list2))
            }

        }
            discoverRv.apply {
                layoutManager = LinearLayoutManager(this@Main3Activity, RecyclerView.VERTICAL, false)
                adapter = discoveradapter
            }
            discoveradapter.onItemClick = {
                Toast.makeText(this, "Discover", Toast.LENGTH_SHORT).show()

                val intent = Intent(this,Main5Activity::class.java)
                intent.putExtra("category discover",it.category)
                startActivity(intent)
            }

            GlobalScope.launch {
                val response = withContext(Dispatchers.IO) { Client.api.getAllMovieDiscover() }
                if (response.isSuccessful) {
                    response.body()?.let { res ->
                        res.results?.let {
                            list1.addAll(it)
                        }
                        runOnUiThread { discoveradapter.notifyDataSetChanged() }
                    }
                }
            }

            GlobalScope.launch {
                val response = withContext(Dispatchers.IO) { Client.api.getAllTvDiscover() }
                if (response.isSuccessful) {
                    response.body()?.let { res ->
                        res.results?.let { list2.addAll(it) }
                        runOnUiThread { discoveradapter.notifyDataSetChanged() }
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
                startActivity(Intent(this, MainActivity::class.java))
                // Toast.makeText(this,"MOVIE", Toast.LENGTH_SHORT).show()
            }
            R.id.tvSeries -> {
                startActivity(Intent(this, Main2Activity::class.java))
            }
            R.id.discover -> {
                startActivity(Intent(this, Main3Activity::class.java))
            }
            R.id.people -> {
                startActivity(Intent(this, Main4Activity::class.java))
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



