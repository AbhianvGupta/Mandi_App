package com.android.mandi.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.android.mandi.room.dao.MandiDao
import com.android.mandi.room.entity.LoyaltyIndexEntity
import com.android.mandi.room.entity.SellerEntity
import com.android.mandi.room.entity.VillageEntity

@Database(
    entities = [VillageEntity::class, SellerEntity::class, LoyaltyIndexEntity::class],
    version = 1
)
abstract class MandiDB : RoomDatabase() {
    abstract fun mandiDao(): MandiDao

    companion object {
        @Volatile
        private var instance: MandiDB? = null

        // build the db
        fun getInstance(context: Context): MandiDB {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): MandiDB {
            return Room.databaseBuilder(
                context.applicationContext,
                MandiDB::class.java,
                "mandi_database"
            ).allowMainThreadQueries().build()
        }
    }
}