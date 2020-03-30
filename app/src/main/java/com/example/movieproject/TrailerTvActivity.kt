package com.example.movieproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.movieproject.client.Client
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import kotlinx.android.synthetic.main.activity_trailer.*
import kotlinx.android.synthetic.main.activity_trailer_tv.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TrailerTvActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trailer_tv)

        val tv_id = intent.getStringExtra("tvid")


        GlobalScope.launch {
            val response = withContext(Dispatchers.IO){ Client.api.getAllTvTrailer(tv_id.toString())}
            if (response.isSuccessful){
                if (response.body()?.results.isNullOrEmpty()){
                    Toast.makeText(this@TrailerTvActivity,"Trailer not available", Toast.LENGTH_LONG).show()
                }else{
                    response.body()?.let {res->
                        res.results?.let {  }
                        runOnUiThread{
                            lifecycle.addObserver(youtube_button)
                            youtube_button.addYouTubePlayerListener(object : AbstractYouTubePlayerListener(){
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
