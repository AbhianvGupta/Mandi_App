package com.android.mandi.helper

import android.app.Application
import com.android.mandi.room.DatabasePopulator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        CoroutineScope(Dispatchers.IO).launch {
            DatabasePopulator().populateDatabase(applicationContext)
        }
    }

}