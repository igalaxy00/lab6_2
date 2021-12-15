package com.example.lab6_2

import android.app.Application
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class For_Exec : Application() {
    val executor: ExecutorService = Executors.newFixedThreadPool(1)
}