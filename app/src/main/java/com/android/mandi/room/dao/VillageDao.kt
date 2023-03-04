package com.android.mandi.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.mandi.room.entity.VillageEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface VillageDao {
    @Query("SELECT * FROM villages")
    fun getAllVillages(): Flow<List<VillageEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg villages: VillageEntity)
}