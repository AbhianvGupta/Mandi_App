package com.android.mandi

import android.app.Application
import com.android.mandi.room.DatabasePopulator
import com.android.mandi.room.MandiDB
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