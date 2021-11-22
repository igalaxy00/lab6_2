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

class MainActivity : AppCompatActivity() {
    private lateinit var executor: ExecutorService
    private val bitmapData = MutableLiveData<Bitmap>()
    private lateinit var imageView : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imageView  = findViewById(R.id.imageView)

        if (imageView.drawable == null) {
            load()
        }
        bitmapData.observe(this) {
            if (it != null) {
                imageView.setImageBitmap(it)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        executor.shutdown()
    }

    private fun load() {
        executor = Executors.newFixedThreadPool(1)
        executor.execute {
             URL("https://i.ibb.co/BPDWVhj/unnamed.jpg")
                 .openConnection().getInputStream().use {
                val bitmap = BitmapFactory.decodeStream(it)
                bitmapData.postValue(bitmap)
            }
        }

    }
}