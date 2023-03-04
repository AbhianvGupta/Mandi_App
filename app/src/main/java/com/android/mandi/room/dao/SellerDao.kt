package com.android.mandi.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.mandi.room.entity.SellerEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SellerDao {
    @Query("SELECT * FROM sellers")
    fun getAllSellers(): Flow<List<SellerEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg sellers: SellerEntity)
}