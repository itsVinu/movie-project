package com.example.movieproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.example.movieproject.client.Client
import com.google.android.material.navigation.NavigationView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main.*
import kotlinx.android.synthetic.main.main6.*
import kotlinx.android.synthetic.main.main8.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Main8Activity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main8)

        setSupportActionBar(toolbar8)

        val people_id = intent.getStringExtra("people")

        GlobalScope.launch {
            val response = withContext(Dispatchers.IO) { Client.api.getAllPeopleDetails(people_id)}
            if (response.isSuccessful){
                response.body()?.let {
                    runOnUiThread{
                        mainTv1.text = it.name
                        mainTv2.text = it.birthday
                        mainTv3.text = it.biography
                        Picasso.get().load("https://image.tmdb.org/t/p/w500" + it.profilePath.toString()).into(mainIv1)
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
