package com.android.mandi.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.mandi.room.entity.LoyaltyIndexEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LoyaltyIndexDao {
    @Query("SELECT * FROM loyalty_index")
    fun getAllLoyaltyIndices(): Flow<List<LoyaltyIndexEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg indices: LoyaltyIndexEntity)
}