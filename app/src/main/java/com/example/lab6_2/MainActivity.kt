package com.example.lab6_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.graphics.Bitmap
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import android.graphics.BitmapFactory
import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
import java.net.URL
import java.util.concurrent.Executors.newFixedThreadPool

class MainActivity : AppCompatActivity() {
    private lateinit var executor: ExecutorService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       executor = For_Exec().executor
        executor.execute {
            val url = URL("https://i.ibb.co/BPDWVhj/unnamed.jpg")
            val bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream())

            runOnUiThread {
                findViewById<ImageView>(R.id.imageView).setImageBitmap(bitmap)
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        executor.shutdown()
    }

}