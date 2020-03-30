package com.example.movieproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.movieproject.client.Client
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import kotlinx.android.synthetic.main.activity_trailer.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TrailerMovieActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trailer)

        val movie_id = intent.getStringExtra("id")


        GlobalScope.launch {
            val response = withContext(Dispatchers.IO){ Client.api.getAllMovieTrailer(movie_id.toString())}
            if (response.isSuccessful){
                if (response.body()?.results.isNullOrEmpty()){
                   Toast.makeText(this@TrailerMovieActivity,"Trailer not available",Toast.LENGTH_LONG).show()
                }else{
                    response.body()?.let {res->
                        res.results?.let {  }
                        runOnUiThread{
                            lifecycle.addObserver(youtube)
                            youtube.addYouTubePlayerListener(object : AbstractYouTubePlayerListener(){
                                override fun onReady(youTubePlayer: YouTubePlayer) {
                                    val videoId:String = res.results?.get(0)?.key.toString()
                                    youTubePlayer.loadVideo(videoId, 0F)
                                    super.onReady(youTubePlayer)
                                }
                            })
                        }
                    }
                }
            }
        }




    }
}
