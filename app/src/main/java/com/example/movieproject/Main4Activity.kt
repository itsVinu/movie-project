package com.example.movieproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieproject.Model.PeopleModel.popularpeopleresponse.ResultsItem
import com.example.movieproject.adapter.childadapter.PeopleChildAdapter.PeoplePopularAdapter
import com.example.movieproject.client.Client
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main4.*
import kotlinx.android.synthetic.main.main4.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Main4Activity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    val list = arrayListOf<ResultsItem>()
    val peopleadapter = PeoplePopularAdapter(list)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        setSupportActionBar(toolbar)

        peopleRv.apply {
            layoutManager = LinearLayoutManager(this@Main4Activity,RecyclerView.VERTICAL,false)
            adapter = peopleadapter
        }

        peopleadapter.onItemClick = {
            Toast.makeText(this,"people",Toast.LENGTH_LONG).show()

            val intent = Intent(this,Main8Activity::class.java)
            intent.putExtra("people",it.id.toString())
            startActivity(intent)
        }

        GlobalScope.launch {
            val response = withContext(Dispatchers.IO) { Client.api.getAllPeoplePopular() }
            if (response.isSuccessful) {
                response.body()?.let { res ->
                    res.results?.let {
                        list.addAll(it)
                        Log.i("abc", it.toString())}
                    runOnUiThread {
                        peopleadapter.notifyDataSetChanged()
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


